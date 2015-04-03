package votewatch.json

import spray.json._
import org.joda.time.format.ISODateTimeFormat
import com.github.nscala_time.time.Imports._

import votedb.tables.Voter

package object VoteWatchProtocol extends DefaultJsonProtocol {

  implicit object VoterFormat extends RootJsonFormat[Voter] {
    def read(j: JsValue): Voter = ???
    def write(v: Voter): JsValue = JsObject(
      "fname" -> JsString(v.firstName.getOrElse("")),
      "mname" -> JsString(v.middleName.getOrElse("")),
      "lname" -> JsString(v.lastName.getOrElse("")),
      "affiliation" -> JsString(v.affiliation.getOrElse("")),
      "priorvote" -> JsString(v.dob.toString),
      "status" -> JsBoolean(if (v.status == "I") false else true),
      "dob" -> JsString(v.dob.toString)
    )
  }

}
