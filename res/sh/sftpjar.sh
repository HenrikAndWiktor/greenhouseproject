#!/usr/bin/env bash
/usr/bin/sftp pi@192.168.0.112:/home/pi <<< $'put /Users/henrikeriksson/Development/gitrepos/greenhouse/target/greenhouse-1.0-SNAPSHOT.jar /home/pi/greenhouse/greenhouse.jar'