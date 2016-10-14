package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Dice;
import model.Human;

public class Role extends State {
	private boolean beenhere = false;
	public Role(GameEngine engine) {
		super(engine);
		// TODO Auto-generated constructor stub
	}
	
	public void onClickDice()
	{
		engine.rollDices();
		if(engine.getLegalStartPlaces().isEmpty())
		{
			System.out.println("No moves available!");
			engine.nextPlayer();
			engine.setState(new Role(engine));
		}
		else
		{
			if(engine.getPlayer() instanceof Human)
			{
				engine.setState(new PickStart(engine));
			}
			else
			{
				Timeline timeline = new Timeline(new KeyFrame(
				        Duration.millis(2500),
				        ae -> engine.setState(new PickStart(engine))));
				timeline.play();
			}
		}
	}
}
