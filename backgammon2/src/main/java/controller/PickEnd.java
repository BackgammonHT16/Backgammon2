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
		engine.selectPlaces(engine.getLegalEndPlaces());
	}

	public void onClickPlace(String placeId)
	{
		if(engine.setEndPlace(engine.getPlace(placeId)) == true)
		{
			engine.unselectAllPlaces();
			System.out.println("End Place: " + placeId);
			engine.executeMove();		
			if(engine.won())
			{
				engine.finishGame();
			}
			else if(engine.diceLeft())
			{
				if(engine.getLegalStartPlaces().isEmpty())
				{
					System.out.println("No moves available!");
					engine.nextPlayer();
					engine.setState(new Role(engine));
				}
				engine.setState(new PickStart(engine));
			}
			else
			{
				engine.nextPlayer();
				engine.setState(new Role(engine));
			}
		}
	}
}
