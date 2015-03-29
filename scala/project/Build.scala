import sbt._
import sbt.Keys._

object Build extends Build {

  override lazy val settings =
    super.settings ++ Seq(shellPrompt := { s => Project.extract(s).currentProject.id + " > " })

  lazy val root =
    Project("root", file("."))
      .aggregate(votewatch)
      .aggregate(votedb)

  lazy val votedb =
    Project("votedb", file("votedb")).settings(
      name := "votedb",
      organization := "votedb",
      version := "0.0.0",
      scalaVersion := "2.10.5",
      fork := true,
      scalacOptions ++=
        Seq("-deprecation",
          "-unchecked",
          "-Yinline-warnings",
          "-language:implicitConversions",
          "-language:reflectiveCalls",
          "-language:postfixOps",
          "-language:existentials",
          "-feature"),
      resolvers ++=
        Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
            "bmjames Bintray Repo" at "https://dl.bintray.com/bmjames/maven",
            Resolver.sonatypeRepo("public")),
      libraryDependencies ++=
        Seq(
          // TESTING
          "org.scalatest"   %% "scalatest"    % "2.2.4",
          "org.scalacheck"  %% "scalacheck"   % "1.12.2",

          // SLICK DB
          "com.typesafe.slick" %% "slick" % "2.1.0",
          "org.slf4j" % "slf4j-nop" % "1.6.4",
          "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",

          // UTILS
          "com.github.nscala-time" %% "nscala-time" % "1.8.0",
          "com.azavea.geotrellis" %% "geotrellis" % "0.10.0-SNAPSHOT"
        )
    )

  lazy val votewatch =
    Project("votewatch", file("votewatch")).settings(
      name := "vote",
      organization := "votewatch",
      version := "0.0.0",
      scalaVersion := "2.10.5",
      fork := true,
      scalacOptions ++=
        Seq("-deprecation",
          "-unchecked",
          "-Yinline-warnings",
          "-language:implicitConversions",
          "-language:reflectiveCalls",
          "-language:postfixOps",
          "-language:existentials",
          "-feature"),
      resolvers ++=
        Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
            "bmjames Bintray Repo" at "https://dl.bintray.com/bmjames/maven",
            Resolver.sonatypeRepo("public")),
      libraryDependencies ++=
        Seq(
          // TESTING
          "org.scalatest"   %% "scalatest"    % "2.2.4",
          "org.scalacheck"  %% "scalacheck"   % "1.12.2",

          // SPRAY
          "io.spray" % "spray-routing" % "1.3.1",
          "io.spray" % "spray-can" % "1.3.1",
          "io.spray" % "spray-client" % "1.3.1",
          "io.spray" %% "spray-json" % "1.2.6",
          "io.spray" % "spray-httpx" % "1.3.1",
          "com.typesafe.akka"   %%  "akka-actor"    % "2.2.4",
          "com.typesafe.akka"   %%  "akka-testkit"  % "2.2.4"   % "test",

          // UTILS
          "com.github.nscala-time" %% "nscala-time" % "1.8.0",
          "com.azavea.geotrellis" %% "geotrellis" % "0.10.0-SNAPSHOT"
        )
    )

}
