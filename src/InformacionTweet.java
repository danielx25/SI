import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class InformacionTweet {
	
	Twitter twitter;
	private String id;
	private String screeName;
	private int numeroComentarios=0;
	private int numeroReplicas=0;
	private int numeroMeGustas=0;
	
	ArrayList<InformacionUsuario> usuarios_comentaron;
	//List<>
	
	public InformacionTweet(Twitter twitter,String screenName ,String id) {
		// TODO Auto-generated constructor stub
		this.twitter = twitter;
		this.screeName = screenName;
		this.id = id;
		usuarios_comentaron = new ArrayList<InformacionUsuario>();
		
	}
	
	
	public void analizandoTwitts() throws TwitterException
	{
		Status status = null;
		try {
			status = twitter.showStatus(Long.parseLong(id));
		} catch (NumberFormatException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status != null)
		{
			System.out.println("id                : "+ status.getId());
			System.out.println("Descripcion       : " + status.getText());
			System.out.println("numero favoritos  : "+status.getFavoriteCount());
			System.out.println("numero de reTweets: "+status.getRetweetCount());
			System.out.println("fecha             : "+status.getCreatedAt());
			System.out.println("InReplyToStatusId : "+status.getInReplyToStatusId());
			
			if(status.getPlace() != null)
			{
				System.out.println("localizacion: ");
            	System.out.println("pais        : "+status.getPlace().getCountry());
            	System.out.println("fullName    : "+status.getPlace().getFullName());
            	System.out.println("name        : "+status.getPlace().getName());
            	System.out.println("addres      : "+status.getPlace().getStreetAddress());
            	System.out.println("x: "+status.getPlace().getGeometryCoordinates());
			}


			Query query = new Query("to:" + screeName + " since_id:" + id);
	        QueryResult results;
	        

	        do {
	            results = twitter.search(query);
	            System.out.println("Results: " + results.getTweets().size());
	            List<Status> tweets = results.getTweets();
	            //ResponseList<Status> lista = results.get

	            for (Status tweet : tweets) 
	            {
	            	if (tweet.getInReplyToStatusId() == Long.parseLong(id))
	            	{
	            		System.out.println("de: "+tweet.getUser().getName());
	            		System.out.println("res: "+tweet.getText());
	            		//ResponseList<Status> lista =  tweet.getF;
	            		System.out.println("----------------");
	            	}
	            		
	            }
	            //    if (tweet.getInReplyToStatusId() == idUltimoTweet)
	            //        replies.add(tweet);
	        } while ((query = results.nextQuery()) != null);
		}
        
	}
}
