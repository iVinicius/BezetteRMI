/**
 * 
 */

/**
 * @author Vinicius
 *
 */
public class BezetteImpl {

	public static void main(String[] args) {
		boolean isServer = args.length > 0 && args[0].equalsIgnoreCase("server") ? true : false;
		
		//TODO: Para testar execute primeiro com o isServer = true, e depois no modo client, isServer = false
		//isServer = false;

		if(isServer){
			BezetteServer server = new BezetteServer();
			server.startServer();
		} else{
			BezetteClient client = new BezetteClient();
			client.startClient();
		}
	}

}