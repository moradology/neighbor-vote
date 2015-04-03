package votewatch.service.route

import spray.httpx.marshalling.BasicMarshallers._

import spray.http.StatusCodes
import spray.routing._
import spray.routing.{ExceptionHandler, HttpService}
import scala.concurrent._

import spray.util.LoggingContext
import spray.routing._
import spray.json._

import votewatch.json.VoteWatchProtocol._
import votedb.tables.Voter

import com.github.nscala_time.time.Imports._
import scala.slick.driver.{JdbcDriver,JdbcProfile, PostgresDriver}
import java.util.Date
import com.github.tototoshi.slick.GenericJodaSupport


trait BufferRoutes extends BaseRoute {
  import spray.httpx.SprayJsonSupport._
  def voters =
    path("voters") {
      get { parameters('coords) { (coords) =>
        complete {
          Voter(None,None,None,
                None,None,None,
                "",None,None,
                Some("lname"),Some("fname"),Some("mname"),
                new LocalDate(2015, 2, 2),"",None,None,None,
                None,None,"",None)
        }
      }}
    }
}
