package com.github.instagram4j.Instagram4J.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.instagram4j.Instagram4J.utils.IGUtils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class IGUploadParameters {
    @Builder.Default
    private String retry_context = "{\"num_step_auto_retry\":0,\"num_reupload\":0,\"num_step_manual_retry\":0}";
    private String media_type;
    @Builder.Default
    private Object[] xsharing_user_ids = new Object[] {};
    private String image_compression;
    private String upload_id;
    private String is_sidecar;
    private String for_album;
    private String direct_v2;
    private String for_direct_story;
    private String is_igtv_video;

    public static String forPhoto(String upload_id, String media_type, boolean is_sidecar) {
        return IGUtils.objectToJson(IGUploadParameters.builder().upload_id(upload_id).media_type(media_type)
                .is_sidecar(is_sidecar ? "1" : null)
                .image_compression("{\"lib_name\":\"moz\",\"lib_version\":\"3.1.m\",\"quality\":\"80\"}").build());
    }

    public static String forVideo(String upload_id, String media_type, boolean is_sidecar, boolean for_album) {
        return IGUtils.objectToJson(IGUploadParameters.builder().upload_id(upload_id).media_type(media_type)
                .is_sidecar(is_sidecar ? "1" : null).for_album(for_album ? "1" : null).build());
    }
}
