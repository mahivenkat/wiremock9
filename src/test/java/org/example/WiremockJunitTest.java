package org.example;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockJunitTest {

    @Rule
    public WireMockRule wm = new WireMockRule(wireMockConfig().port(8080));

    @Test
    public void assertWiremockSetup() throws IOException {

        // Arrange - setup wiremock stubs
        configureStubs();

        // execute request through the http client
        OkHttpClient client = new OkHttpClient();

        Request request1 = new Request.Builder()
                .url("http://localhost:8080/test/abc1")
                .get()
                .build();
        Request request2 = new Request.Builder()
                .url("http://localhost:8080/test/abc2")
                .get()
                .build();

        Request request3 = new Request.Builder()
                .url("http://localhost:8080/test/abc3")
                .get()
                .build();

        // Act - call the endpoint
        Response response1 = client.newCall(request1).execute();
        Response response2 = client.newCall(request2).execute();
        Response response3 = client.newCall(request3).execute();

        System.out.println("----------------");
        System.out.println(response1.body().string());
        System.out.println("----------------");
        System.out.println(response2.body().string());
        System.out.println("----------------");
        System.out.println(response3.body().string());
        // Assert - verify the response
//        assertEquals("Test success!", response.body().string());
//        verify(exactly(1),getRequestedFor(urlEqualTo("/test/abc")));

    }

    // configure stubs for wiremock
    private void configureStubs() {
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/test/abc1"))
                .willReturn(aResponse().withBody("Test1 success!")));
        stubFor(get(urlEqualTo("/test/abc2"))
                .willReturn(aResponse().withBody("Test2 success!")));
        stubFor(get(urlEqualTo("/test/abc3"))
                .willReturn(aResponse().withBody("Test3 success!")));
    }

}