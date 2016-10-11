/**
 * 
 */
package controller;

/**
 * @author philipp
 *
 */

import model.*;

public class Start1 extends State {

	public Start1(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		Dice d = engine.getPlayer().rollSingleDice();
		if(d != null)
		{
			engine.setState(new Start2(engine));
			engine.nextPlayer();
		}
	}

}
