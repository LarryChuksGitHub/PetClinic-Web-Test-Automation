#!/bin/bash

  export DIPP_TEST_ENV="${TEST_ENV}"
  export HEADLESS_MODE="${HEADLESS_MODE:-HEADLESS_MODE}"
  export BROWSERSTACK="false"
  export so_device_id="${DEVICE_ID:-}"

  TEAMS_HOOK_URL="${3:-}"

  if [[ -n "${GROUP:-}" ]]; then
    GROUP="${GROUP}"
    export LOCALE="${LOCALE:-de}"
    export LOCALE="de"


  echo "GROUP = $GROUP"
  echo "DIPP_TEST_ENV = $DIPP_TEST_ENV"
  echo "BROWSERSTACK = $BROWSERSTACK"
  #echo "Microsoft Teams webhook url: ${TEAMS_HOOK_URL}"

  set -x
  ./gradlew clean backendTestByGroup $GRADLE_OPTIONS \
      --no-daemon \
      -Dgroups="$GROUP" \
      -DbuildNumber="$CI_JOB_ID" \
      -DprojectName="$CI_JOB_NAME" \
      -DbrowserStackRun="false" \
      -Dlocale="$LOCALE" \
      -Dfile.encoding=UTF-8 | tee gradlew.log

  set +x

  ./teams_notification.sh "${TEAMS_HOOK_URL}" "Backend" || true
  source "$(dirname "$(realpath "$0")")/_final_steps.sh"