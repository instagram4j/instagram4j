package com.github.instagram4j.instagram4j.actions.account;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.requests.accounts.*;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsActionRequest.AccountsAction;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.accounts.AccountsUserResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountAction {
    @NonNull
    private IGClient client;

    public CompletableFuture<AccountsUserResponse> setProfilePicture(byte[] photo) {
        return client.actions().upload()
                .photo(photo, String.valueOf(System.currentTimeMillis()))
                .thenCompose(res -> new AccountsChangeProfilePictureRequest(res.getUpload_id())
                        .execute(client));
    }
    
    public CompletableFuture<AccountsUserResponse> setProfilePicture(File file) {
        try {
            return setProfilePicture(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    public CompletableFuture<IGResponse> setBio(String bio) {
        return new AccountsSetBiographyRequest(bio).execute(client);
    }
    
    public CompletableFuture<AccountsUserResponse> action(AccountsAction action) {
        return new AccountsActionRequest(action).execute(client);
    }
    
    public CompletableFuture<AccountsUserResponse> currentUser() {
        return new AccountsCurrentUserRequest().execute(client);
    }

    /**
     * There is a daily limit to the use of setStatus.
     *
     * @param text The thread app limit the String to a length to 30 characters, but there is no server verification
     * @param emoji The thread app prevent you to put anything else that an emoji, but there is no server verification
     * @param expires_at Must be in second (Example System.currentTimeMillis()/1000+60 for 60 seconds)
     * @param should_notify Close friend should be notify or not
     * @param status_type Must be "auto" or "manual"
     * @return IGResponse
     */
    public CompletableFuture<IGResponse> setStatus(String text, String emoji, long expires_at, boolean should_notify, String status_type) {
        return new AccountsEditStatusRequest(new AccountsEditStatusRequest.AccountsEditStatusPayload(text,emoji,expires_at,should_notify,status_type)).execute(client);
    }
}
