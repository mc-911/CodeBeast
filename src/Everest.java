

public class Everest extends Monster{
	public Everest(String name){
		super(name);
		setter();
	}
	public Everest() {
		super("Everest");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(250);
		super.setDamageAmount(25);
		super.setMaxHealth(250);
		super.sethealAmount(5);
	}	
}
