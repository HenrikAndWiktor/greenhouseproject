#!/usr/bin/env bash
echo "create work directory on pi"
/usr/bin/ssh pi@192.168.1.131 "mkdir -p /home/pi/greenhouse"
echo "create work directory on pi DONE"
/usr/bin/sftp pi@192.168.1.131:/home/pi <<< $'put /Users/heer2737/develop/gitrepos/greenhouseproject/target/greenhouse-1.0-SNAPSHOT-shaded.jar /home/pi/greenhouse/greenhouse.jar'
#/usr/bin/sftp pi@192.168.1.131:/home/pi <<< $'put /Users/heer2737/develop/gitrepos/greenhouseproject/res/sh/restartJetty.sh /home/pi/greenhouse/restartJetty.sh'
#/usr/bin/sftp pi@192.168.1.131:/home/pi <<< $'put /Users/heer2737/develop/gitrepos/greenhouseproject/config_pi.yml /home/pi/greenhouse/config_pi.yml'