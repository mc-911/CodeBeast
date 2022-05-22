package test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import main.Battle;
import main.Items;
import main.Monster;
import main.Player;
import main.Shop;

class BattleTest {
	private Battle testBattle;
	private Player testPlayer;
	private Shop testItem;
	
	/** will check if the player name is valid **/
	@Test
	public void testPlayerName() {
		boolean testLength = Player.checkName("Adama");
		boolean testCharacter = Player.checkName("4dama/^");
		assertTrue(testLength);
		assertFalse(testCharacter);
	}
	
	/** will check if there are more than 3 items**/
	@Test
	public void testItemAmountShop() {
		testItem = new Shop();
		Items[] items = testItem.getItems();
		assertEquals(4, items.length);
		
		
	}
	
	
	/** will check if the monsters the user has to battle are 5 **/
	@Test
	public void testBattleAmountMonsters() {
		testBattle = new Battle();
		ArrayList<Monster> monsters = testBattle.getFoeMonsters();
		assertEquals(5, monsters.size());
	}
	
	
}