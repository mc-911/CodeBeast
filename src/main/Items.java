 package main;
/** A subclass of Purchasable that sets all the details of the items in the shop **/
public class Items extends Purchasable{
	
	/** The constructor that will set the name, purchase price, sell back price and description of the Item. Takes parameter 'name' and 'description' of type String, parameter 'purchase' and 'sellBack' of type int **/
	public Items(String name, int purchase, int sellBack, String description) {
		super.setName(name);
		super.setPurchasePrice(purchase);
		super.setSellBackPrice(sellBack);
		super.setDescription(description);
	}
	
	public void drinkPotion(Monster monster, Player player) {
	}

}
