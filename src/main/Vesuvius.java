package main;


public class Vesuvius extends Monster {
	public Vesuvius(String name){
		/**Calls the constructor of monster and passes the parameter name into it, then calls setter to set the instance variables found in monster**/
		super(name);
		setter();
	}
	public Vesuvius() {
		/**the same constructor as the one above but used when the user inputs nothing when naming the monster, passes the monster's default name into the Monster constructor**/
		super("Vesuvius");
		setter();
	}
	public void setter() {
		/**Sets the instance variables found in Monster**/
		super.setCurrentHealth(100);
		super.setDamageAmount(15);
		super.setMaxHealth(100);
		super.sethealAmount(1);
	}
	
}
