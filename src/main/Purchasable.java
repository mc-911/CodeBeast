package main;

import java.util.ArrayList;

public abstract class Purchasable {
	private String name;
	private int purchasePrice;
	private int sellBackPrice;
	private String description;
	
	public void setName(String itemName) {
		name = itemName;
	}
	public String getName() {
		return name;
	}
	
	public void setPurchasePrice(int purchase) {
		purchasePrice = purchase;
	}
	public int getPurchasePrice() {
		return purchasePrice;
	}
	
	public void setSellBackPrice(int sell) {
		sellBackPrice = sell;
	}
	public int getSellBackPrice() {
		return sellBackPrice;
	}
	
	public void setDescription(String descrip) {
		description = descrip;
	}
	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return String.format("Name: %s \nPrice: %s \nSell-back Price: %s \nDescription: %s \n", name, purchasePrice, sellBackPrice, description);
	}
	
	

}
