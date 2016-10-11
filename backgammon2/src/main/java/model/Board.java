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


public class Board {
	private List<Checker> checkers = new ArrayList<Checker>();
	private List<Place> places = new ArrayList<Place>();
	private List<Player> players = new ArrayList<Player>();
	
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

	private void initPlaces()
	{
		for(int i = 0; i < 24; i++)
		{
			places.add(new Point());
		}
		places.add(new Bar(players.get(0)));
		places.add(new Bar(players.get(1)));
		places.add(new Goal(players.get(0)));
		places.add(new Goal(players.get(1)));
	}
	
	private void initPlayers()
	{
		players.add(new Human(this, engine));
		players.add(new AI(this, engine));
	}
	
	private void initCheckers()
	{
		placeNewCheckers(places.get(0), players.get(1), 2);
		placeNewCheckers(places.get(5), players.get(0), 5);
		placeNewCheckers(places.get(7), players.get(0), 3);
		placeNewCheckers(places.get(11), players.get(1), 5);
		placeNewCheckers(places.get(12), players.get(0), 5);
		placeNewCheckers(places.get(16), players.get(1), 3);
		placeNewCheckers(places.get(18), players.get(1), 5);
		placeNewCheckers(places.get(23), players.get(0), 2);
	}
	
	private void placeNewCheckers(Place place, Player player, int amount)
	{
		for(int i = 0; i < amount; i++)
		{
			Checker c = new Checker(player, place);
			place.addChecker(c);
		}
	}

	public List<Checker> getCheckers() {
		return checkers;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public List<Player> getPlayers() {
		return players;
	}

}
