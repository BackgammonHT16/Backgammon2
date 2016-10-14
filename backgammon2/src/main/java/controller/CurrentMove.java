/**
 * 
 */
package controller;

import model.Place;

/**
 * @author philipp
 *
 */
public class CurrentMove {
	private GameEngine engine;
	private boolean selectStart = false;
	private boolean selectEnd = false;
	private Place start;
	private Place end;
	
	public CurrentMove(GameEngine engine)
	{
		this.engine = engine;
	}
	
	public boolean onClick(String p)
	{
		Place place = engine.getPlace(p);
		if(selectStart)
		{
			if(engine.isLegalStartPlace(place, false))
			{
				start = place;
				selectStart = false;
				return true;
			}
		}
		else if(selectEnd)
		{
			if(engine.isLegalEndPlace(place))
			{
				end = place;
				selectEnd = false;
				return true;
			}
		}
		return false;
	}
	
	public void activateSelectStart()
	{
		selectStart = true;
	}
	
	public void activateSelectEnd()
	{
		selectEnd = true;
	}
	
	public Place getStart()
	{
		return start;
	}

	public Place getEnd()
	{
		return end;
	}
}
