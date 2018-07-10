package eu.imagecode.scias

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._

class AnalysisServiceActor extends Actor with AnalysisService {

  def actorRefFactory = context
  def receive = runRoute(analysisRoute)
}


trait AnalysisService extends HttpService {

  val analysisRoute =
    path("analysis") {
      get {
        val analysisDB = new AnalysisDB()

        respondWithMediaType(`text/html`) {
          complete {
            val analyse = analysisDB.test().get.get
            println(s"from Spray: ${analyse.head}")
            <html>
              <body>
                <h1>SCIAS analysis service {analyse.head._3}</h1>
              </body>
            </html>
          }
        }
      }
    }
}