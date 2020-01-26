package utils
import akka.actor.ActorSystem
import akka.http.caching.LfuCache
import akka.http.caching.scaladsl.{Cache, CachingSettings}
import akka.stream.ActorMaterializer
import scala.concurrent.duration._

trait CacheConfig {
    implicit val actorSystem = ActorSystem("Cache")
    implicit val actorMaterializer = ActorMaterializer()

    val defaultCachingSettings = CachingSettings(actorSystem)
    val lfuCacheSettings =
      defaultCachingSettings.lfuCacheSettings
        .withInitialCapacity(25)
        .withMaxCapacity(50)
        .withTimeToLive(2000.seconds)
        .withTimeToIdle(1000.seconds)

    val cachingSettings =
      defaultCachingSettings.withLfuCacheSettings(lfuCacheSettings)

    val cache: Cache[String, String] = LfuCache(cachingSettings)
}
