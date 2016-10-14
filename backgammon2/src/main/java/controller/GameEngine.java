/**
 * 
 */
package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.swing.JDialog;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.*;
import view.*;

/**
 * @author philipp
 *
 */

public class GameEngine {
	private LinkedHashMap<String, Object> config;
	private Stage stage;
	private Board board;
	
	private Player currentPlayer;
	private State currentState;
	private Dice dice;
	
	
	private String startPlace;
	private String endPlace;
	
	private Graphic graphic;
	
	public GameEngine(Stage stage, LinkedHashMap<String, Object> config)
	{
		this.config = config;
		this.stage = stage;
		/*
		board = new Board(this, config);
    	graphic = new Graphic(this, stage, config);
    	dice = new Dice();
    	currentPlayer = board.getPlayers().get("0");
    	*/
    	currentState = new Menu(this, null);
	}
	
	public void quit()
	{
		stage.hide();
	}
	
	public void resetGame(int color, int difficulty, int time)
	{
		board = new Board(this, config);
    	graphic = new Graphic(this, stage, config);
    	dice = new Dice();
    	currentPlayer = board.getPlayers().get("0");
    	currentState = new Start(this);
	}
	

	public void onClickPlace(String placeId)
	{
		currentState.onClickPlace(placeId);
	}
	
	public void onClickMenu()
	{
		currentState.onClickMenu();
	}
	
	public void onClickDice()
	{
		currentState.onClickDice();
	}
	
	
	
	public Dice rollDices()
	{
		dice.role();
		graphic.rollDice(dice);
		return dice;
	}

	public Dice rollSingleDice()
	{
		dice.roleSingleNumber();
		graphic.rollDice(dice);
		return dice;
	}
	
	
	public void setState(State state)
	{
		currentState = state;
		if(currentPlayer instanceof AI)
		{
			if(state instanceof Role)
			{
				onClickDice();
			}
			else if(state instanceof PickStart)
			{
				onClickPlace(((AI) currentPlayer).chooseStart().getId());
			}
			else if(state instanceof PickEnd)
			{
				onClickPlace(((AI) currentPlayer).chooseEnd().getId());
			}
		}
	}

	public void selectPlaces(LinkedHashMap<String, Place> places)
	{
		graphic.selectPlaces(places);
	}
	public void unselectAllPlaces()
	{
		graphic.unselectAllPlaces();
	}
	
	public LinkedHashMap<String, Place> getLegalStartPlaces()
	{
		LinkedHashMap<String, Place> result = new LinkedHashMap<String, Place>();
		
		for(Map.Entry<String, Place> p : board.getPlaces().entrySet())
		{
			if(isLegalStartPlace(p.getValue(), false))
			{
				result.put(p.getKey(), p.getValue());
			}
		}
		if(currentPlayer.allCheckersInHomeField() && result.isEmpty())
		{
			for(Map.Entry<String, Place> p : board.getPlaces().entrySet())
			{
				if(isLegalStartPlace(p.getValue(), true))
				{
					result.put(p.getKey(), p.getValue());
				}
			}
		}
		return result;
	}
	
	private boolean allHome()
	{
		LinkedHashMap<String, Place> result = new LinkedHashMap<String, Place>();
		
		for(Map.Entry<String, Place> p : board.getPlaces().entrySet())
		{
			if(isLegalStartPlace(p.getValue(), false))
			{
				result.put(p.getKey(), p.getValue());
			}
		}
		if(currentPlayer.allCheckersInHomeField() && result.isEmpty())
		{
			return true;
		}
		return false;
	}


	public boolean isLegalStartPlace(Place place, boolean allHome)
	{
		// Falls ein Checker auf der Bar liegt muss dieses zuerst bewegt werden
		if(board.getPlaces().get("bar" + currentPlayer.getId()).size() > 0)
		{
			if(place.getId().equals("bar" + currentPlayer.getId()))
			{
				if((isLegalEndPlace(getPlacePlusN(place, dice.getValue(0), allHome)) && !dice.isUsed(0)) ||
						(isLegalEndPlace(getPlacePlusN(place, dice.getValue(1), allHome)) && !dice.isUsed(1)))
				{
					return true;
				}
			}
			else
			{
				return false;
			}
		}
		if(place == null)
		{
			return  false;
		}
		if(place.getOwner() == null)
		{
			return false;
		}
		if(place.getOwner().getId().equals(currentPlayer.getId()) && place.size() > 0)
		{
			if((isLegalEndPlace(getPlacePlusN(place, dice.getValue(0), allHome)) && !dice.isUsed(0)) ||
					(isLegalEndPlace(getPlacePlusN(place, dice.getValue(1), allHome)) && !dice.isUsed(1)))
			{
				return true;
			}
		}
		return false;
	}
	
	public Place getPlacePlusN(Place place, int n, boolean allHome)
	{
		int id = place.getIntId();
		// Place ist ein Point
		if(id <= 23 && id >= 0)
		{
			return getPlaceFromIDPlusN(id, n, allHome);
		}
		else if(place.getId().equals("bar0"))
		{
			return getPlaceFromIDPlusN(-1, n, allHome);
		}
		else if(place.getId().equals("goal0"))
		{
			return getPlaceFromIDPlusN(24, n, allHome);
		}
		else if(place.getId().equals("bar1"))
		{
			return getPlaceFromIDPlusN(24, n, allHome);
		}
		else if(place.getId().equals("goal1"))
		{
			return getPlaceFromIDPlusN(-1, n, allHome);
		}
		return null;
	}
	
	private Place getPlaceFromIDPlusN(int id, int n, boolean allHome)
	{
		if(id + n <= 23 && id + n >= 0)
		{
			return getPlace("point" + (id + n));
		}
		else if((id + n == 24) || ((id + n >= 24) && allHome))
		{
			if(currentPlayer.getId().equals("0"))
			{
				return getPlace("goal0");
			}
			if(currentPlayer.getId().equals("1"))
			{
				return getPlace("bar1");
			}
		}
		else if(id + n == -1 || ((id + n <= -1) && allHome))
		{
			if(currentPlayer.getId().equals("0"))
			{
				return getPlace("bar0");
			}
			if(currentPlayer.getId().equals("1"))
			{
				return getPlace("goal1");
			}
		}
		return null;
	}
	
	public boolean isLegalEndPlace(Place place)
	{
		if(place == null)
		{
			return  false;
		}
		if((place.getOwner() == currentPlayer || 
				place.size() <= 1) && place instanceof Point)
		{
			return true;
		}
		if(place instanceof Goal && place.getOwner() == currentPlayer && currentPlayer.allCheckersInHomeField())
		{
			return true;
		}
		return false;
	}
	
/*	public boolean isReachableEndPlace(Place start, Place end)
	{
		if(getPlacePlusN(start, dice.getValue(0)))
	}*/

	public LinkedHashMap<String, Place> getLegalEndPlaces()
	{
		LinkedHashMap<String, Place> result = new LinkedHashMap<String, Place>();
		boolean allHome = this.allHome();
		if(startPlace == null || dice == null)
		{
			return null;
		}
		if(dice.size() > 0 &&  !dice.isUsed(0) &&
				isLegalEndPlace(getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome)))
		{
			result.put(
					getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome).getId(), 
					getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome)
					);
			if(dice.size() > 1 &&  !dice.isUsed(1) &&
					isLegalEndPlace(
							getPlacePlusN(
									getPlace(startPlace), 
									dice.getValue(0) + dice.getValue(1), allHome)))
			{
				result.put(
						getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome).getId(), 
						getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome));
				if(dice.size() > 2 && !dice.isUsed(2) &&
						isLegalEndPlace(
								getPlacePlusN(
										getPlace(startPlace), 
										dice.getValue(0) + dice.getValue(1) + dice.getValue(2), allHome)))
				{
					result.put(
							getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2), allHome).getId(), 
							getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2), allHome));
					if(dice.size() > 3 && !dice.isUsed(3) &&
							isLegalEndPlace(
									getPlacePlusN(
											getPlace(startPlace), 
											dice.getValue(0) + dice.getValue(1) + dice.getValue(2) + dice.getValue(3), allHome)))
					{
						result.put(
								getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2) + dice.getValue(3), allHome).getId(), 
								getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2) + dice.getValue(3), allHome));
					
					}
				}

			}
		}

		
		if(dice.size() > 1 && !dice.isUsed(1) &&
				isLegalEndPlace(getPlacePlusN(getPlace(startPlace), dice.getValue(1), allHome)))
		{
			result.put(
					getPlacePlusN(getPlace(startPlace), dice.getValue(1), allHome).getId(), 
					getPlacePlusN(getPlace(startPlace), dice.getValue(1), allHome)
					);
			if(!dice.isUsed(0) && isLegalEndPlace(getPlacePlusN(
								getPlace(startPlace), 
								dice.getValue(0) + dice.getValue(1), allHome)))
			{
				result.put(
						getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome).getId(), 
						getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome));
			}
		}

		return result;
	}
	
	public boolean setStartPlace(Place p)
	{
		LinkedHashMap<String, Place> places = getLegalStartPlaces();
		// Der gewählte Platz ist gültig
		if(places != null && places.containsValue(p))
		{
			startPlace = p.getId();
			return true;
		}

		return false;
	}

	public boolean setEndPlace(Place p)
	{
		LinkedHashMap<String, Place> places = getLegalEndPlaces();
		// Der gewählte Platz ist gültig
		if(places != null && places.containsValue(p))
		{
			endPlace = p.getId();
			return true;
		}
		return false;
	}
	
	
	// TODO Es kann im Endspiel (abstand Goal < würfel) dazu kommen dass 
	// der entsprechende würfel nicht entfernt wird
	private void removeDices()
	{
		boolean allHome = this.allHome();
		if(dice.size() > 0 && !dice.isUsed(0))
		{
			Place p = getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome);
			if(p != null)
			{
				if(p.getId().equals(endPlace))
				{
					dice.removeDice(0);
					graphic.updateDice(dice);
					return;
				}
			}
		}
		if(dice.size() > 1 && !dice.isUsed(1))
		{
			Place p = getPlacePlusN(getPlace(startPlace), dice.getValue(1), allHome);
			if(p != null)
			{
				if(p.getId().equals(endPlace))
				{
					dice.removeDice(1);
					graphic.updateDice(dice);
					return;
				}
			}
		}
		if(dice.size() > 1)
		{
			Place p = getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome);
			if(p != null)
			{
				if(p.getId().equals(endPlace))
				{
					Place temp = getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
						dice.removeDice(0);
						dice.removeDice(1);
						graphic.updateDice(dice);
						return;
					}
					temp = getPlacePlusN(getPlace(startPlace), dice.getValue(1), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
						dice.removeDice(0);
						dice.removeDice(1);
						graphic.updateDice(dice);
					}
					return;
				}
			}
		}
		if(dice.size() > 2)
		{
			Place p = getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2), allHome);
			if(p != null)
			{
				if(p.getId().equals(endPlace))
				{
					Place temp = getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
					}
					temp = getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
					}
					dice.removeDice(0);
					dice.removeDice(1);
					dice.removeDice(2);
					graphic.updateDice(dice);
					return;
				}
			}
		}
		if(dice.size() > 3)
		{
			Place p = getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2) + dice.getValue(3), allHome);
			if(p != null)
			{
				if(p.getId().equals(endPlace))
				{
					Place temp = getPlacePlusN(getPlace(startPlace), dice.getValue(0), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
					}
					temp = getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
					}
					temp = getPlacePlusN(getPlace(startPlace), dice.getValue(0) + dice.getValue(1) + dice.getValue(2), allHome);
					if(isLegalEndPlace(temp))
					{
						bumpDices(temp.getId());
					}
					dice.removeDice(0);
					dice.removeDice(1);
					dice.removeDice(2);
					dice.removeDice(3);
					graphic.updateDice(dice);
					return;
				}
			}
		}
	}
	
	private Player getOtherPlayer()
	{
		if(currentPlayer.getId().equals("0"))
		{
			return board.getPlayers().get("1");
		}
		else if(currentPlayer.getId().equals("1"))
		{
			return board.getPlayers().get("0");
		}
		return null;
	}
	
	private void bumpDices(String start)
	{
		if(getPlace(start).getOwner() != currentPlayer)
		{
			if(getPlace(start).size() > 0)
			{
				board.moveCheckerFromTo(getPlace(start), getPlace("bar" + getOtherPlayer().getId()));
				graphic.moveCheckerFromTo(start, "bar" + getOtherPlayer().getId());
			}
		}
	}

	public void executeMove() {
		removeDices();
		if(getPlace(endPlace).getOwner() != currentPlayer)
		{
			if(getPlace(endPlace).size() > 0)
			{
				board.moveCheckerFromTo(getPlace(endPlace), getPlace("bar" + getOtherPlayer().getId()));
				graphic.moveCheckerFromTo(endPlace, "bar" + getOtherPlayer().getId());
			}
		}
		board.moveCheckerFromTo(getPlace(startPlace), getPlace(endPlace));
		graphic.moveCheckerFromTo(startPlace, endPlace);
		startPlace = "";
		endPlace = "";
	}
	

	public boolean won() {
		return currentPlayer.won();
	}

	public void finishGame() {
		// TODO Auto-generated method stub
		
	}

	public boolean diceLeft() {
		for(int i = 0; i < dice.size(); i++)
		{
			if(!dice.isUsed(i))
			{
				return true;
			}
		}
		return false;
	}
	
	public Dice getDice()
	{
		return dice;
	}
	
	public void nextPlayer()
	{
		System.out.print("player change! From: " + currentPlayer.getId() + " to ");
		if(currentPlayer.getId().equals("0"))
			currentPlayer = board.getPlayers().get("1");
		else
			currentPlayer = board.getPlayers().get("0");
		dice.setPlayer(currentPlayer);
		System.out.println(currentPlayer.getId());
	}
	
	public Player getPlayer()
	{
		return currentPlayer;
	}

	public Place getPlace(String place) {
		return board.getPlaces().get(place);
	}

	public State getCurrentState() {
		return this.currentState;
	}

	public void setSound(Boolean sound) {
		graphic.sound(sound);
	}

}
