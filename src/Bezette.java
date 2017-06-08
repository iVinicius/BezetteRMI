import java.util.ArrayList;
import java.util.Random;

/**
 * @author Vinicius
 *
 */
public class Bezette{
	
	private Player player1;
	
	private Player player2;
	
	private int ringsP1;
	
	private int ringsP2;

	private int hasteTotal;
	
	private int nextToPlay;
	
	private boolean p1Quit;
	
	private boolean p2Quit;
	
	public Bezette(){
		this.ringsP1 = 12;
		this.ringsP2 = 12;
		this.hasteTotal = 0;
		this.p1Quit = false;
		this.p2Quit = false;
	}
	
	public void handleDesistance(int idPlayer){
		boolean isP1 = false;
		if(idPlayer == player1.getId()){
			isP1 = true;
		}
		
		if(isP1){
			p1Quit = true;
		} else{
			p2Quit = true;
		}
	}
	
	public int hasVictor(){
		if(ringsP1 == 0 || p2Quit){
			return player1.getId();
		} else if(ringsP2 == 0 || p1Quit){
			return player2.getId();
		}
		return -1;
	}
	
	public boolean victorDueToWO(){
		return p1Quit || p2Quit;
	}
	
	public boolean isReady(){
		return player1 != null && player2 != null ? true : false;
	}
	
	public void registerPlayer(Player player){
		if(player1 == null){
			player1 = player;
			nextToPlay = player1.getId();
		} else{
			player2 = player;
		}
	}
	
	public int nextToPlay(){
		return nextToPlay;
	}
	
	public ArrayList<Player> getPlayers(){
		ArrayList<Player> list = new ArrayList<>();
		if(player1 != null) list.add(player1);
		if(player2 != null) list.add(player2);
		return list;
	}
	
	public String toString(){
		String aux = "";
		
		if(!isReady()){
			return "Ainda não há partida. Portanto não há tabuleiro.";
		}
		
		aux += String.format("P1(%s): %d | P2(%s): %d || Haste: %d", player1.getId(),ringsP1, player2.getId(), ringsP2, hasteTotal);
		
		return aux;
	}
	
	public String play(int playerid){
		if(nextToPlay() != playerid){
			return null;
		}
		
		ArrayList<Integer> dices = this.randomDices();
		
		this.handlePlay(dices, playerid);
		
		this.changeTurn();
		
		String aux = "";
		for(Integer inte : dices){
			aux += inte + ",";
		}
		
		return aux;
	}
	
	private void handlePlay(ArrayList<Integer> dices, int playerId){
		boolean isP1 = false;
		if(playerId == player1.getId()){
			isP1 = true;
		}
		int one = 0;
		int sixC = 0;
		boolean four = false;
		boolean five = false;
		boolean six = false;
		
		for(Integer inte : dices){
			if(inte == 1) one++;
			else if (inte == 6){
				sixC++;
				six = true;
			}
			else if(inte == 4) four = true;
			else if(inte == 5) five = true;
		}
		
		int rings = 0;
		if(isP1){
			rings = ringsP1;
		} else{
			rings = ringsP2;
		}
		if(four && five && six){
			int allButOne = rings - 1;
			if(isP1){
				ringsP1 -= allButOne;
				ringsP2 += allButOne;
			} else{
				ringsP2 -= allButOne;
				ringsP1 += allButOne;
			}
		}
		
		if(isP1){
			ringsP1 -= one;
			hasteTotal += one;
			ringsP2 += sixC;
		} else{
			ringsP2 -= one;
			hasteTotal += one;
			ringsP1 += sixC;
		}
	}
	
	private ArrayList<Integer> randomDices(){
		ArrayList<Integer> dices = new ArrayList<>();
		
		Random rand = new Random();
		for(int i = 0; i < 3; i++){
			dices.add(rand.nextInt(5) + 1);
		}
		
		return dices;
	}
	
	private void changeTurn(){
		if(nextToPlay == player1.getId()){
			nextToPlay = player2.getId();
		} else{
			nextToPlay = player1.getId();
		}
	}
}