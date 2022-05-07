package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
	private static Items[] items = new Items[] {new HealthPotion(), new AttackPotion(), new MaxHealthPotion(), new LvlUpPotion()};
	Monster[] monsters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	
	public void displayItems() {
		for (Items item: items) {
			System.out.println(item);
		}
	}
	
	public void displayMonsters() {
		for (Monster monster: monsters) {
			System.out.println(monster);
		}
	}
	
	public void buy(Player player, Monster monster) {
		int coins = player.getGold();
		int price = monster.getPurchasePrice();
		if (coins >= price) {
			player.setGold(coins- price);
			player.addMonster(monster);
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 50-coins));
		}	
	}
	
	public void buy(Player player, Items item) {
		int coins = player.getGold();
		if (coins >= 50) {
			player.setGold(coins- 50);
			player.addItem(item);
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 50-coins));
		}	
	}
	
	public void sell(Player player, Items item) {
		int coins = player.getGold();
		player.setGold(coins+25);
		player.removeItem(item);
	}
	
	public void sell(Player player, Monster monster) {
		int coins = player.getGold();
		player.setGold(coins+25);
		player.removeMonster(monster);
	}
	
	public void shopMenu(Player player) {
		int i = 0;
		System.out.println(String.format("Current gold = %s", player.getGold()));
		displayItems();
		displayMonsters();
		System.out.println("Input 0 to buy a monster.\nInput 1 to sell a monster.\nInput 2 to buy items.\nInput 3 to sell items.");
		boolean picked = false;
		int input = 0;
		while (picked == false) {
			input = Environment.getUserInt();
			if (input < 0|| input > 4) {
				System.out.println("Invalid Input");
		}
		else {
			picked = true;
		}
		}
		switch (input) {
		case 0:
			for (Monster monster : monsters) {
				System.out.println(String.format("%s. %s", i, monster));
				i++;
			}
			System.out.println("Select the monster you want to buy.");
			picked = false;
			while (picked == false) {
				input = Environment.getUserInt();
				input = input -1;
				if (input < 0|| input > 4) {
					System.out.println("Invalid Input");
			}
			else {
				picked = true;
			}
			}
			buy(player, (Monster) Array.get(monsters, input));
			break;
		case 1:
			for (Monster monster : player.getMonsters()) {
				System.out.println(String.format("%s. %s", i, monster));
				i++;
			}
			System.out.println("Select the monster you want to sell.");
			picked = false;
			while (picked == false) {
				input = Environment.getUserInt();
				input = input -1;
				if (input < 0|| input > 4) {
					System.out.println("Invalid Input");
			}
			else {
				picked = true;
			}
			}
			break;
		case 2:
			for (Items item : items) {
				System.out.println(String.format("%s. %s", i, item));
				i++;
			}
			System.out.println("Select the item you want to buy.");
			picked = false;
			while (picked == false) {
				input = Environment.getUserInt();
				input = input -1;
				if (input < 0|| input > 4) {
					System.out.println("Invalid Input");
			}
			else {
				picked = true;
			}
			}
			break;
		case 3:
			for (Items item : player.getItems()) {
				System.out.println(String.format("%s. %s", i, item));
				i++;
			}
			System.out.println("Select the item you want to sell.");
			picked = false;
			while (picked == false) {
				input = Environment.getUserInt();
				input = input -1;
				if (input < 0|| input > 4) {
					System.out.println("Invalid Input");
			}
			else {
				picked = true;
			}
			}
			break;
			
			
	}
	}
	
	

}
