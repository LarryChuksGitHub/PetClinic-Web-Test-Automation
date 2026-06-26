#!/bin/bash

message_body='{"text":"'$1'"}'
WEB_HOOK_URL=$2
if [[ -z ${WEB_HOOK_URL} ]]; then
    echo "Channel Webhook hook URL is not set. Skipping sending notification to channel."
else
    curl --max-time 120 -X POST -H 'Content-type: application/json' --data "${message_body}" ${WEB_HOOK_URL}
fi