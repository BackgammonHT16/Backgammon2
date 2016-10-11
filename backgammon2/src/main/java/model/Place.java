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

public abstract class Place {
	private List<Checker> checkers = new ArrayList<Checker>();
	
	public void addChecker(Checker checker)
	{
		if(checker != null)
		{
			checkers.add(checker);
		}
	}
	
	public Checker removeChecker()
	{
		if(checkers.isEmpty())
		{
			return null;
		}
		else
		{
			return checkers.remove(0);
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
			return checkers.get(0).getPlayer();
		}
		return null;
	}
}
