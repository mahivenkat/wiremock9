package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static wiremock.org.eclipse.jetty.webapp.MetaDataComplete.True;

public class withoutJunit {
    public static void main(String[] args) {
        WireMock.configureFor("localhost", 8080);

        WireMockServer wm = new WireMockServer(wireMockConfig()); //No-args constructor will start on port 8080, no HTTPS
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
//        wm.stubFor(proxyAllTo("https://jsonplaceholder.typicode.com/posts").withName("/api4").);

            wm.stubFor(get(urlMatching("/posts"))
                    .willReturn(aResponse().proxiedFrom("https://jsonplaceholder.typicode.com")));



        stubFor(get(urlEqualTo("/posts"))
                .willReturn(aResponse()
                        .withBodyFile("resp.json")));

        stubFor(get("/json")
                .willReturn(okJson("{ \"message\": \"Hello\" }")));



        // Do some stuff

//        WireMock.reset();

// Finish doing stuff

//        wireMockServer.stop();
    }
}
