/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import view.GChecker;

public abstract class Place {
	private ArrayList<Checker> checkers = new ArrayList<Checker>();
	private Player owner;
	final String id;
	
	public String getId()
	{
		return id;
	}
	public Place(String id)
	{
		this.id = id;
		owner = null;
	}

	public Place(Player player, String id)
	{
		this.id = id;
		owner = player;
	}
	
	public abstract int getIntId();
	
	public void register(Checker checker)
	{
		if(checker != null)
		{
			checkers.add(checker);
			owner = checker.getPlayer();
			System.out.println("MODEL Checker " + checker.id + " at " + this.id + 
					" registered. Current size is " + checkers.size());
		}
	}
	
	public void unregister(Checker checker)
	{
		if(!checkers.isEmpty())
		{
			checkers.remove(checker);
			System.out.println("MODEL Checker " + checker.id + " from " + this.id + 
					" removed. Current size is " + checkers.size());
		}
	}
	
	public int size()
	{
		return checkers.size();
	}

	public Player getOwner()
	{
		if(size() > 0)
		{
			return owner;
		}
		return null;
	}

	public void moveCheckerTo(Place place) {
		getLastChecker().moveTo(place);
	}
	
	private Checker getLastChecker()
	{
		return checkers.get(checkers.size() - 1);
	}
}
