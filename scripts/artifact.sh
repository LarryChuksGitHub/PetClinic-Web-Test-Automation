#!/bin/sh

set -e

. $(dirname $(realpath $0))/_functions.sh

BASE_VERSION=${BASE_VERSION:-"0.1"}
REPO_PATH="https://artifactory.tools.verimi.cloud/artifactory/generic/system-test/"

title "Preparing version ..."
suf=
if [ "$CI_COMMIT_BRANCH" != "$CI_DEFAULT_BRANCH" ]; then
    suf=.$(echo "$CI_COMMIT_BRANCH" | sed 's/[^a-zA-Z0-9\.]/./g' | sed -E 's/\.\.+/./g')
fi
version="${BASE_VERSION}.$(date +%y%m%d).${CI_JOB_ID}-${CI_COMMIT_SHORT_SHA}${suf}"
strong "VERSION = $version"

title "Generating artifact ..."
archive_name=${version}.tgz
tar -cvzf ../${archive_name} --exclude=.git/* ./
mv -v ../${archive_name} ${archive_name}
ARTIFACT_MD5_CHECKSUM=$(md5sum ${archive_name} | awk '{print $1}')
ARTIFACT_SHA1_CHECKSUM=$(shasum -a 1 ${archive_name} | awk '{ print $1 }')

title "Uploading artifact ..."
#export VAULT_TOKEN="$( vault write -field=token auth/tools/id_token_jwt/gitlab/login role=$VAULT_ROLE jwt=$ID_TOKEN_4_VAULT )"
#export JFROG_API_TOKEN="$(vault kv get -field=reference_token kv/tools/artifactory/personal/artifactory_write)"
curl -sSf -X PUT -T ${archive_name} \
    --header "X-JFrog-Art-Api:$JFROG_API_TOKEN" \
    --header "X-Checksum-MD5:$ARTIFACT_MD5_CHECKSUM" \
    --header "X-Checksum-Sha1:$ARTIFACT_SHA1_CHECKSUM" \
    "$REPO_PATH"
echo

strong "DONE. VERSION = $version"

