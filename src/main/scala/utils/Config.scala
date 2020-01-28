package utils

import java.io.{File, FileInputStream}
import org.yaml.snakeyaml.Yaml

trait Config {

  val yaml = new Yaml()
  val configFile = new FileInputStream(new File("config.yml"))
  val configProps = yaml.load(configFile).asInstanceOf[java.util.Map[String, Any]]

  val host = configProps.get("host").toString
  val port = configProps.get("port").toString.toInt
  val dbName = configProps.get("dbName").toString
  val dbUser = configProps.get("dbUser").toString
  val dbPassword = configProps.get("dbPassword").toString
  val mySqlDriver = configProps.get("mySqlDriver").toString

}
