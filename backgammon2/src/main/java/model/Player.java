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
	private String id;
	
	public Player(Board board, GameEngine engine, String id)
	{
		this.board = board;
		this.engine = engine;
		this.id = id;
	}
	
	public String getId()
	{
		return id;
	}
	
	public boolean allCheckersInHomeField()
	{
		boolean allAreHome = true;
		for(Checker c : checkers)
		{
			allAreHome &= c.isInHomeField();
		}
		return allAreHome;
	}

	public boolean won() {
		boolean allAreAtGoal = true;
		for(Checker c : checkers)
		{
			allAreAtGoal &= c.isInGoal();
		}
		return allAreAtGoal;
	}
	
	public void addChecker(Checker checker)
	{
		checkers.add(checker);
	}
}
