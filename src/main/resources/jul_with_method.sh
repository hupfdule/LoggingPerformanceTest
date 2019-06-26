#!/bin/sh

#create log dir if it doesn't exit
if [ ! -d "logs" ]; then
  mkdir "logs"
fi

JAR_DIR="jars"

ls jul_with_method.properties
time java -cp "jars/*" -Djava.util.logging.config.file=jul_with_method.properties com.github.hupfdule.lpt.LPT 1500000 true false
