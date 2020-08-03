package com.github.instagram4j.instagram4j.models.user;

import java.io.Serializable;

import com.github.instagram4j.instagram4j.models.IGBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profile extends IGBaseModel implements Serializable {
    private static final long serialVersionUID = -892648357982l;
    @EqualsAndHashCode.Include
    private Long pk;
    private String username;
    private String full_name;
    private boolean is_private;
    private String profile_pic_url;
    private String profile_pic_id;
    private boolean is_verified;
    private boolean has_anonymous_profile_picture;
}
