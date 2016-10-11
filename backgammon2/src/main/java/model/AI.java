/**
 * 
 */
package model;

import java.util.List;

/**
 * @author philipp
 *
 */

import controller.GameEngine;


public class AI extends Player {
	
	public AI(Board board, GameEngine engine)
	{
		super(board, engine);
	}

	@Override
	public Dice rollDices()
	{
		return engine.rollDices();
	}

	@Override
	public Dice rollSingleDice()
	{
		return engine.rollSingleDice();
	}
	

	@Override
	public Place chooseStart() {
		// TODO Add AI
		List<Place> places = engine.getLegalStartPlaces();
		if(places != null)
		{
			return places.get(0);
		}
		// TODO Add valid code
		return null;
	}
	

	@Override
	public Place chooseEnd() {
		// TODO Add AI
		List<Place> places = engine.getLegalEndPlaces();
		if(places != null)
		{
			return places.get(0);
		}
		return null;
	}

}
