package main;

/**An abstract class that has subclasses that are random events and all must implement the method startEvent**/
public abstract class RandomEvent {
	/**An abstract method which all subclasses of RandomEvent must implement their different implementations is what creates "Random  Events", takes a Monster variable mon, and a Player variable player as its parameters, returns void**/
	abstract public void startEvent(Monster mon, Player player, GameWindow window);

	

}
