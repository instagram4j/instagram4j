package org.brunocvcunha.instagram4j.requests.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Instagram Recent Activity
 * 
 * @author Krisnamourt da Silva C. Filho
 *
 */

@Getter
@Setter
@ToString(callSuper = true)
public class InstagramRecentActivity {

	private List<InstagramItem> items;
	private boolean more_available;
}
