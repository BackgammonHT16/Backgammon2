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
		if(player.getId().equals("0"))
		{
			if(place.getId().equals("point18") ||
					place.getId().equals("point19") ||
					place.getId().equals("point20") ||
					place.getId().equals("point21") ||
					place.getId().equals("point22") ||
					place.getId().equals("point23") ||
					place.getId().equals("goal0"))
			{
				return true;
			}
		}
		if(player.getId().equals("1"))
		{
			if(place.getId().equals("point0") ||
					place.getId().equals("point1") ||
					place.getId().equals("point2") ||
					place.getId().equals("point3") ||
					place.getId().equals("point4") ||
					place.getId().equals("point5") ||
					place.getId().equals("goal1"))
			{
				return true;
			}
		}
		return false;
	}

	public boolean isInGoal() {
		if(player.getId().equals("0"))
		{
			if(place.getId().equals("goal0"))
			{
				return true;
			}
		}
		if(player.getId().equals("1"))
		{
			if(place.getId().equals("goal1"))
			{
				return true;
			}
		}
		return false;
	}
}
