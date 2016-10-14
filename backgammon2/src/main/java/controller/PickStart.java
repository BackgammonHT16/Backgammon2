package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class PickStart extends State {

	public PickStart(GameEngine engine) {
		super(engine);
		engine.selectPlaces(engine.getLegalStartPlaces());
	}
	
	public void onClickPlace(String placeId)
	{
		//engine.getDice().setPlayer(engine.getPlayer());
		if(engine.setStartPlace(engine.getPlace(placeId)) == true)
		{
			System.out.println("Start Place: " + placeId);
			engine.unselectAllPlaces();
			engine.setState(new PickEnd(engine));
		}
	}
}
