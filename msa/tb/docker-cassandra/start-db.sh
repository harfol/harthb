#!/bin/bash
firstlaunch=${DATA_FOLDER}/.firstlaunch

PG_CTL=$(find /usr/lib/postgresql/ -name pg_ctl)

if [ ! -d ${PGDATA} ]; then
    mkdir -p ${PGDATA}
    ${PG_CTL} initdb
fi

exec setsid nohup postgres >> ${PGLOG}/postgres.log 2>&1 &

if [ ! -f ${firstlaunch} ]; then
    sleep 2
    while ! psql -U ${pkg.user} -d postgres -c "CREATE DATABASE thingsboard"
    do
      sleep 1
    done
fi

cassandra_data_dir=${CASSANDRA_DATA}
cassandra_data_link=/var/lib/cassandra

if [ ! -L ${cassandra_data_link} ]; then
    if [ ! -d ${cassandra_data_dir} ]; then
        mkdir -p ${cassandra_data_dir}
    fi
    ln -s ${cassandra_data_dir} ${cassandra_data_link}
fi

exec setsid nohup cassandra >> ${CASSANDRA_LOG}/cassandra.log 2>&1 &

until nmap $CASSANDRA_HOST -p $CASSANDRA_PORT | grep "$CASSANDRA_PORT/tcp open"
do
  echo "Wait for cassandra db to start..."
  sleep 5
done
