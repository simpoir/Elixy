Elixy is a work in progress and mostly broken/incomplete/insecure.
Seriously, don't use it for now.

REQUIREMENTS
============

* apache maven v2+
* java jdk v6+ (developped against icedtea)


INSTALLING
==========

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

 # mvn -P test tomcat:run-war

Then navigate to http://localhost:8080/elixy

