package main;

/**A subclass of monster this can be thought as a particular type of monster, has its own unique instance variables which are different from the other Monster subclasses**/
public class Flame extends Monster{
	/**A constructor sets this monsters name to be the parameter name it will then set the instance variables of its superclass Monster, takes a String variable name as its parameter**/

	public Flame(String name){
		super(name);
		setter();
	}
	/**An overloaded constructor called when there is no String in the parameter when this object is made, specifically its used when the user doesn't want to name this monster**/ 
	public Flame() {
		super("Flame");
		setter();
	}
	/**An instance method used to change some of the instance variables found in its super class Monster**/
	public void setter() {
		super.setCurrentHealth(150);
		super.setDamageAmount(25);
		super.setMaxHealth(150);
		super.sethealAmount(25);
		super.setPurchasePrice(20);
		super.setSellBackPrice(10);
		super.setDescription("A fiery monster as explosive as it's name.");
	}	
	

}
