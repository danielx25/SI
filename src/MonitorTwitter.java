import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MonitorTwitter {
	
	
	
	public MonitorTwitter() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void tweetsUsuario(Twitter twitter, String nameUser)
	{
		
		InformacionTweet info =null;//= new InformacionTweet(twitter, "sebastianpinera", "937862056648298496");
		
		// gets Twitter instance with default credentials
        try {
            List<Status> statuses;
            String user = nameUser;
            statuses = twitter.getUserTimeline(user);
            //System.out.println("Showing @" + user + "'s user timeline.");
            for (Status status : statuses) 
            {
                System.out.println("id: "+status.getId()+" @" + status.getUser().getScreenName() + " - " + status.getText());
                guardarEstadoTweet(twitter, nameUser, String.valueOf(status.getId()));
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
	}
	
	public void guardarEstadoTweet(Twitter twitter, String screenName, String idTweet)
	{
		InformacionTweet info = new InformacionTweet(twitter, screenName, idTweet);
		try {
			info.analizandoTwitts();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FicheroXML fichero = new FicheroXML("tweetMovistar");
		try {
			fichero.archivoJson(info);
			//fichero.crearXML_t(info);
		} catch ( TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
