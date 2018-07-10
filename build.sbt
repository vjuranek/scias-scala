organization  := "eu.imagecode"

name := "scias-scala"

version := "0.1"

scalaVersion := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaVersion = "2.3.9"
  val sprayVersion = "1.3.3"
  val slickVersion = "3.2.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayVersion,
    "io.spray"            %%  "spray-routing" % sprayVersion,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaVersion,
    "com.typesafe.slick"  %% "slick" % slickVersion,
    "com.typesafe.slick"  %% "slick-hikaricp" % slickVersion,
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.h2database" % "h2" % "1.4.197",
  //test depts
    "io.spray"            %%  "spray-testkit" % sprayVersion  % "test",
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaVersion   % "test"
  )
}

Revolver.settings

