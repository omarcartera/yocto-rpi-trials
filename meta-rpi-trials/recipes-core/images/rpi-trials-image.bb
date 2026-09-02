SUMMARY = "Custom RPi Trials image"
LICENSE = "MIT"

inherit core-image

# Development convenience features (do not use in production images)
IMAGE_FEATURES += " \
    ssh-server-dropbear \
    allow-root-login \
    splash \
"

IMAGE_INSTALL += " \
    net-tools \
    serial-splash \
    psplash-raspberrypi \
"

# Set a known root password (development only)
INHERIT += "extrausers"
EXTRA_USERS_PARAMS = "usermod -P 'changeme' root;"
