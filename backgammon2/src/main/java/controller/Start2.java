/**
 * 
 */
package controller;

import model.Dice;

/**
 * @author philipp
 *
 */
public class Start2 extends State {

	private boolean beenhere = false;
	public Start2(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {

		if(!beenhere)
		{
			beenhere = true;
			System.out.println("Start2");
		}
		Dice d = engine.getPlayer().rollSingleDice();
		if(d != null)
		{
			engine.setState(new Compare(engine));
		}
	}

}
