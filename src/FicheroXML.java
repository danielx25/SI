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
}
