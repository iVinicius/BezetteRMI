import java.util.ArrayList;
import java.util.Random;

/**
 * @author Vinicius
 *
 */
public class Bezette {
	
	private Player player1;
	
	private Player player2;
	
	private int ringsP1;
	
	private int ringsP2;

	private int hasteTotal;
	
	private int nextToPlay;
	
	public Bezette(){
		this.ringsP1 = 12;
		this.ringsP2 = 12;
		this.hasteTotal = 0;
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
		list.add(player1);
		list.add(player2);
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
		
		
		return null;
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
}