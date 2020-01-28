import scala.util.{Failure, Success}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.{ContentType, HttpEntity,MediaTypes, StatusCodes}
import DbRequester.getData
import utils.CacheConfig


object Controller extends CacheConfig {

  lazy val dbRoutes: Route = pathPrefix("database") {
    get {
      path("get") {
        parameters("tableName") { tableName =>
          onComplete(cache.getOrLoad(tableName, _ => getData(tableName))) {
            case Success(jsonData) =>
              complete(StatusCodes.OK, HttpEntity(ContentType(MediaTypes.`application/json`), jsonData))
            case Failure(_) =>
              complete(StatusCodes.BadRequest)
          }
        }
      }
    }
  }
}