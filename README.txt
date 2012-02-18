#-------------------------------------------------------------------------------
# Copyright (c) 2012 Simon Poirier  <simpoir@gmail.com>.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the GNU Public License v3.0
# which accompanies this distribution, and is available at
# http://www.gnu.org/licenses/gpl.html
#-------------------------------------------------------------------------------
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

