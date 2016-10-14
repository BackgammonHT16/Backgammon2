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
		engine.setState(new Menu(engine, engine.getCurrentState()));
	}
	
	public void onClickDice()
	{
		
	}

	public void onCloseMenu()
	{

	}
	
}
