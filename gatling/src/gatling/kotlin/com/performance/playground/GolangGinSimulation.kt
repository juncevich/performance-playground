package com.performance.playground

import io.gatling.javaapi.core.Assertion
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.ScenarioBuilder
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpProtocolBuilder
import java.time.Duration

class GolangGinSimulation : Simulation() {

    // Define HTTP configuration
    private val httpProtocol: HttpProtocolBuilder = http
        .baseUrl("http://localhost:8090")
        .acceptHeader("application/json")
        .userAgentHeader(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36"
        )
        .shareConnections()

    // Define scenario
    private val returnOnlyOkCode: ScenarioBuilder = scenario("Return only ok code")
        .exec(http("Return only ok code").get("/golang/gin/return-only-ok-code"))

    private val returnOkCodeString: ScenarioBuilder = scenario("Return ok code string")
        .exec(http("Return ok code string").get("/sping/mvc/return-ok-code-string"))

    // Define assertions
    private val assertion: Assertion = global().failedRequests().count().shouldBe(0L)

    // Define injection profile and execute the test
    init {
        setUp(
//            returnOnlyOkCode
//            returnOkCodeString
//                .injectOpen(
//                    rampUsersPerSec(0.0).to(2000.0).during(Duration.ofSeconds(30)),
//                    constantUsersPerSec(2000.0).during(Duration.ofMinutes(1))
////                    stressPeakUsers(10000).during(20)
////                    stressPeakUsers(15000).during(20)
//
//                )
            returnOnlyOkCode.injectClosed(
                // first ramp the number of concurrent users to 100 users in 1 minute
                rampConcurrentUsers(0).to(2000).during(Duration.ofSeconds(30)),
                // then keep a steady number of concurrent users of 100 users during 10 minutes
                constantConcurrentUsers(2000).during(Duration.ofMinutes(1))
            )

        )
            .assertions(assertion)
            .protocols(httpProtocol)
    }
}