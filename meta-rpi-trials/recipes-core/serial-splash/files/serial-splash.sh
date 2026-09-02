#!/bin/sh
# Print the project ASCII splash to the serial boot console.

if [ -r /etc/serial-splash.txt ]; then
    # Clear the screen, then print the logo.
    printf '\033[2J\033[H' >/dev/ttyS0 2>/dev/null || true
    cat /etc/serial-splash.txt >/dev/ttyS0 2>/dev/null || true
fi
