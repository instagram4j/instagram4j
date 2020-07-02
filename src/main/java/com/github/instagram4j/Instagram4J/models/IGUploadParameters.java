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
    private String is_direct_voice;

    @Override
    public String toString() {
        return IGUtils.objectToJson(this);
    }

    public static IGUploadParameters forPhoto(String upload_id, String media_type, boolean is_sidecar) {
        return IGUploadParameters.builder().upload_id(upload_id).media_type(media_type)
                .is_sidecar(is_sidecar ? "1" : null)
                .image_compression("{\"lib_name\":\"moz\",\"lib_version\":\"3.1.m\",\"quality\":\"80\"}").build();
    }

    public static IGUploadParameters forTimelineVideo(String upload_id, boolean is_sidecar) {
        return IGUploadParameters.builder().upload_id(upload_id).media_type("2").is_sidecar(is_sidecar ? "1" : null)
                .build();
    }

    public static IGUploadParameters forAlbumVideo(String upload_id) {
        return IGUploadParameters.builder().upload_id(upload_id).media_type("2").for_album("1").build();
    }

    public static IGUploadParameters forDirectVideo(String upload_id) {
        return IGUploadParameters.builder().upload_id(upload_id).media_type("2").direct_v2("1").build();
    }

    public static IGUploadParameters forDirectVoice(String upload_id) {
        return IGUploadParameters.builder().upload_id(upload_id).media_type("11").is_direct_voice("1").build();
    }
}
