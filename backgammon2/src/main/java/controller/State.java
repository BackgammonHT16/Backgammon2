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
	
	public abstract void nextAction();
	
	public void onClickPlace()
	{
		
	}
	
	public void onClickMenu()
	{
		
	}
	
	public void onClickDice()
	{
		
	}
	
}
