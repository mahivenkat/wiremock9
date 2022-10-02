package org.example;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class ExampleTransformer extends ResponseDefinitionTransformer {

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        return new ResponseDefinitionBuilder()
//                .withHeader("MyHeader", "Transformed")
                .withStatus(200)
                .withBody("Transformed body")
                .build();
    }

    @Override
    public String getName() {
        return "lllll";
    }
}