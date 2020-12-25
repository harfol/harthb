#!/bin/bash
function additionalComposeArgs() {
    source .env
    ADDITIONAL_COMPOSE_ARGS=""
    case $DATABASE in
        postgres)
        ADDITIONAL_COMPOSE_ARGS="-f docker-compose.postgres.yml"
        ;;
        hybrid)
        ADDITIONAL_COMPOSE_ARGS="-f docker-compose.hybrid.yml"
        ;;
        *)
        echo "Unknown DATABASE value specified: '${DATABASE}'. Should be either postgres or hybrid." >&2
        exit 1
    esac
    echo $ADDITIONAL_COMPOSE_ARGS
}

function additionalComposeQueueArgs() {
    source .env
    ADDITIONAL_COMPOSE_QUEUE_ARGS=""
    case $TB_QUEUE_TYPE in
        kafka)
        ADDITIONAL_COMPOSE_QUEUE_ARGS="-f docker-compose.kafka.yml"
        ;;
        confluent)
        ADDITIONAL_COMPOSE_QUEUE_ARGS="-f docker-compose.confluent.yml"
        ;;
        aws-sqs)
        ADDITIONAL_COMPOSE_QUEUE_ARGS="-f docker-compose.aws-sqs.yml"
        ;;
        pubsub)
        ADDITIONAL_COMPOSE_QUEUE_ARGS="-f docker-compose.pubsub.yml"
        ;;
        rabbitmq)
        ADDITIONAL_COMPOSE_QUEUE_ARGS="-f docker-compose.rabbitmq.yml"
        ;;
        service-bus)
        ADDITIONAL_COMPOSE_QUEUE_ARGS="-f docker-compose.service-bus.yml"
        ;;
        *)
        echo "Unknown Queue service value specified: '${TB_QUEUE_TYPE}'. Should be either kafka or confluent or aws-sqs or pubsub or rabbitmq or service-bus." >&2
        exit 1
    esac
    echo $ADDITIONAL_COMPOSE_QUEUE_ARGS
}

function additionalStartupServices() {
    source .env
    ADDITIONAL_STARTUP_SERVICES=""
    case $DATABASE in
        postgres)
        ADDITIONAL_STARTUP_SERVICES=postgres
        ;;
        hybrid)
        ADDITIONAL_STARTUP_SERVICES="postgres cassandra"
        ;;
        *)
        echo "Unknown DATABASE value specified: '${DATABASE}'. Should be either postgres or hybrid." >&2
        exit 1
    esac
    echo $ADDITIONAL_STARTUP_SERVICES
}
