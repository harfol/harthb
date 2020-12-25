#!/bin/bash
start-db.sh

CONF_FOLDER="${pkg.installFolder}/conf"
jarfile=${pkg.installFolder}/bin/${pkg.name}.jar
configfile=${pkg.name}.conf
upgradeversion=${DATA_FOLDER}/.upgradeversion

source "${CONF_FOLDER}/${configfile}"

FROM_VERSION=`cat ${upgradeversion}`

echo "Starting ThingsBoard upgrade ..."

if [[ -z "${FROM_VERSION// }" ]]; then
    echo "FROM_VERSION variable is invalid or unspecified!"
    exit 1
else
    fromVersion="${FROM_VERSION// }"
fi

java -cp ${jarfile} $JAVA_OPTS -Dloader.main=org.thingsboard.server.ThingsboardInstallApplication \
                -Dspring.jpa.hibernate.ddl-auto=none \
                -Dinstall.upgrade=true \
                -Dinstall.upgrade.from_version=${fromVersion} \
                -Dlogging.config=/usr/share/thingsboard/bin/install/logback.xml \
                org.springframework.boot.loader.PropertiesLauncher

echo "${pkg.upgradeVersion}" > ${upgradeversion}

stop-db.sh