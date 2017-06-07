import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Vinicius
 *
 */
public class BezetteMatchManager extends UnicastRemoteObject implements BezetteInterface{

	private static final long serialVersionUID = 4121368241746762230L;
	
	private ArrayList<Bezette> matchList;
	
	private ArrayList<Player> userList;
	
	public BezetteMatchManager() throws RemoteException{
		matchList = new ArrayList<>();
		userList = new ArrayList<>();
		
		matchList.add(new Bezette());
	}

	@Override
	public int registraJogador(String nomeDoJogador) throws RemoteException {
		Player newPlayer = new Player();
		newPlayer.setId(Player.getNextPlayerSeqNum());
		newPlayer.setName(nomeDoJogador);
		
		userList.add(newPlayer);
		registerPlayerInAMatch(newPlayer);
		
		return newPlayer.getId();
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

	private void registerPlayerInAMatch(Player player){
		Bezette match = this.returnFirstAvaialbleMatch();
		match.registerPlayer(player);
	}
	
	private Bezette returnFirstAvaialbleMatch(){
		for(Bezette bzt : matchList){
			if(!bzt.isReady()){
				return bzt;
			}
		}
		Bezette aux = new Bezette();
		matchList.add(aux);
		return aux;
	}
}