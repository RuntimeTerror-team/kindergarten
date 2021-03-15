#!/bin/sh
sh build.sh
mvn clean tomcat7:redeploy -Dserver=TomcatServer