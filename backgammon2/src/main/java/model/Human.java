/**
 * 
 */
package model;

import controller.*;

/**
 * @author philipp
 *
 */
public class Human extends Player{
	
	
	public Human(Board board, GameEngine engine)
	{
		super(board, engine);
	}
	
	public Dice rollDices()
	{
		return engine.requestHumanRollDice();
	}	
	
	public Dice rollSingleDice()
	{
		return engine.requestHumanRollSingleDice();
	}

	@Override
	public Place chooseStart() {
		return engine.requestHumanChooseStart();
	}
	
	@Override
	public Place chooseEnd() {
		return engine.requestHumanChooseEnd();
	}
}
