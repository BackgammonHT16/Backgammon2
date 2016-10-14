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
		this.place = place;
	}
	
	public boolean isInHomeField() 
	{
		if(player.getId() == "0")
		{
			if(place.getId() == "point18" ||
					place.getId() == "point19" ||
					place.getId() == "point20" ||
					place.getId() == "point21" ||
					place.getId() == "point22" ||
					place.getId() == "point23" ||
					place.getId() == "goal0")
			{
				return true;
			}
		}
		if(player.getId() == "1")
		{
			if(place.getId() == "point0" ||
					place.getId() == "point1" ||
					place.getId() == "point2" ||
					place.getId() == "point3" ||
					place.getId() == "point4" ||
					place.getId() == "point5" ||
					place.getId() == "goal1")
			{
				return true;
			}
		}
		return false;
	}

	public boolean isInGoal() {
		if(player.getId() == "0")
		{
			if(place.getId() == "goal0")
			{
				return true;
			}
		}
		if(player.getId() == "1")
		{
			if(place.getId() == "goal1")
			{
				return true;
			}
		}
		return false;
	}
}
