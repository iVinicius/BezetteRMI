import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Vinicius
 *
 */
public class Bezette extends UnicastRemoteObject implements BezetteInterface{

	private static final long serialVersionUID = 4121368241746762230L;
	
	public Bezette() throws RemoteException{
		
	}

	@Override
	public int registraJogador(String nomeDoJogador) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int temPartida(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ehMinhaVez(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String obtemTabuleiro(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String obtemOponente(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String jogaDados(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int encerraPartida(int idUsuario) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

}