package main;


public class Parihaka extends Monster{
	public Parihaka(String name){
		super(name);
		setter();
	}
	public Parihaka() {
		super("Parihaka");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(50);
		super.setDamageAmount(30);
		super.setMaxHealth(50);
		super.sethealAmount(5);
		super.setPurchasePrice(10);
		super.setSellBackPrice(5);
		super.setDescription("Sexy dark man.");
	}	

}
