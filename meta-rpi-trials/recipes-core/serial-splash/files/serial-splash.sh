#!/bin/sh
# Print the project ASCII splash to the kernel console just before login.

if [ -r /etc/serial-splash.txt ]; then
    # Use /dev/console so output follows the kernel's console= setting.
    {
        echo ""
        cat /etc/serial-splash.txt
        echo ""
    } >/dev/console 2>/dev/null || true
fi
