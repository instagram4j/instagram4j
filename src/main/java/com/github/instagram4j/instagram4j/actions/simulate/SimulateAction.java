package com.github.instagram4j.instagram4j.actions.simulate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.async.AsyncAction;
import com.github.instagram4j.instagram4j.requests.IGRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsContactPointPrefillRequest;
import com.github.instagram4j.instagram4j.requests.accounts.AccountsGetPrefillCandidatesRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectGetPresenceRequest;
import com.github.instagram4j.instagram4j.requests.direct.DirectInboxRequest;
import com.github.instagram4j.instagram4j.requests.discover.DiscoverTopicalExploreRequest;
import com.github.instagram4j.instagram4j.requests.feed.FeedStoriesTrayRequest;
import com.github.instagram4j.instagram4j.requests.feed.FeedTimelineRequest;
import com.github.instagram4j.instagram4j.requests.launcher.LauncherSyncRequest;
import com.github.instagram4j.instagram4j.requests.linkedaccounts.LinkedAccountsGetLinkageStatusRequest;
import com.github.instagram4j.instagram4j.requests.loom.LoomFetchConfigRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaBlockedRequest;
import com.github.instagram4j.instagram4j.requests.multipleaccounts.MultipleAccountsGetAccountFamilyRequest;
import com.github.instagram4j.instagram4j.requests.news.NewsInboxRequest;
import com.github.instagram4j.instagram4j.requests.qe.QeSyncRequest;
import com.github.instagram4j.instagram4j.requests.qp.QpGetCooldowns;
import com.github.instagram4j.instagram4j.requests.status.StatusGetViewableStatusesRequest;
import com.github.instagram4j.instagram4j.requests.users.UsersArlinkDownloadInfoRequest;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SimulateAction {
    @NonNull
    private IGClient client;

    private static final IGRequest<?>[] preLoginFlow = {
            new LauncherSyncRequest(true),
            new QeSyncRequest(true),
            new AccountsContactPointPrefillRequest(),
            new AccountsGetPrefillCandidatesRequest()
    };

    private static final IGRequest<?>[] postLoginFlow = {
            new LauncherSyncRequest(),
            new QpGetCooldowns(),
            new MultipleAccountsGetAccountFamilyRequest(),
            new LinkedAccountsGetLinkageStatusRequest(),
            new LoomFetchConfigRequest(),
            new MediaBlockedRequest(),
            new FeedTimelineRequest(),
            new FeedStoriesTrayRequest(),
            new UsersArlinkDownloadInfoRequest(),
            new DiscoverTopicalExploreRequest().is_prefetch(true),
            new NewsInboxRequest(false),
            new DirectGetPresenceRequest(),
            new DirectInboxRequest().limit(0).visual_message_return_type("unseen")
                    .persistent_badging(true),
            new DirectInboxRequest().limit(20).fetch_reason("initial_snapshot")
                    .thread_message_limit(10).visual_message_return_type("unseen")
                    .persistent_badging(true),
            new StatusGetViewableStatusesRequest()
    };

    public List<CompletableFuture<?>> preLoginFlow() {
        return AsyncAction.executeRequestsAsync(client, preLoginFlow);
    }

    public List<CompletableFuture<?>> postLoginFlow() {
        return AsyncAction.executeRequestsAsync(client, postLoginFlow);
    }
}
