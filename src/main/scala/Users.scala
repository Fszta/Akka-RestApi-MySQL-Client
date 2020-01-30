import spray.json.DefaultJsonProtocol._
import spray.json._

trait Users {
  case class User(id: Int, firstname: String, lastname: String, email: String)
  /**
    * Convert a list of User class to JsonString format
    * @param
    * @return
    */
  def userListToJson(users: List[User]): String = {
    implicit val userJsonFormat = jsonFormat4(User)
    val jsonString = users.toJson.toString
    jsonString
  }
}
