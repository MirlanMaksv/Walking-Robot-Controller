#!/usr/bin/env bash
#
# script to connect android device and computer through common wifi
#

ip=192.168.0.$1
port=5555
res=$(adb devices -l)
if [[ ${res} = *"device"* ]]; then
    (adb tcpip ${port})
    if [ $? -eq 0 ]; then

        res=$(adb connect ${ip})
        if [[ ${res} = "connected to ${ip}:${port}" ]]; then
            echo "Connected!"
        else
            echo "Failed!"
        fi
    fi
fi