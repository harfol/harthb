#!/bin/bash

echo "Starting '${project.name}' ..."

CONF_FOLDER="${pkg.installFolder}/conf"

mainfile=${pkg.installFolder}/bin/${pkg.name}
configfile=${pkg.name}.conf
identity=${pkg.name}

source "${CONF_FOLDER}/${configfile}"

cd ${pkg.installFolder}/bin

exec /bin/sh -c "$mainfile"
