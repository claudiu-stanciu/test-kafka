package example

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging

object MainConsumer extends App with StrictLogging {

  val conf = ConfigFactory.load
  val broker = conf.getString("kafka.broker")
  val topic = conf.getString("consumer.topic")
  val groupId = conf.getString("consumer.group-id")

  logger.info("Started consuming")
  val cons = new Consumer(broker, groupId)
  cons.read(topic)
}
