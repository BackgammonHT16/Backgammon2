/**
 * 
 */
package model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author philipp
 *
 */

import controller.GameEngine;


public class AI extends Player {
	
	public AI(Board board, GameEngine engine, String id)
	{
		super(board, engine, id);
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
		LinkedHashMap<String, Place> places = engine.getLegalStartPlaces();
		if(places != null)
		{
			return places.get("0");
		}
		// TODO Add valid code
		return null;
	}
	

	@Override
	public Place chooseEnd() {
		// TODO Add AI
		LinkedHashMap<String, Place> places = engine.getLegalEndPlaces();
		if(places != null)
		{
			return places.get("0");
		}
		return null;
	}

}
