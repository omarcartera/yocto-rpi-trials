# Yocto Raspberry Pi Trials

## Quick Start

```sh
git clone --recursive <repo-url> yocto-rpi-trials
cd yocto-rpi-trials

# Set up the build environment (sources oe-init-build-env with the rpi-trials template)
. ./setup-environment

# Build the image
bitbake rpi-trials-image
```

## Repository Layout

- `openembedded-core/` – OpenEmbedded Core (git submodule)
- `bitbake/` – BitBake build tool (git submodule)
- `meta-yocto/` – Poky reference distro and BSP (git submodule)
- `meta-openembedded/` – OpenEmbedded add-on layers (git submodule)
- `meta-raspberrypi/` – Raspberry Pi BSP layer (git submodule)
- `meta-rpi-trials/` – Project-specific layer (distro config, image recipe, build templates)
- `build/` – Generated build directory (not version-controlled)

## Configuration Management

Project-wide build settings are tracked in git instead of the untracked
`build/conf/` files:

- `meta-rpi-trials/conf/distro/rpi-trials.conf` – distro configuration
  (`DISTRO`, `LICENSE_FLAGS_ACCEPTED`, etc.)
- `meta-rpi-trials/recipes-core/images/rpi-trials-image.bb` – custom image
  (image features, `IMAGE_INSTALL`)
- `meta-rpi-trials/conf/templates/rpi3-64/` – `TEMPLATECONF` templates for
  `local.conf` and `bblayers.conf`

The generated `build/conf/local.conf` and `build/conf/bblayers.conf` are
created from the tracked templates by `setup-environment`. To change project
settings, edit the source template or the layer files, not the generated
`build/conf/` files.

For host-specific overrides (e.g. `DL_DIR`, `SSTATE_DIR`,
`BB_NUMBER_THREADS`), create `build/conf/site.conf` — BitBake sources it
automatically.
