package main;
/**A subclass of monster this can be thought as a particular type of monster, has its own unique instance variables which are differnt from the other Monster subclasses**/
public class Goolops extends Monster{
	/**A constructor sets this monsters name to be the parameter name it will then set the instance variables of its superclass Monster, takes a String variable name as its parameter**/

	public Goolops(String name){
		super(name);
		setter();
	}
	/**An overloaded constructor called when there is no String in the parameter when this object is made, specifically its used when the user doesn't want to name this monster**/ 
	public Goolops() {
		super("Goolops");
		setter();
	}
	/**An instance method used to change some of the instance variables found in its super class Monster**/
	public void setter() {
		super.setCurrentHealth(850);
		super.setDamageAmount(45);
		super.setMaxHealth(850);
		super.sethealAmount(50);
		super.setPurchasePrice(65);
		super.setSellBackPrice(0);
		super.setDescription("An incredibly rare monster that has a large amount of health.");
	}	

}
