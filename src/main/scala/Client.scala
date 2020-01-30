import java.sql.{Connection, ResultSet}
import scala.collection.mutable.ListBuffer
import scala.util.{Failure, Success, Try}
import utils.{Config, LoggerBase}


object Client extends App with Config with LoggerBase with Users {

  /**
    * Extract content of sql table
    *
    * @param connection java sql connection
    * @param tableName
    * @return
    */
  def selectFromTable(connection: Connection, tableName: String): ResultSet = {
    val statement = connection.createStatement
    val result = statement.executeQuery(s"SELECT * FROM $tableName")
    result
  }

  /**
    * List  existing tables in database
    *
    * @param connection Java SQL Connection
    */
  def listTables(connection: Connection): List[String] = {
    val existingTables = ListBuffer[String]()
    val metadata = connection.getMetaData
    val tables = metadata.getTables(null, null, "%", null)

    while (tables.next) {
      existingTables += tables.getString(3)
    }

    existingTables.toList
  }

  /**
    * Create new table in mysql database
    *
    * @param connection
    */
  def createTable(connection: Connection): Unit = {
    val statement = connection.createStatement

    Try {
      statement.executeUpdate(
        "CREATE TABLE users_info " +
          "(id INTEGER not NULL AUTO_INCREMENT, " +
          " firstname VARCHAR(255), " +
          " lastname VARCHAR(255), " +
          " email VARCHAR(255)," +
          " PRIMARY KEY ( id ))")
    } match {
      case Success(_) =>
        println("Successfully create new table")
      case Failure(e) =>
        println(s"Fail to create new table : $e")
    }
  }

  /**
    * Convert content of sql database to JsonString format
    *
    * @param res
    * @return
    */
  def tableContentToJson(res: ResultSet): String = {
    val users = scala.collection.mutable.ListBuffer[User]()

    while (res.next) {
      users += User(
        res.getInt("id"),
        res.getString("firstname"),
        res.getString("lastname"),
        res.getString("email")
      )
    }
    val jsonString = userListToJson(users.toList)

    jsonString
  }




  /**
    * Insert a new user in users_info Table
    *
    * @param connection
    * @param user
    */
  def insertIntoTable(connection: Connection, user: User): Unit = {
    val statement = connection.createStatement
    val firstname = user.firstname
    val lastname = user.lastname
    val email = user.email

    statement.executeUpdate(
      s"INSERT INTO users_info " +
        s"(firstname,lastname,email) " +
        s"VALUES('$firstname','$lastname','$email');")
  }
}
