#!/bin/bash
set -e

source compose-utils.sh

ADDITIONAL_COMPOSE_QUEUE_ARGS=$(additionalComposeQueueArgs) || exit $?

ADDITIONAL_COMPOSE_ARGS=$(additionalComposeArgs) || exit $?

docker-compose -f docker-compose.yml $ADDITIONAL_COMPOSE_ARGS $ADDITIONAL_COMPOSE_QUEUE_ARGS $@
