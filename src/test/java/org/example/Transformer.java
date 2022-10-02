package org.example;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import java.util.Date;

public class Transformer extends ResponseDefinitionTransformer {
    int counter = 0;

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        System.out.println("counter: " + counter);
        if (request.getUrl().contains("posts") && counter == 0) {
            responseDefinition = new ResponseDefinitionBuilder().
                    proxiedFrom("https://jsonplaceholder.typicode.com").build();
            counter = counter + 1;
            System.out.println("Response from application server");
            loadRespToFile();
        }else if (request.getUrl().contains("posts") && counter > 0) {
            responseDefinition = new ResponseDefinitionBuilder()
                    .withStatus(200)
                    .withBodyFile("resp.json")
                    .build();
            System.out.println("Response from mock server");
        } else if (request.getUrl().contains("transform")) {
            responseDefinition = new ResponseDefinitionBuilder()
                    .withStatus(200)
                    .withBody(javaManipulation())
                    .build();
        } else {
            responseDefinition = new ResponseDefinitionBuilder()
                    .withStatus(400)
                    .withBody("Please check request once. Not matching with existing mock apis")
                    .build();
        }
        return responseDefinition;
    }

    private void loadRespToFile() {
        System.out.println("Writing response to json file.......");
    }

    @Override
    public boolean applyGlobally() {
        return true;
    }

    @Override
    public String getName() {
        return "lllll";
    }

    public String javaManipulation() {
        return "Venkat........." + new Date();
    }
}