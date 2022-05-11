package main;

public class Goolops extends Monster{
	public Goolops() {
		super("Goolops");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(500);
		super.setDamageAmount(30);
		super.setMaxHealth(500);
		super.sethealAmount(50);
		super.setPurchasePrice(35);
		super.setSellBackPrice(0);
		super.setDescription("An incredibly rare monster that has a large amount of health.");
	}	

}
