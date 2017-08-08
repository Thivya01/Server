#!/usr/bin/env bash

mvn -f ./pom.xml clean install

java -cp ./target/tcpserver-5.jar com.distributedsystem.tcpserver.SampleServer