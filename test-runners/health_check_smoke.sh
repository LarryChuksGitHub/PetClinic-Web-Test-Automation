#!/bin/bash

export TEST_ENV=${TEST_ENV}
export LOCALE=en
source "$(dirname "$(realpath "$0")")/_setup_cloud.sh"

export so_device_id=${DEVICE_ID}
echo "TEST_ENV = $TEST_ENV"
echo "BROWSERSTACK = $BROWSERSTACK"
TEAMS_HOOK_URL=$3
echo " Microsoft Teams webhook url: ${TEAMS_HOOK_URL}"
set -x
./gradlew clean frontendTestByGroup $GRADLE_OPTIONS \
    --no-daemon \
    -Dgroups=health-check \
    -DbrowserStackRun=${BROWSERSTACK} \
    -DbuildNumber=$1 \
    -DprojectName=$2 \
    -Dlocale=${LOCALE} \
    -Dfile.encoding=UTF-8 | tee gradlew.log

set +x

if [[ $? -eq 0 ]]; then
    ./notification.sh "Health check passed for ${TEST_ENV}" "${TEAMS_HOOK_URL}" || true
else
    ./notification.sh "Health check failed for ${TEST_ENV}" "${TEAMS_HOOK_URL}" || true
fi
source "$(dirname "$(realpath "$0")")/_final_steps.sh"
