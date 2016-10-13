/**
 * 
 */
package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import model.*;
import view.*;

/**
 * @author philipp
 *
 */


abstract class Call<T> implements Callable<T>
{
	abstract T call(double x, double y);
}

public class GameEngine {
	private LinkedHashMap<String, Object> config;
	private Stage stage;
	private Board board;
	
	
	
	private Player currentPlayer;
	private State currentState;
	private Dice dice;
	private CurrentMove currentMove;
	
	private Dice HumanDice;
	private Place HumanStartPlace;
	private Place HumanEndPlace;
	
	private Place StartPlace;
	private Place EndPlace;
	
	private Call<Integer> onClick;
	private Graphic graphic;
	
	public GameEngine(Stage stage, LinkedHashMap<String, Object> config)
	{
		this.config = config;
		this.stage = stage;
		board = new Board(this, config);
    	graphic = new Graphic(this, stage, config);
    	dice = new Dice();
    	currentPlayer = board.getPlayers().get("0");
    	currentState = new Start1(this);
	}
	
	
	

	public void onClickPlace()
	{
		
	}
	
	public void onClickMenu()
	{
		
	}
	
	public void onClickDice()
	{
		
	}
	
	
	
	
	public CurrentMove getCurrentMove()
	{
		return currentMove;
	}

	public void setCurrentState()
	{
		currentMove = new CurrentMove(this);
	}

	public void resetCurrentState()
	{
		currentMove = null;
	}
	
	public void clickedOnPlace(String id)
	{
		System.out.println("Hit Place: " + id);
		if(currentMove != null)
		{
			currentMove.onClick(id);
		}
	}
	
	public void clickedOnDice()
	{
		System.out.println("Hit Dice");
		if(onClick != null)
		{
			onClick.call(1, 2);
		}
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
	
	public Dice requestHumanRollDice()
	{
		onClick = new Call<Integer>(){
			public Integer call(double x, double y) {
				return onClickRoleDice(x, y);
			}
			public Integer call(){
				return onClickRoleDice();
			};
			};
		Dice temp = HumanDice;
		HumanDice = null;
		return temp;
	}
	
	public Dice requestHumanRollSingleDice()
	{
		onClick = new Call<Integer>(){
			public Integer call(double x, double y) {
				return onClickRoleSingleDice();
			}
			public Integer call(){
				return onClickRoleSingleDice();
			};
			};
		Dice temp = HumanDice;
		HumanDice = null;
		return temp;
	}
	
	public Place requestHumanChooseStart()
	{
		onClick = new Call<Integer>(){
			public Integer call(double x, double y) {
				return onClickChooseStart(x, y);
			}
			public Integer call(){
				return onClickRoleDice();
			};
			};
		Place temp = HumanStartPlace;
		HumanStartPlace = null;
		return temp;
	}
	
	public Place requestHumanChooseEnd()
	{
		onClick = new Call<Integer>(){
			public Integer call(double x, double y) {
				return onClickChooseEnd(x, y);
			}
			public Integer call(){
				return onClickRoleDice();
			};
			};
		Place temp = HumanEndPlace;
		HumanEndPlace = null;
		return temp;
	}
	
	public void setState(State state)
	{
		currentState = state;
	}
	
	
	public boolean allCheckersInHomeField()
	{
		boolean allCheckersAreHome = true;
		/*List<Checker> c = board.getCheckers();
		for(int i = 0; i < c.size(); i++)
		{
			if(c.get(i).getPlayer() == currentPlayer)
			{
				if(c.get(i).getPlace() instanceof Bar)
				{
					allCheckersAreHome = false;
				}
				else if(c.get(i).getPlace() instanceof Point) 
				{
					if(currentPlayer == board.getPlayers().get(0))
					{
						if(i > 5)
						{
							allCheckersAreHome = false;
						}
					}
					else if(currentPlayer == board.getPlayers().get(1))
					{
						if(i < 18)
						{
							allCheckersAreHome = false;
						}
					}
				}
			}
		}*/
		return allCheckersAreHome;
	}
	
	
	public LinkedHashMap<String, Place> getLegalStartPlaces()
	{
		// TODO gültige Startplätze für aktuellen Spieler ermmitteln
		List<Place> result = new ArrayList<Place>();
		/*List<Place> p = board.getPlaces();
		int sign = -1;
		if(currentPlayer == board.getPlayers().get(0))
		{
			sign = 1;
		}
		boolean allHome = allCheckersInHomeField();
		for(int i = 0; i < p.size(); i++)
		{
			if(p.get(i) instanceof Point)
			{
				Point point = (Point) p.get(i);
				if(point.size() > 0 && point.getOwner() == currentPlayer)
				{
					if(i + sign * dice.getValue(0).intValue() < 24 && 
							i + sign * dice.getValue(0).intValue() > 0)
					{
						if(isLegalEndPlace(p.get(i + sign * dice.getValue(0).intValue())))
						{
							result.add(p.get(i + sign * dice.getValue(0).intValue()));
						}
					}
					if(i + sign * dice.getValue(1).intValue() < 24 && 
							i + sign * dice.getValue(1).intValue() > 0)
					{
						if(isLegalEndPlace(p.get(i + sign * dice.getValue(1).intValue())))
						{
							result.add(p.get(i + sign * dice.getValue(1).intValue()));
						}
						// TODO Diese methode komplett überarbeiten
					}
				}
			}
		}*/
		
		//return null;
		
		return board.getPlaces();
	}

	public boolean isLegalStartPlace(Place place)
	{
		return true;
	}
	
	public boolean isLegalEndPlace(Place place)
	{
		return true;
	}

	public LinkedHashMap<String, Place> getLegalEndPlaces()
	{
		return getLegalEndPlaces(StartPlace);
	}
	
	public LinkedHashMap<String, Place> getLegalEndPlaces(Place startPlace)
	{
		// TODO gültige Endplätze für aktuellen Spieler ermmitteln
		//return null;
		return getLegalStartPlaces();
	}
	
	public boolean setStartPlace(Place p)
	{
		LinkedHashMap<String, Place> places = getLegalStartPlaces();
		// Der gewählte Platz ist gültig
		if(places != null && places.containsValue(p))
		{
			StartPlace = p;
			return true;
		}
		//return false;
		return true;
	}

	public boolean setEndPlace(Place p)
	{
		LinkedHashMap<String, Place> places = getLegalEndPlaces();
		// Der gewählte Platz ist gültig
		if(places != null && places.containsValue(p))
		{
			EndPlace = p;
			return true;
		}
		//return false;
		return true;
	}
	
	
	public void update(float deltaTime) {
		currentState.nextAction();
	}

	// TODO löschen
	public void render(GraphicsContext g) {
		//renderer.render(g);
	}

	public void executeMove() {
		// TODO Auto-generated method stub
		board.moveCheckerFromTo(currentMove.getStart(), currentMove.getEnd());
		graphic.moveCheckerFromTo(currentMove.getStart().getId(), currentMove.getEnd().getId());
	}

	public boolean won() {
		// TODO Auto-generated method stub
		return false;
	}

	public void finishGame() {
		// TODO Auto-generated method stub
		
	}

	public boolean diceLeft() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Dice getDice()
	{
		return dice;
	}
	
	public void nextPlayer()
	{
		System.out.print("player change! From: " + currentPlayer.getId() + " to ");
		if(currentPlayer.getId() == "0")
			currentPlayer = board.getPlayers().get("1");
		else
			currentPlayer = board.getPlayers().get("0");
		System.out.println(currentPlayer.getId());
	}
	
	public Player getPlayer()
	{
		return currentPlayer;
	}
	
	public Integer onClick(double x, double y)
	{
		try {
			return onClick.call(x, y);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Integer onClickRoleDice()
	{
		dice.role();
		graphic.rollDice(dice);
		HumanDice = dice;
		System.out.println("roling!");
		//Checker c = board.getPlaces().get(5).removeChecker();
		//board.getPlaces().get(1).addChecker(c);
		return 0;
	}
	public Integer onClickRoleDice(double x, double y)
	{
		dice.role();
		graphic.rollDice(dice);
		HumanDice = dice;
		System.out.println("hit!");
		return 0;
	}
	public Integer onClickRoleSingleDice()
	{
		dice.roleSingleNumber();
		graphic.rollDice(dice);
		HumanDice = dice;
		System.out.println("roleSingleNumber!");
		return 0;
	}
	
	public Integer onClickChooseStart(double x, double y)
	{
		HumanStartPlace = board.getPlaces().get(0);
		System.out.println("start!");
		return 0;
	}
	public Integer onClickChooseEnd(double x, double y)
	{
		HumanEndPlace = board.getPlaces().get(6);
		System.out.println("end!");
		return 0;
	}


	public Place getPlace(String place) {
		return board.getPlaces().get(place);
	}

	public void clickedOnMenuButton() {
		//new JFrame().setVisible(true);
		JDialog jd = new JDialog();
		jd.setModal(true);
		jd.setLocation(200, 200);
		jd.show();
	}
}
