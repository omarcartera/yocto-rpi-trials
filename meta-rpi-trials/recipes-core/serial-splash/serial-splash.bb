SUMMARY = "Serial console ASCII splash screen"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://serial-splash.txt \
    file://serial-splash.sh \
    file://serial-splash.service \
"

inherit systemd update-rc.d

RDEPENDS:${PN} = ""

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${UNPACKDIR}/serial-splash.txt ${D}${sysconfdir}/serial-splash.txt

    install -d ${D}${bindir}
    install -m 0755 ${UNPACKDIR}/serial-splash.sh ${D}${bindir}/serial-splash.sh

    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${UNPACKDIR}/serial-splash.service ${D}${systemd_system_unitdir}/serial-splash.service
    fi

    if ${@bb.utils.contains('DISTRO_FEATURES', 'sysvinit', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/init.d
        install -m 0755 ${UNPACKDIR}/serial-splash.sh ${D}${sysconfdir}/init.d/serial-splash
    fi
}

SYSTEMD_SERVICE:${PN} = "serial-splash.service"

INITSCRIPT_NAME = "serial-splash"
INITSCRIPT_PARAMS = "start 01 S ."

FILES:${PN} += " \
    ${sysconfdir}/serial-splash.txt \
    ${bindir}/serial-splash.sh \
    ${systemd_system_unitdir}/serial-splash.service \
    ${sysconfdir}/init.d/serial-splash \
"
