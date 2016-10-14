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
	Board board;
	String id;
	GameEngine engine;
	
	public AI(Board board, GameEngine engine, String id)
	{
		super(board, engine, id);
		this.board = board;
		this.id = id;
		this.engine = engine;
	}



	public Place chooseStart() {
		// TODO Add AI
		LinkedHashMap<String, Place> places = engine.getLegalStartPlaces();
		if(places != null)
		{
			return places.entrySet().iterator().next().getValue();
		}
		// TODO Add valid code
		return null;
	}
	

	public Place chooseEnd() {
		// TODO Add AI
		LinkedHashMap<String, Place> places = engine.getLegalEndPlaces();
		if(places != null)
		{
			return places.entrySet().iterator().next().getValue();
		}
		return null;
	}

}
