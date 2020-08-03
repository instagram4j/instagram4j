package com.github.instagram4j.instagram4j.responses.commerce;

import com.github.instagram4j.instagram4j.models.commerce.Product;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.responses.IGResponse;

import lombok.Data;

@Data
public class CommerceProductsDetailsResponse extends IGResponse {
    private Profile merchant;
    private Product product_item;
}
