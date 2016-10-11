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
	
	
	public Checker(Player player, Place place)
	{
		this.player = player;
		this.place = place;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public Place getPlace()
	{
		return place;
	}
}
