import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;


import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FicheroXML {
	
	private String ruta;
	
	public FicheroXML(String ruta) {
		// TODO Auto-generated constructor stub
		this.ruta = ruta;
	}
	
	public void crearXML(InformacionUsuario usuario) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
	{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, ruta, null);
        document.setXmlVersion("1.0");
		
        Element raiz = document.getDocumentElement();
        
        raiz.appendChild(usuario(document, usuario));
        
        //Generate XML
        Source source = new DOMSource(document);
        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new java.io.File(this.ruta+".xml")); //nombre del archivo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
	}
	
	
	public Element usuario(Document documento, InformacionUsuario usuario)
	{
		Element user = documento.createElement("Usuario");
		
		Element screenName = documento.createElement("ScreenName");
		Text textScreenName = documento.createTextNode(usuario.getScreenName());
		screenName.appendChild(textScreenName);
		
		Element nameUser = documento.createElement("Name");
		Text texName = documento.createTextNode(usuario.getName());
		nameUser.appendChild(texName);
		
		Element description = documento.createElement("Description");
		Text textDes = documento.createTextNode(usuario.getDescription());
		description.appendChild(textDes);
		
		Element followers = documento.createElement("Followers");
		followers.appendChild(documento.createTextNode(String.valueOf(usuario.getCountFollowers())));
		
		Element followed = documento.createElement("Followed");
		followed.appendChild(documento.createTextNode(String.valueOf(usuario.getCountFollowed())));
		
		Element tweets = documento.createElement("Tweets");
		tweets.appendChild(documento.createTextNode(String.valueOf(usuario.getCountTweets())));
		
		user.appendChild(screenName);
		user.appendChild(nameUser);
		user.appendChild(description);
		user.appendChild(followers);
		user.appendChild(followed);
		user.appendChild(tweets);
		
		return user;
	}
	
	public void crearXML_t(InformacionTweet tweet) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, ruta, null);
        document.setXmlVersion("1.0");
		
        Element raiz = document.getDocumentElement();
        
        raiz.appendChild(tweetXML(document, tweet));
        
        //Generate XML
        Source source = new DOMSource(document);
        //Indicamos donde lo queremos almacenar
        Result result = new StreamResult(new java.io.File(tweet.getScreenName()+tweet.getID()+".xml")); //nombre del archivo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
	}
	
	public Element tweetXML(Document documento, InformacionTweet tweet)
	{
		Element tweetRaiz = documento.createElement("Tweet");
		
		Element id = documento.createElement("Id");
		id.appendChild(documento.createTextNode(tweet.getID()));
		
		Element idReplica = documento.createElement("IdReplica");
		idReplica.appendChild(documento.createTextNode(tweet.getIdReplica()));
		
		Element texto = documento.createElement("Texto");
		texto.appendChild(documento.createTextNode(tweet.getDescripcion()));
		
		Element fecha = documento.createElement("Fecha");
		fecha.appendChild(documento.createTextNode(tweet.getFecha()));
		
		Element screenName = documento.createElement("screenName");
		screenName.appendChild(documento.createTextNode(tweet.getScreenName()));
		
		System.out.println("******>"+tweet.getNumeroComentarios());
		Element numeroComentarios = documento.createElement("NumeroComentarios");
		numeroComentarios.appendChild(documento.createTextNode(String.valueOf(tweet.getNumeroComentarios())));

		Element numeroReplicas = documento.createElement("NumeroReplicas");
		numeroReplicas.appendChild(documento.createTextNode(String.valueOf(tweet.getNumeroReplicas())));

		Element numeroMeGusta = documento.createElement("NumeroMeGustas");
		numeroMeGusta.appendChild(documento.createTextNode(String.valueOf(tweet.getNumeroMeGustas())));
		
		tweetRaiz.appendChild(id);
		tweetRaiz.appendChild(idReplica);
		tweetRaiz.appendChild(texto);
		tweetRaiz.appendChild(fecha);
		tweetRaiz.appendChild(screenName);
		
		if(tweet.getUbicacion())
		{

			Element pais = documento.createElement("Pais");
			pais.appendChild(documento.createTextNode(tweet.getPais()));
			
			Element ciudad = documento.createElement("ciudad");
			ciudad.appendChild(documento.createTextNode(tweet.getCiudad()));
			
			tweetRaiz.appendChild(pais);
			tweetRaiz.appendChild(ciudad);
		}
		
		tweetRaiz.appendChild(numeroComentarios);
		tweetRaiz.appendChild(numeroReplicas);
		tweetRaiz.appendChild(numeroMeGusta);
		
		
		return tweetRaiz;
	}
	
	public void archivoJson(InformacionTweet tweet)
	{
		JSONObject obj = new JSONObject();
		obj.put("Id", tweet.getID());
		obj.put("IdReplica", tweet.getIdReplica());
		obj.put("Texto", tweet.getDescripcion());
		obj.put("Fecha", tweet.getFecha());
		obj.put("ScreenName", tweet.getScreenName());
		obj.put("Numero Comentarios", tweet.getNumeroComentarios());
		obj.put("Numero Replicas", tweet.getNumeroReplicas());
		obj.put("Numero MeGustas", tweet.getNumeroMeGustas());
		
		
		JSONArray list = new JSONArray();

		//obj.put("Tags", list);
		JSONObject usuarioObj;// = new JSONObject();
		for (ReplicaTweet usuario : tweet.getListaUsuarios()) {
			usuarioObj = new JSONObject();
			usuarioObj.put("Id", usuario.getID());
			usuarioObj.put("IdReplica", usuario.getIdtweetReplica());
			usuarioObj.put("ScreenName",usuario.getScreenName());
			usuarioObj.put("Nombre", usuario.getName());
			usuarioObj.put("textoTweet", usuario.getTextoTweet());
			usuarioObj.put("fecha", usuario.getFechaPublicacion());
			usuarioObj.put("Descricion", usuario.getDescription());
			usuarioObj.put("Seguidores", usuario.getCountFollowers());
			usuarioObj.put("Seguidos", usuario.getCountFollowed());
			usuarioObj.put("Numeros Seguidores", usuario.getCountTweets());
			
			
			//obj.put("usuario", usuarioObj);
			list.add(usuarioObj);
			
		}
		obj.put("Usuarios", list);

		try {

			FileWriter file = new FileWriter(tweet.getScreenName()+tweet.getID()+".json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			//manejar error
		}

		System.out.print(obj);
	}
}
