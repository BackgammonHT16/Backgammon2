package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class PickStart extends State {

	private boolean beenhere = false;
	public PickStart(GameEngine engine) {
		super(engine);
	}

	@Override
	public void nextAction() {

		if(!beenhere)
		{
			beenhere = true;
			System.out.println("PickStart");
		}
		// Prüfen ob mit dem gewürfeltem wert überhaupt züge möglich sind
		if(engine.getLegalStartPlaces() == null)
		{
			// Es sind keine Züge möglich
			engine.nextPlayer();
			engine.setState(new Role(engine));
			return;
		}
		
		//Place p = engine.getPlayer().chooseStart();
		if(engine.getCurrentMove().getStart() != null)
		//if(p != null)
		{
			if(engine.setStartPlace(engine.getCurrentMove().getStart()))
			{
				// Der gewählte Platz ist gültig
				engine.getCurrentMove().activateSelectEnd();
				engine.setState(new PickEnd(engine));
			}
			// Der gewählte Platz ist ungültig d.h. der Zustand bleibt gleich
		}
		
	}
}
