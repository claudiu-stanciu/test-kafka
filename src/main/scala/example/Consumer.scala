package example

import java.time.Duration
import java.util.Properties
import scala.collection.JavaConverters._

import com.typesafe.scalalogging.StrictLogging
import org.apache.kafka.clients.consumer.KafkaConsumer

/**
 * Simple Kafka consumer
 * @param brokerUrl
 * @param groupId
 */
class Consumer(brokerUrl: String, groupId: String = "scala-test") extends StrictLogging {

  /**
   * Poll style reader
   * @param topic
   */
  def read(topic: String) = {
    val props = new Properties()
    props.put("bootstrap.servers", brokerUrl)
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "earliest")
    props.put("group.id", groupId)

    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
    consumer.subscribe(List(topic).asJava)
    logger.info("Reading data")
    while (true) {
      val record = consumer.poll(Duration.ofMillis(1000)).asScala
      for (data <- record.iterator)
        logger.info(s"Read message (${data.key()} -> ${data.value()})")
    }
  }
}
