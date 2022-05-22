package main;

import java.util.ArrayList;

/** A class in which the items and monsters that are bought from the shop have their details set and will extend **/
public abstract class Purchasable {
	/** The name of the monster or item in the shop **/
	private String name;
	/** The purchase price for the monster or item in the shop **/
	private int purchasePrice;
	/** The sell price for the monster or item in the shop **/
	private int sellBackPrice;
	/** The description for the monster or item in the shop **/
	private String description;
	
	
	/** A method in which the name of the Item is set that is to be sold in the shop. Takes a parameter 'itemName' of type String **/ 
	public void setName(String itemName) {
		name = itemName;
	}
	
	/** A method in which the name of the Item is returned **/
	public String getName() {
		return name;
	}
	
	/** A method in which the purchase price of the Item or Monster that is to be sold in the shop is set. Takes a parameter 'purchase' of type int**/
	public void setPurchasePrice(int purchase) {
		purchasePrice = purchase;
	}
	
	/** A method in which the purchase price of the Item or Monster is returned **/
	public int getPurchasePrice() {
		return purchasePrice;
	}
	
	/** A method in which the sell back price of the Item or Monster is set. Takes a parameter 'sell' of type int **/
	public void setSellBackPrice(int sell) {
		sellBackPrice = sell;
	}
	
	/** A method in which the sell back price of the Item or Monster is returned **/
	public int getSellBackPrice() {
		return sellBackPrice;
	}
	
	/** A method in which the description of the Item or Monster is set. Takes a parameter 'descrip' of type String **/
	public void setDescription(String descrip) {
		description = descrip;
	}
	
	/** A method in which the description of the Item or Monster is returned **/
	public String getDescription() {
		return description;
	}
	
	/** A method which is the template for the items to be sold in the shop **/
	public String toString() {
		return String.format("Name: %s \nPrice: %s \nSell-back Price: %s \nDescription: %s \n", name, purchasePrice, sellBackPrice, description);
	}
	
	

}
