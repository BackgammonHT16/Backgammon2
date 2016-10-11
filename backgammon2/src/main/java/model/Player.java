/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */


import java.util.ArrayList;
import java.util.List;

import controller.GameEngine;

public abstract class Player {
	private List<Checker> checkers = new ArrayList<Checker>();
	private Board board;
	protected GameEngine engine;
	
	public Player(Board board, GameEngine engine)
	{
		this.board = board;
		this.engine = engine;
	}
	
	public abstract Dice rollDices();
	
	public abstract Dice rollSingleDice();

	public abstract Place chooseStart();
	
	public abstract Place chooseEnd();
}
