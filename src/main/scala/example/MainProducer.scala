package example

import com.typesafe.scalalogging.StrictLogging

object MainProducer extends App with StrictLogging {
  logger.info("Started producing")
  val broker = "localhost:9092"
  val prod = new Producer(broker)
  prod.write("claudiu-test")
}
