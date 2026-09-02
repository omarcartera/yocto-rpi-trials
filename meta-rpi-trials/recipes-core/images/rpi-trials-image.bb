SUMMARY = "Custom RPi Trials image"
LICENSE = "MIT"

inherit core-image

# Development convenience features (do not use in production images)
IMAGE_FEATURES += " \
    ssh-server-dropbear \
    allow-empty-password \
    empty-root-password \
    allow-root-login \
"

IMAGE_INSTALL += " \
    net-tools \
"
