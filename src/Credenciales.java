

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Credenciales {

	public static String consumerKey = "Zf27urR8aOmUZmbZMyfCwkoUq";
	public static String consumerSecret = "Dh02xU6rtfHbYXbqE7cX1ty4MsaerPGXz8wpICmjF9fGUm6TL7";
	public static String accessToken = "104270882-LNozyQuIzTopUKRaqk9ly5tSXp7b7IkMy4PvmD8g";
	public static String accessTokenSecret = "U9hzcis3THxdng487m9zOJzuQ9grzNmtFwB5ZbfWJNCGO";
	
	static Twitter obtenerCredencial()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
				.setOAuthConsumerKey(consumerKey)
				.setOAuthConsumerSecret(consumerSecret)
				.setOAuthAccessToken(accessToken)
				.setOAuthAccessTokenSecret(accessTokenSecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}
}
