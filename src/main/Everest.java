package main;

/**A subclass of monster this can be thought as a particular type of monster, has its own unique instance variables which are different from the other Monster subclasses**/
public class Everest extends Monster{
	/**A constructor sets this monsters name to be the parameter name it will then set the instance variables of its superclass Monster, takes a String variable name as its parameter**/

	public Everest(String name){
		super(name);
		setter();
	}
	/**An overloaded constructor called when there is no String in the parameter when this object is made, specifically its used when the user doesn't want to name this monster**/ 
	public Everest() {
		super("Everest");
		setter();
	}
	/**An instance method used to change some of the instance variables found in its super class Monster**/
	public void setter() {
		super.setCurrentHealth(250);
		super.setDamageAmount(20);
		super.setMaxHealth(250);
		super.sethealAmount(5);
		super.setPurchasePrice(25);
		super.setSellBackPrice(5);
		super.setDescription("A monster as impevious as the mountain it is named after.");
	}	
}
