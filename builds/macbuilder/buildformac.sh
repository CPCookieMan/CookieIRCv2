# This file is experimental
# It is supposed to generate a Mac .app package
# ONLY run from within the macbuilder folder
# Tested on OS X and Ubuntu variants
# Requires the zip command

set -e

read -p "Please enter the build number: " build
./buildformacargs.sh $build
