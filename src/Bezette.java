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
	
	public Bezette(){
		this.ringsP1 = 12;
		this.ringsP2 = 12;
		this.hasteTotal = 0;
	}
	
	public boolean isReady(){
		return player1 != null && player2 != null ? true : false;
	}
	
	public void registerPlayer(Player player){
		if(player1 != null){
			player1 = player;
		} else{
			player2 = player;
		}
	}
}