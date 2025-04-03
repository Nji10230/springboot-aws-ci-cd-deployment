package com.example.dataJpa_relations.handler;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.serverless.proxy.spring.SpringBootProxyHandlerBuilder;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.dataJpa_relations.SpringbootDataJpaRelationshipsApplication;

public class StreamLambdaHandler implements RequestHandler<AwsProxyRequest, AwsProxyResponse> {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = (SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse>)
                    new SpringBootProxyHandlerBuilder()
                            .springBootApplication(SpringbootDataJpaRelationshipsApplication.class)
                            .asyncInit()
                            .buildAndInitialize();
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }


    @Override
    public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {
        return handler.proxy(awsProxyRequest,context);
    }
}