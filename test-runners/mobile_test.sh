#!/bin/bash

export TEST_ENV=${TEST_ENV}
export LOCALE=de
export BROWSERSTACK=true

TEAMS_HOOK_URL="${3:-}"

if [[ -n "$GROUP" ]]
then
  GROUP=${GROUP}
  export LOCALE=${LOCALE}
fi


echo "GROUP = $GROUP"
echo "TEST_ENV = $TEST_ENV"
echo "BROWSERSTACK = $BROWSERSTACK"
echo " Microsoft Teams webhook url: ${TEAMS_HOOK_URL}"
set -x
./gradlew clean mobileTest $GRADLE_OPTIONS \
    --no-daemon \
    -Dgroups=${GROUP} \
    -DappName=${VERIMI_APP_NAME} \
    -DbuildNumber=${CI_JOB_ID} \
    -DprojectName=${CI_JOB_NAME} \
    -DbrowserStackRun=${BROWSERSTACK} \
    -Dlocale=${LOCALE} | tee gradlew.log

set +x

./teams_notification.sh "${TEAMS_HOOK_URL}" ${ACTION} || true
source "$(dirname "$(realpath "$0")")/_final_steps.sh"
