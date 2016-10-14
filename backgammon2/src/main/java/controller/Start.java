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
		if(engine.getDice().getAbsValue(0) > engine.getDice().getAbsValue(1))
		{
			engine.nextPlayer();
		}
		if(engine.getLegalStartPlaces().isEmpty())
		{
			System.out.println("No moves available!");
			engine.nextPlayer();
			engine.setState(new Role(engine));
		}
		else
		{
			engine.setState(new PickStart(engine));
		}
	}

}
