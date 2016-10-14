/**
 * 
 */
package controller;

/**
 * @author philipp
 *
 */
public abstract class State {
	protected GameEngine engine;
	
	public State(GameEngine engine)
	{
		this.engine = engine;
	}
	
	public void onClickPlace(String placeId)
	{
		
	}
	
	public void onClickMenu()
	{
		
	}
	
	public void onClickDice()
	{
		
	}
	
}
