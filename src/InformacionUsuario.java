
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class InformacionUsuario {
	
	private Twitter twitter;
	private String screenName;
	private String name;
	private String descripcion;
	private int numeroSeguidores;
	private int numeroSeguidos;
	private int numeroTweets;
	
	public String idUltimoTweet;
	
	
	public String getScreenName()
	{
		return screenName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return descripcion;
	}
	
	public int getCountFollowers()
	{
		return numeroSeguidores;
	}
	
	public int getCountFollowed()
	{
		return numeroSeguidos;
	}
	
	public int getCountTweets()
	{
		return numeroTweets;
	}
	
	
	public InformacionUsuario(Twitter twitter, String screenName) {
		// TODO Auto-generated constructor stub
		this.twitter = twitter;
		this.screenName = screenName;
	}
	
	
	public void infoUser()
	{
		User user = null;
		try {
			user = twitter.showUser(screenName);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (user.getStatus() != null) {
            System.out.println("screen name : "+ user.getScreenName());
            System.out.println("name        : "+ user.getName());
            System.out.println("ultimo tweet: "+ user.getStatus().getText());
            System.out.println("descripcion : "+ user.getDescription());
            System.out.println("seguidores  : "+ user.getFollowersCount());
            System.out.println("sigue       : "+ user.getFriendsCount());
            System.out.println("tweets      : "+ user.getStatusesCount());
            
            name = user.getName();
            idUltimoTweet = String.valueOf(user.getStatus().getId());
            descripcion = user.getDescription();
            numeroSeguidores = user.getFollowersCount();
            numeroSeguidos = user.getFriendsCount();
            numeroTweets = user.getStatusesCount();
            
        } else {
            // the user is protected
            System.out.println("@" + user.getScreenName());
        }
	}

	public void analizandoTwitts(String id) throws TwitterException
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
			}


			Query query = new Query("to:" + screenName + " since_id:" + idUltimoTweet);
	        QueryResult results;
	        

	        do {
	            results = twitter.search(query);
	            System.out.println("Results: " + results.getTweets().size());
	            List<Status> tweets = results.getTweets();
	            //ResponseList<Status> lista = results.get

	            for (Status tweet : tweets) 
	            {
	            	if (tweet.getInReplyToStatusId() == Long.parseLong(idUltimoTweet))
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
