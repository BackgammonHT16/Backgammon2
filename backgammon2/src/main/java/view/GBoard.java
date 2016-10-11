/**
 * 
 */
package view;

import java.util.LinkedHashMap;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author philipp
 *
 */
public class GBoard {
	private ImageView image;
	private LinkedHashMap<String, GPlace> places;
	private LinkedHashMap<String, GChecker> checkers;
	
	public GBoard(LinkedHashMap<String, Object> config, StackPane root)
	{
		image = new ImageHelper((String)config.get("bgImage"));
		root.getChildren().add(image);
		
		// Points hinzufügen
		places = createPlaces(config, root);
		
		// Checkers hinzufügen
		checkers = createCheckers(config, root);
	}
	
	public void moveCheckerFromTo(String from, String to)
	{
		places.get(from).moveCheckerTo(places.get(to));
	}

	private LinkedHashMap<String, GChecker> createCheckers(
			LinkedHashMap<String, Object> config,
			StackPane root)
	{
		LinkedHashMap<String, GChecker> checkers = new LinkedHashMap<String, GChecker>();
		int checkerId = 0;
		for(int i = 0; i < 24; i++)
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
			StackPane root)
	{
		LinkedHashMap<String, GPlace> places = new LinkedHashMap<String, GPlace>();
		addPoints(config, places, root);
		addBar(config, places, root);
		addGoal(config, places, root);
		return places;
	}
	
	private void addPoints(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root)
	{
		for(int i = 0; i < 24; i++)
		{
			ImageView im = new ImageHelper(
					(String)config.get("pointImage"),
					(Integer)config.get("pointWidth"),
					false,
					(Integer)config.get("point" + i + "PositionX"),
					(Integer)config.get("point" + i + "PositionY"));
			root.getChildren().add(im);
			places.put("point"+i, new GPoint(im, "point"+i));
		}
	}

	private void addBar(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root)
	{
		ImageView im = new ImageHelper(
				(String)config.get("bar0Image"),
				(Integer)config.get("barWidth"),
				false,
				(Integer)config.get("bar0PositionX"),
				(Integer)config.get("bar0PositionY"));
		places.put("bar0", new GBar(im, "bar0"));
		root.getChildren().add(im);
		
		im = new ImageHelper(
				(String)config.get("bar1Image"),
				(Integer)config.get("barWidth"),
				false,
				(Integer)config.get("bar1PositionX"),
				(Integer)config.get("bar1PositionY"));
		places.put("bar1", new GBar(im, "bar1"));
		root.getChildren().add(im);
	}
	
	private void addGoal(
			LinkedHashMap<String, Object> config, 
			LinkedHashMap<String, GPlace> places,
			StackPane root)
	{
		ImageView im = new ImageHelper(
				(String)config.get("goal0Image"),
				(Integer)config.get("goalWidth"),
				false,
				(Integer)config.get("goal0PositionX"),
				(Integer)config.get("goal0PositionY"));
		places.put("goal0", new GGoal(im, "goal0"));
		root.getChildren().add(im);
		
		im = new ImageHelper(
				(String)config.get("goal1Image"),
				(Integer)config.get("goalWidth"),
				false,
				(Integer)config.get("goal1PositionX"),
				(Integer)config.get("goal1PositionY"));
		places.put("goal1", new GGoal(im, "goal1"));
		root.getChildren().add(im);
	}
	
	public ImageView getImage()
	{
		return image;
	}
}
