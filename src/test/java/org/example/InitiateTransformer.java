package org.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class InitiateTransformer {
    public static void main(String[] args) {
        WireMock.configureFor("localhost", 8080);
        WireMockServer wm =  new WireMockServer(wireMockConfig()
                .extensions("org.example.Transformer"));
        wm.start();
    }
}
