package org.brunocvcunha.instagram4j.requests.payload;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramGetReelsTrayResult extends StatusResult {
    private List<InstagramStoryTray> tray;
    private List<InstagramBroadcast> broadcasts;
    private InstagramPostLive post_live;
    private int sticker_version;
    private int face_filter_nux_version;
    private boolean has_new_nux_story;
    private String story_ranking_token;

}