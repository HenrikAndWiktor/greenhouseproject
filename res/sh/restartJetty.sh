pkill -9 -f "config_pi"
java -jar ./greenhouse.jar server ./config_pi.yml > greenhouse.log &
