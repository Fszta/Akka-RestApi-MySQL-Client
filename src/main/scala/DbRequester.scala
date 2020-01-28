import java.sql.{Connection, DriverManager}
import akka.actor.ActorSystem
import Client.{selectFromTable, tableContentToJson}
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import utils.{Config, Logger}

object DbRequester extends Logger with Config {

  implicit lazy val actorSystem = ActorSystem("RDBMS")
  implicit lazy val executionContext = actorSystem.dispatcher

  val url = "jdbc:mysql://"+host+":3306"+"/"+dbName
  var connection: Connection = _

  /**
   * Get json data from sql table
   * @param tableName
   * @return
   */
  def getData(tableName: String): Future[String] = Future {
    Try(connection = DriverManager.getConnection(url, dbUser, dbPassword)) match {
      case Success(_) =>
        writeLog("info", s"Connected to $dbUser")
      case Failure(e) =>
        writeLog("error", s"fail to connect to $dbUser : $e")
    }
    val selectResult = selectFromTable(connection, tableName)
    val jsonData = tableContentToJson(selectResult)
    jsonData
  }
}
