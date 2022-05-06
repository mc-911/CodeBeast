

public class Vesuvius extends Monster {
	public Vesuvius(String name){
		super(name);
		setter();
	}
	public Vesuvius() {
		super("Vesuvius");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(100);
		super.setDamageAmount(15);
		super.setMaxHealth(100);
		super.sethealAmount(1);
	}
	
}
