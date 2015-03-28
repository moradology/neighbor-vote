package com.votewatch

import akka.io.IO
import akka.actor.{ActorSystem, Props}

import spray.can.Http

import com.typesafe.config.{ConfigFactory, Config}

import com.votewatch.service.VoteWatchServiceActor

object Main {


  def main(args: Array[String]) = {
    // We need an ActorSystem to host our service
    implicit val system = ActorSystem("votewatch")

    // Create our service actor
    val service = system.actorOf(Props[VoteWatchServiceActor], "votewatch-service")

    // Bind our actor to HTTP
    IO(Http) ! Http.Bind(service, interface = "0.0.0.0", port = ConfigFactory.load.getInt("votewatch.port"))
  }
}

