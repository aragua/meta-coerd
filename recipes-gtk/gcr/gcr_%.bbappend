FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

REQUIRED_DISTRO_FEATURES_remove = "x11"

SRC_URI += " file://withoutx11.patch "
