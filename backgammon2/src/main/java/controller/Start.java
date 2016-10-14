/**
 * 
 */
package controller;

/**
 * @author philipp
 *
 */

import model.*;

public class Start extends State {
	
	private boolean beenhere = false;

	public Start(GameEngine engine) {
		super(engine);
	}


	public void onClickDice()
	{
		// Mensch würfelt
		engine.rollSingleDice();
		// AI würfelt
		engine.nextPlayer();
		engine.rollSingleDice();
		if(engine.getDice().getValue(0) > engine.getDice().getValue(1))
		{
			engine.nextPlayer();
		}
		engine.setState(new PickStart(engine));
	}

}
