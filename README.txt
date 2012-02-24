REQUIREMENTS
============

 - apache maven v2+
 - java jdk v6+ (developped against icedtea)


INSTALLING
==========

 # mvn clean package
 # cp target/elixy.war /path/to/tomcat webapps
 # cp example-elixy.properties /etc/elixy.properties
 edit the config
 restart tomcat


TESTING
=======

The provided maven config has an embedded tomcat config for your pleasure

 # mvn tomcat:run
 Then navigate to http://localhost:8080/elixy

