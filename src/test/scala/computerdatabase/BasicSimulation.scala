package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("https://www.centenariofm.com.br")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Scenario Name")
    .exec(http("request_1")
      .get("/"))
    .pause(7)
    .exec(http("request_2")
      .get("/noticias"))
    .pause(7)
    .exec(http("request_3")
      .get("/noticias/morre-francisco-camargo-pai-dos-sertanejos-zeze-e-luciano"))
    .pause(7)
    .exec(http("request_4")
      .get("/noticias"))
    .pause(7)
    .exec(http("request_5")
      .get("/noticias/urgente-araraquara-vive-madrugada-de-terror"))
    .pause(7)
    .exec(http("request_6")
      .get("/noticias"))
    .pause(7)
    .exec(http("request_7")
      .get("/noticias/tabatinga-11-novos-casos-e-1-obito-pela-covid-19-e-registrado-no-municipio-numero-de-casos-chega-a-401"))
    .pause(7)
    .exec(http("request_8")
      .get("/noticias"))
    .pause(7)
    .exec(http("request_9")
      .get("/"))
    .pause(7)

  setUp(
    scn.inject(
      nothingFor(4 seconds),
      atOnceUsers(10),
      rampUsers(10) during (5 seconds),
      constantUsersPerSec(20) during (15 seconds),
      constantUsersPerSec(20) during (15 seconds) randomized,
      rampUsersPerSec(10) to 20 during (10 minutes),
      rampUsersPerSec(10) to 20 during (10 minutes) randomized,
      heavisideUsers(10) during (20 seconds)
    )).protocols(httpProtocol)
}