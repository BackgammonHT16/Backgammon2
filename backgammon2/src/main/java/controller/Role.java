package controller;

import model.Dice;

public class Role extends State {
	private boolean beenhere = false;
	public Role(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		if(!beenhere)
		{
			beenhere = true;
			System.out.println("Role");
		}
		Dice d = engine.getPlayer().rollDices();
		if(d != null)
		{
			engine.setCurrentState();
			engine.getCurrentMove().activateSelectStart();
			engine.setState(new PickStart(engine));
		}
	}
}
