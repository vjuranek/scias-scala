package eu.imagecode.scias

import slick.jdbc.H2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

class AnalysisDB {

  def test(): Option[Try[Seq[(Int, Int, String)]]] = {
    val db = Database.forConfig("h2mem")

    try {
      val analyses = TableQuery[AnalysisEntity]
      val setup = DBIO.seq(
        analyses.schema.create,
        analyses += (1, 10, "test sample 1"),
        analyses += (2, 11, "test sample 2"),
        analyses += (3, 12, "test sample 3")
      )
      val setupFuture = db.run(setup)
      Await.result(setupFuture, Duration.Inf)

      val queryFuture = db.run(analyses.filter(_.id === 2).result)
      Await.result(queryFuture, Duration.Inf)
      queryFuture.value
    } finally db.close
  }
}

class AnalysisEntity(tag: Tag) extends Table[(Int, Int, String)](tag, "ANALYSIS") {
  def id = column[Int]("id", O.PrimaryKey)

  def localId = column[Int]("local_id")

  def sample = column[String]("sample")

  def * = (id, localId, sample)
}