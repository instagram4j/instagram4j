package weco.results;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * 
 * @author Weco
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "width",
    "height"
})
@Data
public class CarouselCandidate {
	
    @JsonProperty("url")
    public String url;
    @JsonProperty("width")
    public Long width;
    @JsonProperty("height")
    public Long height;
    
}
