package com.votedb.tables

import geotrellis.vector._
import geotrellis.slick._

import scala.slick.driver.{JdbcDriver,JdbcProfile, PostgresDriver}
import java.util.Date

/**
  * Represents a voter
  *
  */
case class Voter(
  geocodedLocation: Option[String],
  tigerlineId: Option[Int],
  geocodeStatus: Option[String],
  geocodeScore: Option[String],
  address: Option[String],
  tigerlineSide: Option[String],
  id: String,
  x: Option[Double],
  y: Option[Double],
  lastName: Option[String],
  firstName: Option[String],
  middleName: Option[String],
  dob: Date,
  streetAddress: String,
  city: Option[String],
  state: Option[String],
  zip: Option[Int],
  lastvotedate: Option[Date],
  affiliation: Option[String],
  status: String,
  geom: Option[Projected[Point]]
)

object VotersTable {
  import PostgresDriver.simple._
  private val gisSupport = new PostGisProjectionSupport(PostgresDriver)
  import gisSupport._

  class Voters(tag: Tag) extends Table[Voter](tag, "voters") {
    def geocodedLocation = column[Option[String]],
    def tigerlineId = column[Option[Int]],
    def geocodeStatus = column[Option[String]],
    def geocodeScore = column[Option[String]],
    def address = column[Option[String]],
    def tigerlineSide = column[Option[String]],
    def id = column[String],
    def x = column[Option[Double]],
    def y = column[Option[Double]],
    def lastName = column[Option[String]],
    def firstName = column[Option[String]],
    def middleName = column[Option[String]],
    def dob = column[Date],
    def streetAddress = column[String],
    def city = column[Option[String]],
    def state = column[Option[String]],
    def zip = column[Option[Int]],
    def lastvotedate = column[Option[Date]],
    def affiliation = column[Option[String]],
    def status = column[String],
    def geom = column[Option[Projected[Point]]]
  }

  def votersTable = TableQuery[Boundaries]

}
