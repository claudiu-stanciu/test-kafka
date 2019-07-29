# Kafka consumer and producer sample code

- Kafka  2.x+

## Setup kafka

- Run the following [docker-compose](https://github.com/confluentinc/cp-docker-images/blob/master/examples/kafka-single-node/docker-compose.yml)

- Depending on your docker setup (windows docker toolbox/VM), you might need to expose the port `9092`

- Check the [app config](src/main/resources/application.conf) to configure
  - kafka endpoint
  - topic
  - (consumer) group-id

## Build and run
- Main

```sbt clean run```

- Run consumer. Continuously read data

```sbt "runMain example.MainConsumer"```

- Run producer. Data is read from std in and send to kafka

```sbt "runMain example.MainProducer"```