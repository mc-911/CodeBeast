package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/** The shop class will have the place where the player can buy and sell monsters and items **/

public class Shop {
	/** an array of items that will be sold in the shop **/
	private static Items[] items = new Items[] {new HealthPotion(), new AttackPotion(), new MaxHealthPotion(), new LvlUpPotion()};
	/** an array of monsters that will be sold in the shop**/
	Monster[] monsters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	
	/** a method that will print out the available items in the shop **/
	public void displayItems() {
		for (Items item: items) {
			Environment.printMsg(item.toString());
		}
	}
	
	/** a method that will print out the available monsters in the shop **/
	public void displayMonsters() {
		for (Monster monster: monsters) {
			Environment.printMsg(monster.toString());
		}
	}
	
	/** a method that will buy a monster that the player selects **/
	public void buy(Player player, Monster monster) {
		if (player.getMonsters().size() < 4) {
			int coins = player.getGold();
			int price = monster.getPurchasePrice();
			if (coins >= price) {
				player.setGold(coins- price);
				Environment.printMsg(String.format("Succesfully bought the Monster. Your gold balance is now %s", player.getGold()));
				Environment.printMsg("Would you like to name your new monster?\nInput 0 for yes, or 1 for no");
				Environment.addMon(player, monster);
				
					
					
			} else {
				Environment.printMsg(String.format("Insufficient funds, can't buy this Monster. \nNeed %s more gold.", price-coins));
			}	
		} else {
			Environment.printMsg("You are allowed a maximum of 4 monsters and you either have 4 or more.");
		}
	}
	
	/** a method that will buy an item that the player selects **/
	public void buy(Player player, Items item) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		if (coins >= price) {
			player.setGold(coins- price);
			player.addItem(item);
			Environment.printMsg(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
		} else {
			Environment.printMsg(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", price-coins));
		}	
	}
	
	/** a method that will sell an item that the player selects **/
	public void sell(Player player, Items item) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		player.setGold(coins+price);
		player.removeItem(item);
		Environment.printMsg(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	
	/** a method that will sell a monster that the player selects **/
	public void sell(Player player, Monster monster) {
		int coins = player.getGold();
		int price = monster.getPurchasePrice();
		player.setGold(coins+price);
		player.removeMonster(monster);
		Environment.printMsg(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	
	/** adds a rarity system to the shop **/
	public void rareMonster() {
		Random rand = new Random();
		Goolops goolops = new Goolops();
		Youngly youngly = new Youngly();
		Gloomlops gloomlops = new Gloomlops();
		int num = rand.nextInt(20);
		boolean occured =false;
		Monster[] rareMonsters = {goolops, goolops, goolops, goolops, youngly, youngly, youngly, youngly, gloomlops, gloomlops};
		if (num < rareMonsters.length) {
			monsters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry(), rareMonsters[num]};
		}
	}
	
	/** a method that has the shop menu. the player will select the item or monster they want to buy. they can also view their inventory **/
	public void shopMenu(Player player) {
		rareMonster();
		boolean picked = false;
		int input = 0;
		Environment.printMsg(String.format("Welcome to the Shop! Current gold = %s\nSelect 0 to display Items in Shop. Select 1 to display Monsters in Shop.", player.getGold()));
		input = Environment.getUserIntBounds(0, 1);
		if (input == 0) {
			// for viewing items//
			displayItems();
			Environment.printMsg("Input 0 to buy Item.\nInput 1 to sell Item.\nInput 2 to go back.");
			input = Environment.getUserIntBounds(0, 2);
			if (input == 0) {
				// for buying the item//
				Environment.displayList(items);
				Environment.printMsg("Select the item you want to buy.");
				input = Environment.getUserIntBounds(0, 4);
				System.out.print(input);
				buy(player, (Items) Array.get(items, input));
			//want to sell item//
			}
			else if (input == 1){
				Environment.displayList(items);
				Environment.printMsg("Select the item you want to sell.");
				input = Environment.getUserIntBounds(0, player.getItems().size());
				sell(player, (Items) Array.get(items, input));
			}
			//for going back to the main shop menu//
			else if (input == 2) {
				shopMenu(player);
			}
			
		//for viewing monsters//
		}else {
			displayMonsters();
			Environment.printMsg("Input 0 to buy Monster.\nInput 1 to sell Monster\nInput 2 to go back.");
			input = Environment.getUserIntBounds(0, 2);
			//for buying monsters//
			if (input == 0) {
				Environment.printMsg("Buying Monster");
				Environment.displayList(monsters);
				Environment.printMsg("Select the monster you want to buy.");
				input = Environment.getUserIntBounds(0, monsters.length-1);
				buy(player, (Monster) Array.get(monsters, input));
			}
			//for selling monsters//
			else if (input == 1) {
				Purchasable[] arr = player.getMonsters().toArray(new Purchasable[player.getMonsters().size()]);
				Environment.displayList(arr);
				Environment.printMsg("Select the monster you want to sell.");
				input = Environment.getUserIntBounds(0, player.getMonsters().size());
				sell(player, (Monster) Array.get(monsters, input));
			}
			//for going back to the main shop menu//
			else if (input == 2) {
				shopMenu(player);
			}
		}
	}
}

