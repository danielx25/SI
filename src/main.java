import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import twitter4j.IDs;
import twitter4j.Place;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.TweetsResources;
import twitter4j.conf.ConfigurationBuilder;


public class main {



static void consulta(Twitter twitter)
	{
		try {
            Query query = new Query("alexis sanchez");
            QueryResult result;
            int contador = 0;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	System.out.println("id: "+tweet.getId());
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                    if(tweet.getPlace()!=null)
                    {
                    	System.out.println("localizacion: ");
                    	System.out.println("pais        : "+tweet.getPlace().getCountry());
                    	System.out.println("fullName    : "+tweet.getPlace().getFullName());
                    	System.out.println("name        : "+tweet.getPlace().getName());
                    	System.out.println("addres      : "+tweet.getPlace().getStreetAddress());
                    	
                    }
                    //localizacion(twitter, tweet);
                }
                contador+=1;
            } while ((query = result.nextQuery()) != null && contador < 2);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	static void amigos_seguidores(Twitter twitter)
	{
		try {
	        long cursor = -1;
	        IDs ids;
	      
	        System.out.println("Listing followers's ids.");
	        do {
	        	ids = twitter.getFollowersIDs("Danielxb25", cursor);
	            for (long id : ids.getIDs()) {
	            	System.out.println();
	                System.out.println("ID: "+id);
	                System.out.println("@"+twitter.showUser(id).getScreenName());
	                System.out.println("name: "+twitter.showUser(id).getName());
	            }
	        } while ((cursor = ids.getNextCursor()) != 0);
	        System.exit(0);
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to get followers' ids: " + te.getMessage());
	        System.exit(-1);
	    }
	}

	static void tweetsUsuario(Twitter twitter, String nameUser)
	{
		// gets Twitter instance with default credentials
        try {
            List<Status> statuses;
            String user = nameUser;
            statuses = twitter.getUserTimeline(user);
            System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) {
                System.out.println("id: "+status.getId()+" @" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Twitter twitter = Credenciales.obtenerCredencial();
		/*
		try {
			twitter.createFavorite(Long.parseLong("902937067386281985"));
		} catch (NumberFormatException | TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
        //amigos_seguidores(twitter);
        //enviarMensaje(twitter);
        //System.out.println("mensaje enviado!!!");
        //subirUnaFoto(twitter);
        //trendicTopic(twitter);
        //tweetsUsuario(twitter, "sebastianpinera");
        //getRetweets(twitter, "905607826936131584");
		
		/*
        List<Status> statuses;
		try {
			statuses = twitter.getFavorites("Reisub1");//Long.parseLong("902937067386281985"),-1);
			for (Status status : statuses) {
	            System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	        }
		} catch (NumberFormatException | TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
		//tweetsUsuario(twitter, "MovistarChile");
        /*
        InformacionUsuario usuario1 = new InformacionUsuario(twitter, "sebastianpinera");
        usuario1.infoUser();
        FicheroXML archivo = new FicheroXML("sebastianpinera");
        try {
			archivo.crearXML(usuario1);
		} catch (ParserConfigurationException
				| TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        /*
        try {
			usuario1.analizandoTwitts(usuario1.idUltimoTweet);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		
		
		/*
		InformacionTweet info = new InformacionTweet(twitter, "sebastianpinera", "937862056648298496");//"925367463709159424");
		try {
			info.analizandoTwitts();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FicheroXML fichero = new FicheroXML("tweetMovistar");
		try {
			fichero.archivoJson(info);
			fichero.crearXML_t(info);
		} catch (ParserConfigurationException
				| TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		MonitorTwitter monitor = new MonitorTwitter(twitter, "sebastianpinera");
		//monitor.tweetsUsuario();//twitter, "sebastianpinera");
		try {
			monitor.minarTweets();
		} catch (TwitterException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Termino del programa");
	 
	}
}
