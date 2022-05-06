
import java.util.*;
public class suddenLevel extends RandomEvent {
	/**An event where the level of one of the player's monsters increases**/
	public void startEvent(Player player) {
		Random random = new Random();
		int picked_i = random.nextInt(player.getMonsters().size());
		System.out.println("You check on one of your monsters during the night...");
		player.getMonster(picked_i).levelUp();
		
		
	}
	

}
