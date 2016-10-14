package controller;

import model.Dice;

public class Role extends State {
	private boolean beenhere = false;
	public Role(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	public void onClickDice()
	{
		engine.rollDices();
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
