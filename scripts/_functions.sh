
esc=$(printf '\033')
red="${esc}[1;31m"
green="${esc}[1;32m"
yellow="${esc}[1;33m"
nocolor="${esc}[0m"

title() {
    echo
    echo "${green}>>> $@${nocolor}"
}

strong() {
    echo "${yellow}=== $@${nocolor}"
}

err() {
    echo "${red}=== ERROR: $@${nocolor}"
    echo
    exit 1
}

