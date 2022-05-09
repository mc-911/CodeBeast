package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
/** The shop class will have the place where the player can buy and sell monsters and items **/

public class Shop {
	/** an array of items that will be sold in the shop **/
	private static Items[] items = new Items[] {new HealthPotion(), new AttackPotion(), new MaxHealthPotion(), new LvlUpPotion()};
	/** an array of monsters that will be sold in the shop**/
	Monster[] monsters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	
	/** a method that will print out the available items in the shop **/
	public void displayItems() {
		for (Items item: items) {
			System.out.println(item);
		}
	}
	
	/** a method that will print out the available monsters in the shop **/
	public void displayMonsters() {
		for (Monster monster: monsters) {
			System.out.println(monster);
		}
	}
	
	/** a method that will buy a monster that the player selects **/
	public void buy(Player player, Monster monster) {
		if (player.getMonsters().size() < 4) {
			int coins = player.getGold();
			int price = monster.getPurchasePrice();
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
	
	/** a method that will buy an item that the player selects **/
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
	
	/** a method that will sell an item that the player selects **/
	public void sell(Player player, Items item) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		player.setGold(coins+price);
		player.removeItem(item);
		System.out.println(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	
	/** a method that will sell a monster that the player selects **/
	public void sell(Player player, Monster monster) {
		int coins = player.getGold();
		int price = monster.getPurchasePrice();
		player.setGold(coins+price);
		player.removeMonster(monster);
		System.out.println(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	public void displayList(Purchasable[] p) {
		int i = 0;
		for (Purchasable d : p) {
			System.out.println(String.format("%s. %s", i, d));
			i++;
		}
		
	}
	/** a method that has the shop menu. the player will select the item or monster they want to buy. they can also view their inventory **/
	public void shopMenu(Player player) {
		boolean picked = false;
		int input = 0;
		System.out.println(String.format("Welcome to the Shop! Current gold = %s\nSelect 0 to display Items in Shop. Select 1 to display Monsters in Shop.", player.getGold()));
		input = Environment.getUserIntBounds(0, 1);
		if (input == 0) {
			// for viewing items//
			displayItems();
			System.out.println("Input 0 to buy Item.\nInput 1 to sell Item.\nInput 2 to go back.");
			input = Environment.getUserIntBounds(0, 2);
			if (input == 0) {
				// for buying the item//
				displayList(items);
				System.out.println("Select the item you want to buy.");
				input = Environment.getUserIntBounds(0, 4);
				System.out.print(input);
				buy(player, (Items) Array.get(items, input));
			//want to sell item//
			}
			if (input == 1){
				displayList(items);
				System.out.println("Select the item you want to sell.");
				input = Environment.getUserIntBounds(0, player.getItems().size());
				sell(player, (Items) Array.get(items, input));
			}
			//for going back to the main shop menu//
			if (input == 2) {
				shopMenu(player);
			}
			
		//for viewing monsters//
		}else {
			displayMonsters();
			System.out.println("Input 0 to buy Monster.\nInput 1 to sell Monster\nInput 2 to go back.");
			input = Environment.getUserIntBounds(0, 2);
			//for buying monsters//
			if (input == 0) {
				displayList(monsters);
				System.out.println("Select the monster you want to buy.");
				input = Environment.getUserIntBounds(0, 4);
				buy(player, (Monster) Array.get(monsters, input));
			}
			//for selling monsters//
			if (input == 1) {
				Purchasable[] arr = player.getMonsters().toArray(new Purchasable[player.getMonsters().size()]);
				displayList(arr);
				System.out.println("Select the monster you want to sell.");
				input = Environment.getUserIntBounds(0, player.getMonsters().size());
				sell(player, (Monster) Array.get(monsters, input));
			}
			//for going back to the main shop menu//
			if (input == 2) {
				shopMenu(player);
			}
		}
	}
}
