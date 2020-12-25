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