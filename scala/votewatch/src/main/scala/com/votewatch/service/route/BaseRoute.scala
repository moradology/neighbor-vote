package com.votewatch.service.route

import spray.httpx.marshalling.BasicMarshallers._

import spray.http.StatusCodes
import spray.routing._
import spray.routing.{ExceptionHandler, HttpService}
import scala.concurrent._

import spray.util.LoggingContext
import spray.routing._
import spray.json._
import spray.httpx.SprayJsonSupport._
import DefaultJsonProtocol._


import scala.util.Success

trait BaseRoute extends HttpService {
  // Required for marshalling futures
  implicit val dispatcher: ExecutionContext

  def successMessage(msg: String) =
    JsObject("success" -> JsBoolean(true), ("message" -> JsString(msg.replace("\"", "'"))))

  def failureMessage(msg: String) =
    JsObject("success" -> JsBoolean(true), ("message" -> JsString(msg.replace("\"", "'"))))
    //("success" := false) ->: ("message" := msg.replace("\"", "'")) ->: jEmptyObject

  implicit def myExceptionHandler(implicit log: LoggingContext) =
    ExceptionHandler {
      case e: Exception =>
        requestUri { uri =>
          log.warning("Request to {} could not be handled normally", uri)

          complete {
            JsObject("aNum" -> JsNumber(1))
          }
        }
    }

  def testRoute =
    path("test") {
      get {
        complete {
          JsObject("whatever" -> JsNumber(867))
        }
      }
    }
}
