FILESEXTRAPATHS_prepend := "${THISDIR}/epiphany:"

REQUIRED_DISTRO_FEATURES_remove = "x11"

DEPENDS_remove = "gnome-desktop3"

SRC_URI += " file://fixwayland.patch "