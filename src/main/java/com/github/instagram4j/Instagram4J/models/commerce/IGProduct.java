package com.github.instagram4j.Instagram4J.models.commerce;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;
import com.github.instagram4j.Instagram4J.models.user.IGProfile;

import lombok.Data;

@Data
public class IGProduct extends IGBaseModel {
    private String name;
    private String price;
    private String current_price;
    private String full_price;
    private long product_id;
    private IGProfile merchant;
    private String compound_product_id;
    private String description;
    private String retailer_id;
    private String external_url;
    
}
