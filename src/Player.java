/**
 * 
 */

/**
 * @author Vinicius
 *
 */
public class Player {

	private int id;
	
	private String name;
	
	public static int PLAYER_SEQ_NUM = 1;
	
	public static int getNextPlayerSeqNum(){
		return PLAYER_SEQ_NUM++;
	}

	public Player(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Player(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}