#!/bin/bash
# USAGE
# './launchservices.sh up' brings up network, mysql, and package service
# ./launchservices.sh down' will take down everything
#

#echo "$1";
if [ "$1" = 'up' ]; then
  IS_NETWORK_UP=$(docker network ls | grep package_service_network)
  #echo " network is   $IS_NETWORK_UP"
  if [[ -z $IS_NETWORK_UP ]]; then
    (docker network create package_service_network &)
    (cd mysqlcontainer; docker-compose up &)
    sleep 20
    (cd packageservicecontainer; docker-compose up &)
    sleep 10
  else # network is up but mysql may have gone down on last shutdown if ms were attached to network
    IS_DB_UP=$(docker container ps | grep mysqldb)
    if [[ -z $IS_DB_UP ]]; then
      (cd mysqlcontainer; docker-compose up &)
      sleep 20
    fi
    IS_PACKAGE_SERVICE_UP=$(docker container ps | grep packageservice)
    if [[ -z $IS_PACKAGE_SERVICE_UP ]]; then
      (cd packageservicecontainer; docker-compose up &)
      sleep 10
    fi
  fi

elif [ "$1" = 'down' ]; then
  IS_PACKAGE_SERVICE_UP=$(docker container ps | grep packageservice)
  if [[ -n $IS_PACKAGE_SERVICE_UP ]]; then
    # we are in services, take down dcservice, mockos, ldap, ldapseed
    (cd packageservicecontainer; docker-compose down -t 1 &)
    sleep 10
  fi
  IS_DB_UP=$(docker container ps | grep mysqldb)
  if [[ -n $IS_DB_UP ]]; then
    (cd mysqlcontainer; docker-compose stop -t 1; docker-compose down -t 1 &)
    sleep 15
  fi
  docker network rm package_service_network
fi
