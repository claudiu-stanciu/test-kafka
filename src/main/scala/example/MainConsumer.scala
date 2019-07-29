package example

import com.typesafe.scalalogging.StrictLogging

object MainConsumer extends App with StrictLogging {
  logger.info("Started consuming")
  val broker = "localhost:9092"
  val cons = new Consumer(broker)
  cons.read("claudiu-test")
}
