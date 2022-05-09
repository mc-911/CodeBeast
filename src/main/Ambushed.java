package main;


public class Ambushed extends RandomEvent{
	/**An event where a player is ambushed**/
	public void startEvent(Player player){
		System.out.println("You've been ambushed!");
		Battle battle = new Battle(player);
		battle.startBattle(player, 2, false, 2);
		System.out.println("Funny");
	}

}
