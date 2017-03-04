This project is dead. I recommend switching to LXD which has nice tooling and remote control.

Seriously, don't use this.

REQUIREMENTS
============

* apache maven v2+
* java jdk v6+ (developped against icedtea)


INSTALLING
==========

The app needs to run as root, which makes it awfully insecure.
This is one of the first reason why you should not use it.

In a shell::

 # mvn clean package -P standalone
 # java -jar elixy/target/elixy-0.0.1-SNAPSHOT-war-exec.jar

Then navigate to http://localhost:8080/elixy

The generated jar file supports arguments. see -help


OLD INSTALLATION PROCEDURE
==========================

Note this does not work anymore as sudo does not work with liblxc binding.

In a shell::

 # mvn clean package
 # cp target/elixy.war /path/to/tomcat webapps
 # cp example-elixy.properties /etc/elixy.properties

edit the config
restart tomcat

tomcat will also need some admin rights which are provided by visudo config::

 tomcat6 ALL=NOPASSWD:/usr/bin/lxc-info,/usr/bin/lxc-start,/usr/bin/lxc-stop,/usr/bin/lxc-console


TESTING
=======

The provided maven config has an embedded tomcat config for your pleasure::

 # mvn -P test tomcat:run

Then navigate to http://localhost:8080/elixy


