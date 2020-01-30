package utils

import com.typesafe.scalalogging.Logger

trait LoggerBase {
  lazy val logger = Logger(this.getClass)

  def writeLog(level: String, message: String): Unit = level match {
    case("debug")=> logger.debug(message)
    case("info") => logger.info(message)
    case("error") => logger.error(message)
  }
}
