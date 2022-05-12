package main;

/**A subclass of monster this can be thought as a particular type of monster, has its own unique instance variables which are differnt from the other Monster subclasses**/
public class Parihaka extends Monster{
	/**A constructor sets this monsters name to be the parameter name it will then set the instance variables of its superclass Monster, takes a String variable name as its parameter**/
	public Parihaka(String name){
		super(name);
		setter();
	}
	/**An overloaded constructor called when there is no String in the parameter when this object is made, specifically its used when the user doesn't want to name this monster**/ 
	public Parihaka() {
		super("Parihaka");
		setter();
	}
	/**An instance method used to change some of the instance variables found in its super class Monster**/
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
