package com.github.instagram4j.Instagram4J.models.user;

import java.io.Serializable;

import com.github.instagram4j.Instagram4J.models.IGBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class IGProfile extends IGBaseModel implements Serializable {
    private static final long serialVersionUID = -892648357983l;
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
