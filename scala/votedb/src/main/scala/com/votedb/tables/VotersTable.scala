package com.votedb.tables

import geotrellis.vector._
import geotrellis.slick._

import com.github.nscala_time.time.Imports._
import scala.slick.driver.{JdbcDriver,JdbcProfile, PostgresDriver}
import java.util.Date
import com.github.tototoshi.slick.GenericJodaSupport


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
  dob: LocalDate,
  streetAddress: String,
  city: Option[String],
  state: Option[String],
  zip: Option[Int],
  lastvotedate: Option[LocalDate],
  affiliation: Option[String],
  status: String,
  geom: Option[Projected[Point]]
)

object VotersTable {
  import PostgresDriver.simple._
  private val gisSupport = new PostGisProjectionSupport(PostgresDriver)
  import com.github.tototoshi.slick.PostgresJodaSupport._
  import gisSupport._

  class Voters(tag: Tag) extends Table[Voter](tag, "voters") {
    def geocodedLocation = column[Option[String]]("geocoded_location")
    def tigerlineId = column[Option[Int]]("tigerline_id")
    def geocodeStatus = column[Option[String]]("geocode_status")
    def geocodeScore = column[Option[String]]("geocode_score")
    def address = column[Option[String]]("address")
    def tigerlineSide = column[Option[String]]("tigerline_side")
    def id = column[String]("id")
    def x = column[Option[Double]]("x")
    def y = column[Option[Double]]("y")
    def lastName = column[Option[String]]("lastname")
    def firstName = column[Option[String]]("firstname")
    def middleName = column[Option[String]]("middlename")
    def dob = column[LocalDate]("dob")
    def streetAddress = column[String]("streetaddress")
    def city = column[Option[String]]("city")
    def state = column[Option[String]]("state")
    def zip = column[Option[Int]]("zip")
    def lastvotedate = column[Option[LocalDate]]("lastvotedate")
    def affiliation = column[Option[String]]("affiliation")
    def status = column[String]("status")
    def geom = column[Option[Projected[Point]]]("geom")

    def * = (geocodedLocation, tigerlineId, geocodeStatus, geocodeScore, address, tigerlineSide,
      id, x, y, lastName, firstName, middleName, dob, streetAddress, city, state, zip,
      lastvotedate, affiliation, status, geom) <> (Voter.tupled, Voter.unapply)
  }

  def votersTable = TableQuery[Voters]

}
