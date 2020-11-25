package computerdatabase

import computerdatabase.utils.Manipulador
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val props = Manipulador.getProp();

  val httpProtocol = http
    .baseUrl(props.getProperty("prop.baseUrl"))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Scenario Name")
    .exec(http("request_1")
      .get(props.getProperty("prop.index")))
    .pause(7)
    .exec(http("request_2")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1")))
    .pause(7)
    .exec(http("request_3")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1") + props.getProperty("prop.test2")))
    .pause(7)
    .exec(http("request_4")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1")))
    .pause(7)
    .exec(http("request_5")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1") + props.getProperty("prop.test3")))
    .pause(7)
    .exec(http("request_6")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1")))
    .pause(7)
    .exec(http("request_7")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1") + props.getProperty("prop.test4")))
    .pause(7)
    .exec(http("request_8")
      .get(props.getProperty("prop.index") + props.getProperty("prop.test1")))
    .pause(7)
    .exec(http("request_9")
      .get(props.getProperty("prop.index")))
    .pause(7)

  setUp(
    scn.inject(
      nothingFor(4 seconds),
      atOnceUsers(1),
      rampUsers(1) during (5 seconds),
      constantUsersPerSec(1) during (5 seconds),
      constantUsersPerSec(1) during (5 seconds) randomized,
      rampUsersPerSec(1) to 1 during (1 minutes),
      rampUsersPerSec(1) to 1 during (1 minutes) randomized,
      heavisideUsers(1) during (5 seconds)
    )).protocols(httpProtocol)
}
