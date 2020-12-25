#!/bin/bash
for i in "$@"
do
case $i in
    --fromVersion=*)
    FROM_VERSION="${i#*=}"
    shift
    ;;
    *)
            # unknown option
    ;;
esac
done

if [[ -z "${FROM_VERSION// }" ]]; then
    echo "--fromVersion parameter is invalid or unspecified!"
    echo "Usage: docker-upgrade-tb.sh --fromVersion={VERSION}"
    exit 1
else
    fromVersion="${FROM_VERSION// }"
fi

set -e

source compose-utils.sh

ADDITIONAL_COMPOSE_QUEUE_ARGS=$(additionalComposeQueueArgs) || exit $?

ADDITIONAL_COMPOSE_ARGS=$(additionalComposeArgs) || exit $?

ADDITIONAL_STARTUP_SERVICES=$(additionalStartupServices) || exit $?

docker-compose -f docker-compose.yml $ADDITIONAL_COMPOSE_ARGS $ADDITIONAL_COMPOSE_QUEUE_ARGS pull tb-core1

docker-compose -f docker-compose.yml $ADDITIONAL_COMPOSE_ARGS $ADDITIONAL_COMPOSE_QUEUE_ARGS up -d redis $ADDITIONAL_STARTUP_SERVICES

docker-compose -f docker-compose.yml $ADDITIONAL_COMPOSE_ARGS $ADDITIONAL_COMPOSE_QUEUE_ARGS run --no-deps --rm -e UPGRADE_TB=true -e FROM_VERSION=${fromVersion} tb-core1
