sudo apt update

#INSTALL APACHE
sudo apt install apache2 -y

#ACTIVATE modproxy
sudo a2enmod proxy
sudo a2enmod proxy_http
sudo a2enmod proxy_ajp
sudo a2enmod rewrite
sudo a2enmod deflate
sudo a2enmod headers
sudo a2enmod proxy_balancer
sudo a2enmod proxy_connect
sudo a2enmod proxy_html

#INSTALL GPIO
sudo apt-get install wiringpi

#INSTALL GIT
sudo apt-get install git-core

#INSTALL 433 Utils
git clone --recursive git://github.com/ninjablocks/433Utils.git
cd 433Utils/RPi_utils
make

#INSTALL java
sudo apt install openjdk-8-jdk
java -version

##MANUELLT
#sudo vi /etc/ssh/sshd_config
#Ändra till: PasswordAuthentication yes
#pi@FarsansRPi4:~ $ sudo service ssh restart

# Reverse proxy
#pi@FarsansRPi4:~ $ more /etc/apache2/sites-enabled/000-default.conf 
#<VirtualHost *:80>
#............................
#...... och sen->
#	ProxyPass "/greenhousestatus" "http://0.0.0.0:8080/greenhousestatus"
#    	ProxyPassReverse "/greenhousestatus" "http://0.0.0.0:8080/greenhousestatus"	
#</VirtualHost>

searchString="greenhouse"
file="/etc/apache2/sites-enabled/000-default.conf"
if grep -q "$searchString" $file
    then
            echo "ProxyPass for greenhouse found in $file"
    else
            echo "Create ProxyPass for greenhouse in $file"
 	   sudo sed '/VirtualHost>/i\	ProxyPass "/greenhousestatus" "http://0.0.0.0:8080/greenhousestatus"' $file > tmpfile
           sudo sed '/VirtualHost>/i\	ProxyPassReverse "/greenhousestatus" "http://0.0.0.0:8080/greenhousestatus"' tmpfile > tmpfile2
	   sudo cp tmpfile2 $file
	   sudo rm tmpfile*
           sudo service apache2 restart
fi


# starta om apache


#
#Installera Termometer
# Enable 1-Wire interface with the raspi-config tool.
#
#sudo raspi-config
# Startar en meny. Leta upp 1-Wire -> <Yes>
#
# Lägg till dtoverlay=w1–gpio i /boot/config.txt
# sudo reboot
# sudo modprobe w1–gpio
# sudo modprobe w1-therm
# sudo reboot
# Sen bör en katalog/fil ha skapats i /sys/bus/w1/devices/28......
# Lägg till det i config_pi.yml

#Installera vattentankkontroll och jordsensorkontroll
# https://www.raspberrypi.org/documentation/configuration/config-txt/gpio.md
# På pi-4 behövs detta:
# i /boot/config.txt lägg till:
# gpio=16=pu i pi-3 styrdes det via koden. Funkar inte nu längre.
# gpio=24=pd i pi-3 styrdes det via koden. Funkar inte nu längre.
# sudo reboot