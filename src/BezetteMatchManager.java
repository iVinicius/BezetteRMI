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
	
	public BezetteMatchManager() throws RemoteException{
		matchList = new ArrayList<>();
		
		matchList.add(new Bezette());
	}

	@Override
	public int registraJogador(String nomeDoJogador) throws RemoteException {
		Player newPlayer = new Player();
		newPlayer.setId(Player.getNextPlayerSeqNum());
		newPlayer.setName(nomeDoJogador);
		
		registerPlayerInAMatch(newPlayer);
		
		return newPlayer.getId();
	}

	@Override
	public int temPartida(int idUsuario) throws RemoteException {
		Bezette match = findPlayerMatch(idUsuario);
		
		if(match == null || !match.isReady()){
			return 0;
		}
		
		boolean isFirst = isPlayerStartPlaying(match, idUsuario);
		
		return isFirst ? 1 : 2;
	}

	@Override
	public int ehMinhaVez(int idUsuario) throws RemoteException {
		Bezette match = findPlayerMatch(idUsuario);
		
		if(match == null){
			return -2;
		}
		
		//TODO: resto da implementação. vencedores....
		
		int nextToPlay = match.nextToPlay();
		if(nextToPlay == idUsuario){
			return 1;
		} else{
			return 0;
		}
	}

	@Override
	public String obtemTabuleiro(int idUsuario) throws RemoteException {
		Bezette match = findPlayerMatch(idUsuario);	
		
		return match.toString();
	}

	@Override
	public String obtemOponente(int idUsuario) throws RemoteException {
		Bezette match = findPlayerMatch(idUsuario);
		for(Player p : match.getPlayers()){
			if(p.getId() != idUsuario){
				return p.getName();
			}
		}
		return "Você não possui oponentes ainda.";
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
	
	private Bezette findPlayerMatch(int playerid){
		for(Bezette bzt : matchList){
			for(Player p : bzt.getPlayers()){
				if(p.getId() == playerid) return bzt;
			}
		}
		
		return null;
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
	
	private boolean isPlayerStartPlaying(Bezette match, int playerId){
		return match.nextToPlay() == playerId;
	}
}