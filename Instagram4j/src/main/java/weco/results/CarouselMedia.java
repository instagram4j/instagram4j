
package weco.results;

import java.util.ArrayList;
import java.util.List;

import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
/**
 * 
 * @author Weco
 *
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "original_width",
    "image_versions2",
    "pk",
    "media_type",
    "original_height",
    "carousel_parent_id",
    "usertags"
})
public class CarouselMedia {

    @JsonProperty("id")
    public String id;
    @JsonProperty("original_width")
    public Long originalWidth;
    @JsonProperty("image_versions2")
    public ImageVersions2 imageVersions2;
    @JsonProperty("pk")
    public Long pk;
    @JsonProperty("media_type")
    public Long mediaType;
    @JsonProperty("original_height")
    public Long originalHeight;
    @JsonProperty("carousel_parent_id")
    public String carouselParentId;
    @JsonProperty("usertags")
    public Usertags usertags;
    
    @JsonIgnore
    public List<CarouselMediaIn> getCarouselMediaIn(){
    	return usertags.getIn();
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "candidates"
    })
    @Data
    private static class ImageVersions2 {
        @JsonProperty("candidates")
        public List<CarouselCandidate> candidates = new ArrayList<CarouselCandidate>();
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "in"
    })
    @Data
    private static class Usertags{
        @JsonProperty("in")
        public List<CarouselMediaIn> in = new ArrayList<CarouselMediaIn>();
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "position",
        "time_in_video",
        "user"
    })
    @Data
    public static class CarouselMediaIn {
        @JsonProperty("position")
        public List<Double> position = new ArrayList<Double>();
        @JsonProperty("time_in_video")
        public Object timeInVideo;
        @JsonProperty("user")
        public InstagramUser user;
    }
    
}
