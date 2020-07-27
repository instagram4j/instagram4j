package com.github.instagram4j.Instagram4J.requests.commerce;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.requests.IGGetRequest;
import com.github.instagram4j.Instagram4J.responses.commerce.IGCommerceProductsDetailsResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGCommerceProductsDetailsRequest extends IGGetRequest<IGCommerceProductsDetailsResponse> {
    @NonNull
    private String product_id,  merchant_id;

    @Override
    public String path() {
        return "commerce/products/" + product_id + "/details/";
    }

    @Override
    public String getQueryString(IGClient client) {
        return mapQueryString("merchant_id", merchant_id);
    }

    @Override
    public Class<IGCommerceProductsDetailsResponse> getResponseType() {
        return IGCommerceProductsDetailsResponse.class;
    }

}
