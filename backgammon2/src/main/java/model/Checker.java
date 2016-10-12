/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */


import java.util.ArrayList;
import java.util.List;



public class Checker {
	private Place place;
	private Player player;
	final String id;
	
	
	public Checker(Player player, Place place, String id)
	{
		this.player = player;
		this.place = place;
		this.id = id;
		place.register(this);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Place getPlace()
	{
		return place;
	}

	public void moveTo(Place place) {
		this.place.unregister(this);
		place.register(this);
	}
}
