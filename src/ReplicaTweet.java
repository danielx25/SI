import twitter4j.Twitter;


public class ReplicaTweet extends InformacionUsuario{
	
	private String idtweetReplica;
	private String textoTweet;
	private String fecha;
	private String id;
	 

	public ReplicaTweet(Twitter twitter, String screenName) {
		super(twitter, screenName);
		// TODO Auto-generated constructor stub
	}
	
	public void setIdtweetReplica(String idR)
	{
		idtweetReplica = idR;
	}
	
	public String getIdtweetReplica()
	{
		return idtweetReplica;
	}
	
	public void setTextoTweet(String texto)
	{
		textoTweet = texto;
	}
	
	public String getTextoTweet()
	{
		return textoTweet;
	}
	
	public void setFechaPublicacion(String fecha1)
	{
		fecha = fecha1;
	}
	
	public String getFechaPublicacion()
	{
		return fecha;
	}
	
	public String getID()
	{
		return id;
	}
	
	public void setID(String id_)
	{
		id = id_;
	}

}
