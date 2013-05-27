# This file is experimental
# It is supposed to generate a Mac .app package
# ONLY run from within the macbuilder folder
# Tested on OS X and Ubuntu variants
# Requires the zip command

# Paul Hollinsky

set -e

build=$1
printf "Building... "
cp -r Template.app CookieIRCv2-$build.app
cp ../CookieIRCv2-$build.jar CookieIRCv2-$build.app/Contents/Resources/Java/CookieIRCv2.jar
zip -FSr ../CookieIRCv2-$build-OSX.zip CookieIRCv2-$build.app > /dev/null
rm -rf CookieIRCv2-$build.app
printf "DONE\n"
