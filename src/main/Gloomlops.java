package main;
/**A subclass of monster this can be thought as a particular type of monster, has its own unique instance variables which are different from the other Monster subclasses**/
public class Gloomlops extends Monster{
	/**A constructor sets this monsters name to be the parameter name it will then set the instance variables of its superclass Monster, takes a String variable name as its parameter**/

	public Gloomlops(String name){
		super(name);
		setter();
	}
	/**An overloaded constructor called when there is no String in the parameter when this object is made, specifically its used when the user doesn't want to name this monster**/ 
	public Gloomlops() {
		super("Gloomlops");
		setter();
	}
	/**An instance method used to change some of the instance variables found in its super class Monster**/
	public void setter() {
		super.setCurrentHealth(1);
		super.setDamageAmount(40);
		super.setMaxHealth(1000);
		super.sethealAmount(999);
		super.setPurchasePrice(45);
		super.setSellBackPrice(0);
		super.setDescription("An incredibly rare monster that has a large amount of heal at night.");
	}	

}
