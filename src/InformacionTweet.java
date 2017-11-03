import java.text.SimpleDateFormat;
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
	private String descripcion;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private String fecha;
	private int numeroComentarios=0;
	private int numeroReplicas=0;
	private int numeroMeGustas=0;
	private String idReplica = "-1";
	private boolean ubicacion = false;
	private String pais = "None";
	private String ciudad = "None";
	
	ArrayList<InformacionUsuario> usuarios_comentaron;
	//List<>
	
	public String getID()
	{
		return id;
	}
	
	public String getIdReplica()
	{
		return idReplica;
	}
	
	public String getScreenName()
	{
		return screeName;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public String getFecha()
	{
		return fecha;
	}
	
	public int getNumeroComentarios()
	{
		return numeroComentarios;
	}
	
	public int getNumeroReplicas()
	{
		return numeroReplicas;
	}
	
	public int getNumeroMeGustas()
	{
		return numeroMeGustas;
	}
	
	public boolean getUbicacion()
	{
		return ubicacion;
	}
	
	public String getPais()
	{
		return pais;
	}
	
	public String getCiudad()
	{
		return ciudad;
	}
	
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
			descripcion = status.getText();
			numeroComentarios = status.getRetweetCount();
			numeroMeGustas = status.getFavoriteCount();
			fecha = sdf.format(status.getCreatedAt());
			idReplica = String.valueOf(status.getInReplyToStatusId());
			System.out.println("id                : "+ status.getId());
			System.out.println("Descripcion       : " + status.getText());
			System.out.println("numero favoritos  : "+status.getFavoriteCount());
			System.out.println("numero de reTweets: "+status.getRetweetCount());
			System.out.println("fecha             : "+fecha);
			System.out.println("InReplyToStatusId : "+status.getInReplyToStatusId());
			
			if(status.getPlace() != null)
			{
				ubicacion = true;
				pais = status.getPlace().getCountry();
				ciudad = status.getPlace().getName();
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
