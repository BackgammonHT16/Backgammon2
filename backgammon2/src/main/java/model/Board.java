/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import controller.*;
import javafx.scene.image.ImageView;
import view.GChecker;
import view.ImageHelper;


public class Board {
	private LinkedHashMap<String, Checker>  checkers = new LinkedHashMap<String, Checker> ();
	private LinkedHashMap<String, Place>  places = new LinkedHashMap<String, Place> ();
	private LinkedHashMap<String, Player>  players = new LinkedHashMap<String, Player> ();
	
	private GameEngine engine;
	
	private LinkedHashMap<String, Object> config;
	
	public Board(GameEngine engine, LinkedHashMap<String, Object> config)
	{
		this.engine = engine;
		this.config = config;
		initPlayers();
		initPlaces();
		initCheckers();
	}
	
	public void moveCheckerFromTo(Place start, Place end)
	{
		start.moveCheckerTo(end);
	}

	private void initPlaces()
	{
		int numPoints = (Integer) config.get("numberOfPoints");
		for(int i = 0; i < numPoints; i++)
		{
			places.put("point" + i, new Point("point" + i));
		}
		places.put("bar0", new Bar(players.get(0), "bar0"));
		places.put("bar1", new Bar(players.get(1), "bar1"));
		places.put("goal0", new Goal(players.get(0), "goal0"));
		places.put("goal1", new Goal(players.get(1), "goal1"));
	}
	
	private void initPlayers()
	{
		players.put("0", new Human(this, engine, "0"));
		players.put("1", new AI(this, engine, "1"));
	}
	
	private void initCheckers()
	{
		int checkerId = 0;
		int numPoints = (Integer) config.get("numberOfPoints");
		for(int i = 0; i < numPoints; i++)
		{
			if(config.get("point" + i + "CheckersNumber") != null)
			{
				for(int j = 0; j < (Integer)config.get("point" + i + "CheckersNumber"); j++)
				{
					Player p = players.get(((Integer)config.get("point" + i + "CheckersPlayer")).toString());
					Checker checker = new Checker(p, places.get("point"+i), checkerId+"");
					p.addChecker(checker);
					checkers.put(i+"", checker);
					checkerId++;
				}
			}
		}
	}

	public LinkedHashMap<String, Checker> getCheckers() {
		return checkers;
	}
	public LinkedHashMap<String, Place> getPlaces() {
		return places;
	}
	public LinkedHashMap<String, Player> getPlayers() {
		return players;
	}

}
