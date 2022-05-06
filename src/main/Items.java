package main;

public class Items extends Purchasable{
	
	public Items(String name, int purchase, int sellBack, String description) {
		super.setName(name);
		super.setPurchasePrice(purchase);
		super.setSellBackPrice(sellBack);
		super.setDescription(description);
	}

}
