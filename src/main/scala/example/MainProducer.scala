package example

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging

object MainProducer extends App with StrictLogging {
  val conf = ConfigFactory.load
  val broker = conf.getString("kafka.broker")
  val topic = conf.getString("producer.topic")

  logger.info("Started producing")
  val prod = new Producer(broker)
  prod.write(topic)
}
