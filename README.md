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

## Serial Boot Console

The image is configured with `ENABLE_UART = "1"`, so the serial console is
enabled by default for boot messages and a login shell.

### Wiring

You need a 3.3 V USB-to-TTL serial adapter. Connect it to the Raspberry Pi 3
GPIO header:

| Pi GPIO pin | Pi function | Adapter |
|-------------|-------------|---------|
| 6           | GND         | GND     |
| 8           | TXD         | RXD     |
| 10          | RXD         | TXD     |

### Host setup

Find the adapter's device node, then open it at 115200 baud:

```sh
ls /dev/ttyUSB* /dev/ttyACM*
picocom -b 115200 /dev/ttyUSB0
```

Or with `screen`:

```sh
screen /dev/ttyUSB0 115200
```

Power on the Raspberry Pi and the boot log will appear on the host terminal.

Note: enabling UART fixes the Pi's core clock, which can affect Bluetooth
performance on Raspberry Pi 3 because the Bluetooth module shares the mini-UART.
For development debugging this is usually acceptable.

## Boot Splash Screens

The image includes two optional boot branding elements:

- **Display splash** — a framebuffer image shown on the HDMI/display during
  userspace boot, provided by `psplash`. The image source is
  `meta-rpi-trials/recipes-core/psplash/files/psplash-rpi-trials-img.png`.
- **Serial console splash** — ASCII art printed to `/dev/ttyS0` very early in
  boot. The source text is
  `meta-rpi-trials/recipes-core/serial-splash/files/serial-splash.txt`.

To change either splash, replace the corresponding file in the layer and
rebuild `rpi-trials-image`.
