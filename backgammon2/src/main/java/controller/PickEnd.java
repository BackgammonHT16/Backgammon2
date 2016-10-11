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

	public PickEnd(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		Place p = engine.getPlayer().chooseEnd();
		if(p != null)
		{
			if(engine.setEndPlace(p))
			{
				// Der gewählte Platz ist gültig
				engine.setState(new PickEnd(engine));
			}
			// Der gewählte Platz ist ungültig d.h. der Zustand bleibt gleich
		}
	}
}
