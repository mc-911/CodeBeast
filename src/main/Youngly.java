package main;

public class Youngly extends Monster{
	public Youngly() {
		super("Youngly");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(100);
		super.setDamageAmount(100);
		super.setMaxHealth(100);
		super.sethealAmount(50);
		super.setPurchasePrice(45);
		super.setSellBackPrice(0);
		super.setDescription("An incredibly rare monster that has a large amount of attack.");
	}	

}
