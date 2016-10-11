package controller;

import model.Dice;

public class Role extends State {

	public Role(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		Dice d = engine.getPlayer().rollDices();
		if(d != null)
		{
			engine.setState(new PickStart(engine));
		}
	}
}
