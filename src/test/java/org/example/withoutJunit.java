package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class withoutJunit {
    public static void main(String[] args) {
        WireMock.configureFor("localhost", 8080);

        WireMockServer wm = new WireMockServer(wireMockConfig().port(8080)); //No-args constructor will start on port 8080, no HTTPS
        wm.start();
        wm.stubFor(get("/app1")
                .withHost(equalTo("localhost"))
                .willReturn(ok("app1 reply")));
        wm.stubFor(get("/app2")
                .withHost(equalTo("localhost"))
                .willReturn(ok("app2 reply")));
        wm.stubFor(get("/app3")
//                .withHost(equalTo("localhost"))
                .willReturn(ok("app3 reply")));

// Do some stuff

//        WireMock.reset();

// Finish doing stuff

//        wireMockServer.stop();
    }
}
