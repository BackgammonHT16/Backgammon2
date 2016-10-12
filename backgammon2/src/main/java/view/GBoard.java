/**
 * 
 */
package view;

import java.util.LinkedHashMap;

import controller.GameEngine;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import model.Dice;

/**
 * @author philipp
 *
 */
public class GBoard {
	private ImageView image;
	private LinkedHashMap<String, GPlace> places;
	private LinkedHashMap<String, GChecker> checkers;
	private GDice dice;
	private GameEngine engine;
	
	public GBoard(LinkedHashMap<String, Object> config, StackPane root, GameEngine engine)
	{
		this.engine = engine;
		image = new ImageHelper((String)config.get("bgImage"));
		root.getChildren().add(image);
		
		// Points hinzufügen
		places = createPlaces(config, root, engine);
		
		// Checkers hinzufügen
		checkers = createCheckers(config, root);
		
		// Dice hinzufügen
		dice = createDice(config, root);
		
		// Menu Button
		addMenuButton(config, root, engine);
	}
	
	private void addMenuButton(
			LinkedHashMap<String, Object> config,
			StackPane root,
			GameEngine engine)
	{
		ImageView im = new ImageHelper(
				(String)config.get("menuButtonImage"),
				(Integer)config.get("menuButtonWidth"),
				true,
				(Integer)config.get("menuButtonPositionX"),
				(Integer)config.get("menuButtonPositionY"));
		//places.put("goal0", new GGoal(im, "goal0"));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	GBoard.this.engine.clickedOnMenuButton();
                    }
                });
	}
	
	public void rollDice(Dice d)
	{
		dice.setDice(d);
	}
	
	private GDice createDice(
			LinkedHashMap<String, Object> config,
			StackPane root)
	{
		Group box = new Group();
		Image[] image = new Image[7];
		for(int i = 0; i < 7; i++)
		{
			image[i] = new Image("file:" + (String)config.get("diceImage") + i + ".png");
		}
		box.setTranslateX((Double)config.get("dicePositionX"));
		box.setTranslateY((Double)config.get("dicePositionY"));
		root.getChildren().add(box);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		box.setPickOnBounds(true);
		box.setMouseTransparent(false);
		box.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	GBoard.this.engine.clickedOnDice();
                    }
                });
		return new GDice(box, image, (Integer)config.get("diceWidth"));
	}
	
	public void moveCheckerFromTo(String from, String to)
	{
		places.get(from).moveCheckerTo(places.get(to));
	}

	
	// TODO diese Methode mithilfe der informationen aus Board implementieren
	private LinkedHashMap<String, GChecker> createCheckers(
			LinkedHashMap<String, Object> config,
			StackPane root)
	{
		LinkedHashMap<String, GChecker> checkers = new LinkedHashMap<String, GChecker>();
		int checkerId = 0;
		int numPoints = (Integer) config.get("numberOfPoints");
		for(int i = 0; i < numPoints; i++)
		{
			if(config.get("point" + i + "CheckersNumber") != null)
			{
				for(int j = 0; j < (Integer)config.get("point" + i + "CheckersNumber"); j++)
				{
					ImageView im = new ImageHelper(
							(String)config.get("checker" + (Integer)config.get("point" + i + "CheckersPlayer") + "Image"),
							(Integer)config.get("checkerWidth"),
							false);
					checkers.put(i+"", new GChecker(places.get("point"+i), im, checkerId+""));
					root.getChildren().add(im);
					checkerId++;
				}
			}
		}
		return checkers;
	}
	
	private LinkedHashMap<String, GPlace> createPlaces(
			LinkedHashMap<String, Object> config,
			StackPane root,
			GameEngine engine)
	{
		LinkedHashMap<String, GPlace> places = new LinkedHashMap<String, GPlace>();
		addPoints(config, places, root, engine);
		addBar(config, places, root, engine);
		addGoal(config, places, root, engine);
		return places;
	}
	
	private void addPoints(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root,
			GameEngine engine)
	{
		int numPoints = (Integer) config.get("numberOfPoints");
		for(int i = 0; i < numPoints; i++)
		{
			ImageView im = new ImageHelper(
					(String)config.get("pointImage"),
					(Integer)config.get("pointWidth"),
					true,
					(Integer)config.get("point" + i + "PositionX"),
					(Integer)config.get("point" + i + "PositionY"));
			// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
			im.setPickOnBounds(true);
			final int a = i;
			im.setOnMouseClicked(
	                new EventHandler<MouseEvent>()
	                {
	                    public void handle(MouseEvent e)
	                    {
	                    	GBoard.this.engine.clickedOnPlace("point"+a);
	                    }
	                });
			root.getChildren().add(im);
			places.put("point"+i, new GPoint(im, "point"+i));
		}
	}

	private void addBar(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root,
			GameEngine engine)
	{
		ImageView im = new ImageHelper(
				(String)config.get("bar0Image"),
				(Integer)config.get("barWidth"),
				true,
				(Integer)config.get("bar0PositionX"),
				(Integer)config.get("bar0PositionY"));
		places.put("bar0", new GBar(im, "bar0"));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	GBoard.this.engine.clickedOnPlace("bar0");
                    }
                });
		
		im = new ImageHelper(
				(String)config.get("bar1Image"),
				(Integer)config.get("barWidth"),
				true,
				(Integer)config.get("bar1PositionX"),
				(Integer)config.get("bar1PositionY"));
		places.put("bar1", new GBar(im, "bar1"));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	GBoard.this.engine.clickedOnPlace("bar1");
                    }
                });
	}
	
	private void addGoal(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root,
			GameEngine engine)
	{
		ImageView im = new ImageHelper(
				(String)config.get("goal0Image"),
				(Integer)config.get("goalWidth"),
				true,
				(Integer)config.get("goal0PositionX"),
				(Integer)config.get("goal0PositionY"));
		places.put("goal0", new GGoal(im, "goal0"));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	GBoard.this.engine.clickedOnPlace("goal0");
                    }
                });
		
		im = new ImageHelper(
				(String)config.get("goal1Image"),
				(Integer)config.get("goalWidth"),
				true,
				(Integer)config.get("goal1PositionX"),
				(Integer)config.get("goal1PositionY"));
		places.put("goal1", new GGoal(im, "goal1"));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	GBoard.this.engine.clickedOnPlace("goal1");
                    }
                });
	}
	
	public ImageView getImage()
	{
		return image;
	}
}
