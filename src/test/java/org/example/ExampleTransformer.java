package org.example;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import wiremock.com.google.common.collect.ImmutableMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ExampleTransformer extends ResponseDefinitionTransformer {

    @Override
    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
        ResponseDefinition tt;
        if(request.getUrl().contains("transform")){
            tt = new ResponseDefinitionBuilder()
//                .withHeader("MyHeader", "Transformed")
                    .withStatus(200)
                    .withBody("Transformed body...............")
                    .build();
        }else {

             tt = new ResponseDefinitionBuilder()
//                .withHeader("MyHeader", "Transformed")
                    .withStatus(200)
                    .withBody("Transformed body")
                    .build();
        }
        return  tt;
    }



    @Override
    public String getName() {
        return "lllll";
    }
}