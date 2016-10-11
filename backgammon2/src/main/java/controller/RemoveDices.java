/**
 * 
 */
package controller;

/**
 * @author philipp
 *
 */
public class RemoveDices extends State {

	public RemoveDices(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		engine.executeMove();
		if(engine.won())
		{
			engine.finishGame();
		}
		else if(engine.diceLeft())
		{
			engine.setState(new PickStart(engine));
		}
		else
		{
			engine.nextPlayer();
			engine.setState(new Role(engine));
		}
		
	}
}
