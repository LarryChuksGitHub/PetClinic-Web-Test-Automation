#!/bin/bash - 
#===============================================================================
#
#          FILE: jenkins-pages.sh
# 
#         USAGE: ./jenkins-pages.sh
# 
#   DESCRIPTION: 
# 
#       OPTIONS: ---
#  REQUIREMENTS: ---
#          BUGS: ---
#         NOTES: ---
#        AUTHOR: YOUR NAME (), 
#  ORGANIZATION: 
#       CREATED: 12/05/19 13:31
#      REVISION:  ---
#===============================================================================

set -o nounset                              # Treat unset variables as an error

TARGET=$(echo "public/${TEST_ENV}/${CI_JOB_ID}")
mkdir -p $TARGET
echo "copy files to ${TARGET}"

if [ -d "build/test-report" ];then
  echo "found test-report folder, creating test report"
  cp -r build/test-report/screenshots "${TARGET}/"
  cp -r build/test-report/test-report.html "${TARGET}/index.html"
else
  echo "no rest-report folder found, using fallback test report"
  sed -i "s|PLACEHOLDER|${CI_JOB_ID}|g" templates/pipeline-failed.html
  cp templates/pipeline-failed.html "${TARGET}/index.html"
fi

echo "pipeline report is available on https://verimi-platform.gitlab.verimi.cloud/system-test/${TEST_ENV}/${CI_JOB_ID}/"

