package com.github.instagram4j.Instagram4J.requests.accounts;

import com.github.instagram4j.Instagram4J.IGClient;
import com.github.instagram4j.Instagram4J.models.IGPayload;
import com.github.instagram4j.Instagram4J.requests.IGPostRequest;
import com.github.instagram4j.Instagram4J.responses.accounts.IGAccountsUserResponse;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IGAccountsChangeProfilePictureRequest extends IGPostRequest<IGAccountsUserResponse> {
    @NonNull
    private String _upload_id;

    @Override
    protected IGPayload getPayload(IGClient client) {
        return new IGPayload() {
            @Getter
            private String upload_id = _upload_id;
        };
    }

    @Override
    public String path() {
        return "accounts/change_profile_picture/";
    }

    @Override
    public Class<IGAccountsUserResponse> getResponseType() {
        return IGAccountsUserResponse.class;
    }

}
