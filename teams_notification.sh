#!/bin/bash

WEB_HOOK_URL=$1
title_prefix=$2
buildUrl="https://gitlab.verimi.cloud/verimi-platform/system-test/-/jobs/${CI_JOB_ID}/"
WEB_HOOK_URL_DEV1="https://defaultce570dca850b4b9bb0554dff932712.20.environment.api.powerplatform.com:443/powerautomate/automations/direct/workflows/dc98ee3643fa41f8a92f4a7e91f53aea/triggers/manual/paths/invoke?api-version=1&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=oLJP2PXXKkxv_LDIjMteHUEcYMGTyHr4s3A-tyl8jTI"
WEB_HOOK_URL_DEV2="https://defaultce570dca850b4b9bb0554dff932712.20.environment.api.powerplatform.com:443/powerautomate/automations/direct/workflows/9a7f910de09d423ab4c9ead1ac49462b/triggers/manual/paths/invoke?api-version=1&sp=%2Ftriggers%2Fmanual%2Frun&sv=1.0&sig=jztlMFyZ3aNlz3BJ0oRA4gNfWymSiYczQqmRoomPsh8"

function reportResults() {
  if [[ -f "build/ms-teams-json-message.json" ]]; then
    curl --max-time 120 -X POST -H 'Content-type: application/json' --data "$1" "$2"
  else
    curl -vvv --max-time 120 -X POST -H 'Content-type: application/json' --data '{"text":"Build '"$3"' failed"}' "$2"
  fi
}

if [[ -f "build/ms-teams-json-message.json" ]]; then
  message_body=$(cat build/ms-teams-json-message.json)
  message_body="${message_body/$\{buildUrl\}/$buildUrl}"
  message_body="${message_body/$\{titlePrefix\}/$title_prefix}"
fi

if [[ -z ${WEB_HOOK_URL} ]]; then
  echo "Webhook URL not set. Posting results on env. channel on which tests were run"
  if [[ (${TEST_ENV} == "dev1") ]]; then
    reportResults "${message_body}" "${WEB_HOOK_URL_DEV1}" "${buildUrl}"
  elif [[ (${TEST_ENV} == "dev2") ]]; then
    reportResults "${message_body}" "${WEB_HOOK_URL_DEV2}" "${buildUrl}"

  else
    echo "Please set Webhook URL for ${TEST_ENV}"
  fi
else
  reportResults "${message_body}" "${WEB_HOOK_URL}" "${buildUrl}"
fi