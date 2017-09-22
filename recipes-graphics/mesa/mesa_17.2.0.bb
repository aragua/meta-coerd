require recipes-graphics/mesa/${BPN}.inc

SRC_URI = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz \
           file://replace_glibc_check_with_linux.patch \
           file://disable-asm-on-non-gcc.patch \
           file://0001-Use-wayland-scanner-in-the-path.patch \
           file://0002-hardware-gloat.patch \
           file://vulkan-mkdir.patch \
           file://llvm-config-version.patch \
           file://0001-winsys-svga-drm-Include-sys-types.h.patch \
           file://0001-configure.ac-Always-check-for-expat.patch \
           "
SRC_URI[md5sum] = "1d8d7323d11146767a37c3cb6a6d411d"
SRC_URI[sha256sum] = "3123448f770eae58bc73e15480e78909defb892f10ab777e9116c9b218094943"

DEPENDS += " llvm-native wayland-protocols "

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
