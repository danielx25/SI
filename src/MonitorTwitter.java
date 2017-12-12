import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;


public class MonitorTwitter {
	
	Twitter twitter;
	String nameUser;
	ArrayList<ReplicaTweet> usuarios_comentaron;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public MonitorTwitter(Twitter twitter, String nameUser) {
		// TODO Auto-generated constructor stub
		this.twitter = twitter;
		this.nameUser = nameUser;
		usuarios_comentaron = new ArrayList<ReplicaTweet>();
	}
	
	public void minarTweets() throws TwitterException, InterruptedException
	{
		Query query = new Query(nameUser);//("to:" + nameUser + " since_id:" + id);
        QueryResult results;
        ReplicaTweet usuario;

        do {
        	
        	final long startTime = System.nanoTime();
            results = twitter.search(query);
            System.out.println("Results: " + results.getTweets().size());
            List<Status> tweets = results.getTweets();

            for (Status tweet : tweets) 
            {
        		usuario = new ReplicaTweet(twitter, tweet.getUser().getScreenName());
        		usuario.infoUser();
        		usuario.setTextoTweet(tweet.getText());
        		usuario.setIdtweetReplica(String.valueOf(tweet.getInReplyToStatusId()));
        		usuario.setFechaPublicacion(sdf.format(tweet.getCreatedAt()));
        		usuario.setID(String.valueOf(tweet.getId()));
        		usuarios_comentaron.add(usuario);
        		
        		
        		System.out.println();
        		System.out.println("_________________________________________");
    			System.out.println("id                : "+ usuario.getID());
    			System.out.println("screenName        : "+ usuario.getScreenName());
    			System.out.println("texto             : " + usuario.getTextoTweet());
    			System.out.println("id replica        : "+usuario.getIdtweetReplica());
    			//System.out.println("numero de reTweets: "+status.getRetweetCount());
    			//System.out.println("fecha             : "+fecha);
    			//System.out.println("InReplyToStatusId : "+status.getInReplyToStatusId());
            }
            
            
            
            final long duration = System.nanoTime() - startTime;
            if ((5500 - duration / 1000000) > 0) {
                //logger.info("Sleep for " + (6000 - duration / 1000000) + " miliseconds");
                Thread.sleep((5500 - duration / 1000000));
            }

        } while ((query = results.nextQuery()) != null);
	}
	
	public void tweetsUsuario()//Twitter twitter, String nameUser)
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
			info.analizandoTwitts(usuarios_comentaron);
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
