package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class PickStart extends State {

	private boolean beenhere = false;
	public PickStart(GameEngine engine) {
		super(engine);
	}
	
	public void onClickPlace(String placeId)
	{
		engine.getDice().setPlayer(engine.getPlayer());
		if(engine.setStartPlace(engine.getPlace(placeId)) == true)
		{
			System.out.println("Start Place: " + placeId);
			engine.setState(new PickEnd(engine));
		}
	}
}
