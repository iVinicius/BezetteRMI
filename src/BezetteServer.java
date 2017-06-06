import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * 
 */

/**
 * @author Vinicius
 *
 */
public class BezetteServer {

	public void startServer(){
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
			System.out.println("BezetteServer RMI registry ready.");			
		} catch (RemoteException e) {
			System.out.println("BezetteServer RMI registry already running.");			
		}
		try {
			Naming.rebind ("Bezette", new Bezette());
			System.out.println ("BezetteServer is ready.");
		} catch (Exception e) {
			System.out.println ("BezetteServer failed:");
			e.printStackTrace();
		}
	}
}
