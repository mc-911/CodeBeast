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
	
	public Shop() {
		rareMonster();
	}
	/** a method that will print out the available items in the shop **/
	public void displayItems(GameWindow window) {
		for (Items item: items) {
			window.printMsg(item.toString());
		}
	}
	
	/** a method that will print out the available monsters in the shop **/
	public void displayMonsters(GameWindow window) {
		for (Monster monster: monsters) {
			if (monster.getBrought() == false) {
			window.printMsg(monster.toString());
			}
			else {
				window.printMsg("Brought: " + monster.toString());
			}
		}
	}
	public Monster[] getMonsters() {
		return monsters;
	}
	public Items[] getItems() {
		return items;
	}
	/** a method that will buy a monster that the player selects **/
	public boolean buy(Player player, Monster monster, GameWindow window) {
		if (player.getMonsters().size() < 4) {
			int coins = player.getGold();
			int price = monster.getPurchasePrice();
			if (coins >= price) {
				player.setGold(coins- price);
				window.printMsg(String.format("Succesfully bought the Monster. Your gold balance is now %s", player.getGold()));
				return true;	
					
			} else {
				window.printMsg(String.format("Insufficient funds, can't buy this Monster. \nNeed %s more gold.", price-coins));
				return false;
			}	
		} else {
			window.printMsg("You are allowed a maximum of 4 monsters and you either have 4 or more.");
			return false;
		}
	}
	
	/** a method that will buy an item that the player selects **/
	public boolean buy(Player player, Items item, GameWindow window) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		if (coins >= price) {
			player.setGold(coins- price);
			player.addItem(item);
			window.printMsg(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
			return true;
		} else {
			window.printMsg(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", price-coins));
			return false;
		}	
	}
	
	/** a method that will sell an item that the player selects **/
	public void sell(Player player, Items item, GameWindow window) {
		int coins = player.getGold();
		int price = item.getPurchasePrice();
		player.setGold(coins+price);
		player.removeItem(item);
		window.printMsg(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
	}
	
	/** a method that will sell a monster that the player selects **/
	public void sell(Player player, Monster monster, GameWindow window) {
		int coins = player.getGold();
		int price = monster.getPurchasePrice();
		player.setGold(coins+price);
		player.removeMonster(monster);
		window.printMsg(String.format("Succesfully bought the item. Your gold balance is now %s", player.getGold()));
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

	
	}


