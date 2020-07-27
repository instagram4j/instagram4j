package com.github.instagram4j.Instagram4J.responses.commerce;

import com.github.instagram4j.Instagram4J.models.commerce.IGProduct;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class IGCommerceProductsDetailsResponse extends IGResponse {
    private IGProfile merchant;
    private IGProduct product_item;
}
