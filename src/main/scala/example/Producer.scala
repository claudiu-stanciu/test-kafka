package example

import java.util.Properties

import com.typesafe.scalalogging.StrictLogging
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

class Producer(brokerUrl: String) extends StrictLogging {
  val props = new Properties()
  props.put("bootstrap.servers", brokerUrl)
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  val producer = new KafkaProducer[String, String](props)

  def write(topic: String): Unit = {
    import scala.io.StdIn
    logger.info("Type to insert new message into Kafka. Exit on ^C")
    while (true) {
      val data = StdIn.readLine()
      val key = scala.util.Random.nextInt
      val record = new ProducerRecord[String, String](topic, key.toString, data)
      logger.info(s"Wrote message ($key -> $data)")
      producer.send(record)
      producer.flush()
    }
  }
}

