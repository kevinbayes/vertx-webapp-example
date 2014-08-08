package me.bayes.webapp

import org.vertx.scala.core.AsyncResult
import org.vertx.scala.core.http.{HttpServerRequest, RouteMatcher, HttpServer}
import org.vertx.scala.core.json.JsonObject
import org.vertx.scala.platform.Verticle

/**
 * Created by kevinbayes on 2014/08/08.
 */
class WebApp extends Verticle {

  override def start() {
    val LOG = container.logger

    LOG.info("Starting web app verticle.")
    val config: JsonObject = container.config

    val server = vertx.createHttpServer()

    val routeMatcher = RouteMatcher()

    routeMatcher.get("/animals/dogs", { req: HttpServerRequest =>
      req.response().end("You requested dogs")
    })

    server.requestHandler(routeMatcher).listen(8080, "localhost", { asyncResult: AsyncResult[HttpServer] =>
      logger.info("Listen succeeded? " + asyncResult.succeeded())
    })
  }
}
