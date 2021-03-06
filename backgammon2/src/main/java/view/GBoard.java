/**
 * 
 */
package view;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import controller.GameEngine;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.StackPane;
import model.Dice;
import model.Place;

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
                    	//GBoard.this.engine.clickedOnMenuButton();
                    	GBoard.this.engine.onClickMenu();
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
		Image[] imageUsed = new Image[7];
		for(int i = 0; i < 7; i++)
		{
			image[i] = new Image("file:" + (String)config.get("diceImage") + i + ".png");
			imageUsed[i] = new Image("file:" + (String)config.get("diceImageUsed") + i + ".png");
		}
		box.setTranslateX((Integer)config.get("dicePositionX"));
		box.setTranslateY((Integer)config.get("dicePositionY"));
		root.getChildren().add(box);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		box.setPickOnBounds(true);
		box.setMouseTransparent(false);
		box.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	// GBoard.this.engine.clickedOnDice();
                    	GBoard.this.engine.onClickDice();
                    }
                });
		return new GDice(box, image, imageUsed, (Integer)config.get("diceWidth"));
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
					GChecker checker = new GChecker(places.get("point"+i), im, checkerId+"");
					checkers.put(i+"", checker);
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
		Image imageNormal =  new Image("file:" + (String)config.get("pointImage"));
		Image imageSelected =  new Image("file:" + (String)config.get("pointSelectedImage"));
		for(int i = 0; i < numPoints; i++)
		{
			ImageView im = new ImageHelper(
					(String)config.get("pointImage"),
					(Integer)config.get("pointWidth"),
					true,
					(Integer)config.get("point" + i + "PositionX"),
					(Integer)config.get("point" + i + "PositionY"));
			System.out.println((Integer) config.get("point" + i + "Rotation")+ "i = "+i);
			im.setRotate((Integer) config.get("point" + i + "Rotation"));
			// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
			im.setPickOnBounds(true);
			final int a = i;
			im.setOnMouseClicked(
	                new EventHandler<MouseEvent>()
	                {
	                    public void handle(MouseEvent e)
	                    {
	                    	// GBoard.this.engine.clickedOnPlace("point"+a);
	                    	GBoard.this.engine.onClickPlace("point"+a);
	                    }
	                });
			root.getChildren().add(im);
			places.put("point"+i, new GPoint(im, "point"+i, (Integer)config.get("checkerWidth"), imageNormal, imageSelected));
		}
	}

	private void addBar(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root,
			GameEngine engine)
	{
		Image imageNormal =  new Image("file:" + (String)config.get("bar0Image"));
		Image imageSelected =  new Image("file:" + (String)config.get("bar0SelectedImage"));
		ImageView im = new ImageHelper(
				(String)config.get("bar0Image"),
				(Integer)config.get("barWidth"),
				true,
				(Integer)config.get("bar0PositionX"),
				(Integer)config.get("bar0PositionY"));
		places.put("bar0", new GBar(im, "bar0", (Integer)config.get("checkerWidth"), imageNormal, imageSelected));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	//GBoard.this.engine.clickedOnPlace("bar0");
                    	GBoard.this.engine.onClickPlace("bar0");
                    }
                });

		imageNormal =  new Image("file:" + (String)config.get("bar1Image"));
		imageSelected =  new Image("file:" + (String)config.get("bar1SelectedImage"));
		im = new ImageHelper(
				(String)config.get("bar1Image"),
				(Integer)config.get("barWidth"),
				true,
				(Integer)config.get("bar1PositionX"),
				(Integer)config.get("bar1PositionY"));
		places.put("bar1", new GBar(im, "bar1", (Integer)config.get("checkerWidth"), imageNormal, imageSelected));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	// GBoard.this.engine.clickedOnPlace("bar1");
                    	GBoard.this.engine.onClickPlace("bar1");
                    }
                });
	}
	
	private void addGoal(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root,
			GameEngine engine)
	{
		Image imageNormal =  new Image("file:" + (String)config.get("goal0Image"));
		Image imageSelected =  new Image("file:" + (String)config.get("goal0SelectedImage"));
		ImageView im = new ImageHelper(
				(String)config.get("goal0Image"),
				(Integer)config.get("goalWidth"),
				true,
				(Integer)config.get("goal0PositionX"),
				(Integer)config.get("goal0PositionY"));
		places.put("goal0", new GGoal(im, "goal0", (Integer)config.get("checkerWidth"), imageNormal, imageSelected));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	//GBoard.this.engine.clickedOnPlace("goal0");
                    	GBoard.this.engine.onClickPlace("goal0");
                    }
                });

		imageNormal =  new Image("file:" + (String)config.get("goal1Image"));
		imageSelected =  new Image("file:" + (String)config.get("goal1SelectedImage"));
		im = new ImageHelper(
				(String)config.get("goal1Image"),
				(Integer)config.get("goalWidth"),
				true,
				(Integer)config.get("goal1PositionX"),
				(Integer)config.get("goal1PositionY"));
		places.put("goal1", new GGoal(im, "goal1", (Integer)config.get("checkerWidth"), imageNormal, imageSelected));
		root.getChildren().add(im);
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		im.setPickOnBounds(true);
		im.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	// GBoard.this.engine.clickedOnPlace("goal1");
                    	GBoard.this.engine.onClickPlace("goal1");
                    }
                });
	}
	
	public ImageView getImage()
	{
		return image;
	}

	public void updateDice(Dice dice) {
		this.dice.updateDice(dice);
	}

	public void selectPlaces(LinkedHashMap<String, Place> places) {
		for(Entry<String, Place> p : places.entrySet())
		{
			this.places.get(p.getKey()).setSelected();
		}
	}
	

	public void unselectAllPlaces() {
		for(Entry<String, GPlace> p : places.entrySet())
		{
			p.getValue().setNormal();
		}
	}
}
