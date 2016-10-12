/**
 * 
 */
package controller;

import java.util.List;

import model.Place;

/**
 * @author philipp
 *
 */
public class PickEnd extends State {

	private boolean beenhere = false;
	public PickEnd(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		if(!beenhere)
		{
			beenhere = true;
			System.out.println("PickEnd");
		}
		Place p = engine.getCurrentMove().getEnd(); //engine.getPlayer().chooseEnd();
		if(p != null)
		{
			if(engine.setEndPlace(p))
			{

				engine.executeMove();
				// Der gewählte Platz ist gültig
				engine.setState(new RemoveDices(engine));
			}
			// Der gewählte Platz ist ungültig d.h. der Zustand bleibt gleich
		}
	}
}
