import java.io.File;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUploadPhotoRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramConfigurePhotoResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

public class TesteUpload {


	
	
	public static void main(String[] args) {
		
		try {
			Instagram4j instagram = Instagram4j.builder().username("fullballsoccer").password("P@$$w0rd123").build();			
			instagram.setup();
			instagram.login();

			/*InstagramConfigurePhotoResult result =instagram.sendRequest(new InstagramUploadPhotoRequest(
			        new File("C:\\Users\\Dell\\Pictures\\images.jpg"),
			        "Outch!"));*/
			
			/*InstagramConfigurePhotoResult result =instagram.sendRequest(new InstagramUploadPhotoRequest(
			        new File("C:\\Users\\Dell\\Pictures\\download.jpg"),
			        "Outch!",null,"10","50"));*/
			
			InstagramFeedResult tagFeed = instagram.sendRequest(new InstagramTagFeedRequest("fortaleza"));
			Here:for (InstagramFeedItem feedResult : tagFeed.getItems()) {
				
			    System.out.println("Post ID: " + feedResult.getPk());
			    if(feedResult.getLocation()!=null) {
			    	System.out.println("Adress: " + feedResult.getLocation().getAddress());
			    	System.out.println("Name: " + feedResult.getLocation().getName());
				    System.out.println("City: " + feedResult.getLocation().getCity());
				    System.out.println("ExID: " + feedResult.getLocation().getExternal_id());
				    System.out.println("Ex: " + feedResult.getLocation().getExternal_source());
				    System.out.println("ExIdS: " + feedResult.getLocation().getExternal_id_source());
				    System.out.println("FBPId: " + feedResult.getLocation().getFacebook_places_id());
				    System.out.println("FBId: " + feedResult.getLocation().getPlace_fbid());
				    
				    if(feedResult.getLocation().getCity().equals("Fortaleza") ) {
				    	InstagramConfigurePhotoResult result2 =instagram.sendRequest(new InstagramUploadPhotoRequest(
						        new File("C:\\Users\\Dell\\Pictures\\1.jpg"),
						        "We love NY, We love Soccer #soccer!",null,feedResult.getLocation()));
				    	break Here;
				    }
			    }
			    
				/*for (InstagramLocation location: feedResult.location)
				{
				    System.out.println(entry.getKey() + "/" + entry.getValue().toString());
				}*/
			}
			
			

			/*
			 * 
			 * instagram.sendRequest(new InstagramUploadPhotoRequest(
        new File("/tmp/file-to-upload.jpg"),
        "Posted with Instagram4j, how cool is that?"));
			 * */
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
}
