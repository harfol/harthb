const config = require('config'), logger = require('./config/logger')._logger('main');

const serviceType = config.get('queue_type');
switch (serviceType) {
    case 'kafka':
        logger.info('Starting kafka template.');
        require('./queue/kafkaTemplate');
        logger.info('kafka template started.');
        break;
    case 'pubsub':
        logger.info('Starting Pub/Sub template.')
        require('./queue/pubSubTemplate');
        logger.info('Pub/Sub template started.')
        break;
    case 'aws-sqs':
        logger.info('Starting Aws Sqs template.')
        require('./queue/awsSqsTemplate');
        logger.info('Aws Sqs template started.')
        break;
    case 'rabbitmq':
        logger.info('Starting RabbitMq template.')
        require('./queue/rabbitmqTemplate');
        logger.info('RabbitMq template started.')
        break;
    case 'service-bus':
        logger.info('Starting Azure Service Bus template.')
        require('./queue/serviceBusTemplate');
        logger.info('Azure Service Bus template started.')
        break;
    default:
        logger.error('Unknown service type: ', serviceType);
        process.exit(-1);
}

