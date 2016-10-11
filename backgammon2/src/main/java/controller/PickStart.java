package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class PickStart extends State {

	public PickStart(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void nextAction() {
		// Prüfen ob mit dem gewürfeltem wert überhaupt züge möglich sind
		if(engine.getLegalStartPlaces() == null)
		{
			// Es sind keine Züge möglich
			engine.nextPlayer();
			engine.setState(new Role(engine));
			return;
		}
		
		Place p = engine.getPlayer().chooseStart();
		if(p != null)
		{
			if(engine.setStartPlace(p))
			{
				// Der gewählte Platz ist gültig
				engine.setState(new PickEnd(engine));
			}
			// Der gewählte Platz ist ungültig d.h. der Zustand bleibt gleich
		}
		
	}
}
