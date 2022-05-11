package main;

public class Gloomlops extends Monster{
	public Gloomlops() {
		super("Gloomlops");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(1);
		super.setDamageAmount(25);
		super.setMaxHealth(1000);
		super.sethealAmount(999);
		super.setPurchasePrice(45);
		super.setSellBackPrice(0);
		super.setDescription("An incredibly rare monster that has a large amount of heal.");
	}	

}
