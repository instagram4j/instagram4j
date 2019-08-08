package org.brunocvcunha.instagram4j.requests.payload;

import org.apache.commons.lang3.Validate;
import org.brunocvcunha.instagram4j.requests.InstagramGetRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Wrapper for iterate through a InstagramGetUserFollowersResult
 *
 * @see InstagramGetUserFollowersRequest
 * @see InstagramGetUserFollowingRequest
 *
 * @author meiskalt7
 */
public class InstagramGetUserFollowersResultIteratorWrapper implements Iterable<InstagramUserSummary> {
    InstagramGetUserFollowersResult instagramGetUserFollowersResult;
    private InstagramGetRequest<InstagramGetUserFollowersResult> instagramGetRequest;

    /**
     * @param instagramGetRequest request for iterate through results
     * @throws NullPointerException if api field in instagramGetRequest is null
     */
    public InstagramGetUserFollowersResultIteratorWrapper(InstagramGetRequest<InstagramGetUserFollowersResult>
                                                                  instagramGetRequest
    ) throws IOException {
        Validate.notNull(instagramGetRequest.getApi());
        this.instagramGetRequest = instagramGetRequest;
        this.instagramGetUserFollowersResult = instagramGetRequest.getApi().sendRequest(instagramGetRequest);
    }

    @Override
    public Iterator<InstagramUserSummary> iterator() {
        return new InstagramGetUserFollowersRequestIterator();
    }

    /**
     * Iterator implementation for two requests:
     *
     * @see InstagramGetUserFollowersRequest
     * @see InstagramGetUserFollowingRequest
     */
    class InstagramGetUserFollowersRequestIterator implements Iterator<InstagramUserSummary> {
        int cursor;

        @Override
        public boolean hasNext() {
            if (this.cursor == instagramGetUserFollowersResult.getUsers().size() - 1) {
                return instagramGetUserFollowersResult.getNext_max_id() != null;
            } else {
                return true;
            }
        }

        @Override
        public InstagramUserSummary next() {
            int var1 = this.cursor;
            if (var1 >= instagramGetUserFollowersResult.getUsers().size() - 1) {
                if (instagramGetUserFollowersResult.getNext_max_id() == null) {
                    throw new NoSuchElementException();
                } else {
                    try {
                        if (instagramGetRequest instanceof InstagramGetUserFollowersRequest) {
                            InstagramGetUserFollowersRequest request = (InstagramGetUserFollowersRequest) instagramGetRequest;
                            request.setMaxId(instagramGetUserFollowersResult.getNext_max_id());
                        } else {
                            InstagramGetUserFollowingRequest request = (InstagramGetUserFollowingRequest) instagramGetRequest;
                            request.setMaxId(instagramGetUserFollowersResult.getNext_max_id());
                        }
                        instagramGetUserFollowersResult = instagramGetRequest.getApi().sendRequest(instagramGetRequest);
                        this.cursor = 0;
                        return instagramGetUserFollowersResult.getUsers().get(cursor);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                if (var1 >= instagramGetUserFollowersResult.getUsers().size() - 1) {
                    throw new ConcurrentModificationException();
                } else {
                    this.cursor = var1 + 1;
                    return instagramGetUserFollowersResult.getUsers().get(cursor);
                }
            }
        }
    }
}
