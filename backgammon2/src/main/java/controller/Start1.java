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
	
	private boolean beenhere = false;

	public Start1(GameEngine engine) {
		super(engine);
	}

	@Override
	public void nextAction() {
		if(!beenhere)
		{
			beenhere = true;
			System.out.println("Start1");
		}
		Dice d = engine.getPlayer().rollSingleDice();
		if(d != null)
		{
			engine.setState(new Start2(engine));
			engine.nextPlayer();
		}
	}

}
