package main;


public class Flame extends Monster{
	public Flame(String name){
		super(name);
		setter();
	}
	public Flame() {
		super("Flame");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(200);
		super.setDamageAmount(10);
		super.setMaxHealth(200);
		super.sethealAmount(10);
		super.setPurchasePrice(10);
		super.setSellBackPrice(5);
		super.setDescription("Delightful young man.");
	}	
	

}
