package com.github.instagram4j.Instagram4J.responses.commerce;

import com.github.instagram4j.Instagram4J.models.commerce.Product;
import com.github.instagram4j.Instagram4J.models.user.Profile;
import com.github.instagram4j.Instagram4J.responses.IGResponse;

import lombok.Data;

@Data
public class CommerceProductsDetailsResponse extends IGResponse {
    private Profile merchant;
    private Product product_item;
}
