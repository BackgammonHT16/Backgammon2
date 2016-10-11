/**
 * 
 */
package controller;

import model.Dice;

/**
 * @author philipp
 *
 */
public class Start2 extends State {

	public Start2(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		Dice d = engine.getPlayer().rollSingleDice();
		if(d != null)
		{
			engine.setState(new Compare(engine));
		}
	}

}
