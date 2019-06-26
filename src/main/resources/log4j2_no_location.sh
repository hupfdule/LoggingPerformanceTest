#!/bin/sh

#create log dir if it doesn't exit
if [ ! -d "logs" ]; then
  mkdir "logs"
fi

JAR_DIR="jars"

ls log4j2_no_location.xml
time java -cp "jars/*" -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager -Dlog4j.configurationFile=log4j2_no_location.xml com.github.hupfdule.lpt.LPT 1500000 true false
