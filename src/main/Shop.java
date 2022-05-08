package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Shop {
	/**an array of items that will be sold in the shop **/
	private static Items[] items = new Items[] {new HealthPotion(), new AttackPotion(), new MaxHealthPotion(), new LvlUpPotion()};
	/** an array of monsters that will be sold in the shop**/
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
		if (player.getMonsters().size() < 4) {
			int coins = player.getGold();
			int price = monster.getPurchasePrice();
			System.out.println(price);
			if (coins >= price) {
				player.setGold(coins- price);
				player.addMonster(monster);
				System.out.println(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
			} else {
				System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", price-coins));
			}	
		} else {
			System.out.println("You are allowed a maximum of 4 monsters and you either have 4 or more.");
		}
	}
	
	public void buy(Player player, Items item) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		if (coins >= price) {
			player.setGold(coins- price);
			player.addItem(item);
			System.out.println(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", price-coins));
		}	
	}
	
	public void sell(Player player, Items item) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		player.setGold(coins+price);
		player.removeItem(item);
		System.out.println(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	
	public void sell(Player player, Monster monster) {
		int coins = player.getGold();
		int price = monster.getPurchasePrice();
		player.setGold(coins+price);
		player.removeMonster(monster);
		System.out.println(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	
	public void shopMenu(Player player) {
		boolean picked = false;
		int input = 0;
		System.out.println(String.format("Welcome to the Shop! Current gold = %s\nSelect 0 to display Items in Shop. Select 1 to display Monsters in Shop.", player.getGold()));
		while (picked == false) {
			input = Environment.getUserInt();
			if (input < 0|| input > 1) {
				System.out.println("Invalid Input");
		}
		else {
			picked = true;
		}
		}
		// for viewing items//
		if (input == 0) {
			displayItems();
			System.out.println("Input 0 to buy Item.\nInput 1 to sell Item.\nInput 2 to go back.");
			picked = false;
			while (picked == false) {
				input = Environment.getUserInt();
				if (input < 0|| input > 2) {
					System.out.println("Invalid Input");
			}
			else {
				picked = true;
			}
			}
			// for buying the item//
			if (input == 0) {
				int i = 0;
				for (Items item : items) {
					System.out.println(String.format("%s. %s", i, item));
					i++;
				}
				System.out.println("Select the item you want to buy.");
				picked = false;
				while (picked == false) {
					input = Environment.getUserInt();
					if (input < 0|| input > 4) {
						System.out.println("Invalid Input");
				}
				else {
					picked = true;
				}
				System.out.print(input);
				buy(player, (Items) Array.get(items, input));
				}
			//want to sell item//
			}
			if (input == 1){
				int i = 0;
				for (Items item : player.getItems()) {
					System.out.println(String.format("%s. %s", i, item));
					i++;
				}
				System.out.println("Select the item you want to sell.");
				picked = false;
				while (picked == false) {
					input = Environment.getUserInt();
					if (input < 0|| input > 4) {
						System.out.println("Invalid Input");
				}
				else {
					picked = true;
				}
				}
				sell(player, (Items) Array.get(items, input));
			}
			//for going back//
			if (input == 2) {
				shopMenu(player);
			}
		//for viewing monsters//
		}else {
			displayMonsters();
			System.out.println("Input 0 to buy Monster.\nInput 1 to sell Monster\nInput 2 to go back.");
			picked = false;
			while (picked == false) {
				input = Environment.getUserInt();
				if (input < 0|| input > 2) {
					System.out.println("Invalid Input");
			}
			else {
				picked = true;
			}
			}
			//for buying monsters//
			if (input == 0) {
				int i = 0;
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
			}
			//for selling monsters//
			if (input == 1) {
				int i = 0;
				for (Monster monster : player.getMonsters()) {
					System.out.println(String.format("%s. %s", i, monster));
					i++;
				}
				System.out.println(player.getMonsters());
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
				sell(player, (Monster) Array.get(monsters, input));
				
			}
			//for going back//
			if (input == 2) {
				shopMenu(player);
			}
		}
	}
}
