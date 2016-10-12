package bg.backgammon2;

import java.util.LinkedHashMap;

import controller.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.Graphic;

/**
 * Backgammon
 * 
 */
public class App extends Application
{
	
	//testKommentar
	
    public static void main( String[] args )
    {
    	launch(args);
    }
    
    @Override
    public void start(Stage stage) {
    	LinkedHashMap<String, Object> config;
    	
    	config = readConfig("config.xml");
    	
    	final GameEngine engine = new GameEngine(stage, config);


    	new AnimationTimer()
        {
    		long delta = 0l;
    		long lastTime =0;
        	public void handle(long currentNanoTime)
        	{
    			// Update the state (convert to seconds)
    			engine.update((float)(delta / 1000000000.0));
    			// Render the world
    			//engine.render(gc);
    			
    			delta = currentNanoTime - lastTime;
    			lastTime = currentNanoTime;
        	}
        }.start();
    }
    
    private LinkedHashMap<String, Object> readConfig(String filename)
    {
    	// TODO XML Parser Implementieren
    	LinkedHashMap<String, Object> config = new LinkedHashMap<String, Object>();
    	// Innere Fenster Größe
    	config.put("width", 800);
    	config.put("height", 400);
    	// Mindestgröße des Fensters
    	config.put("minWidth", 800);
    	config.put("minHeight", 400);
    	
    	// Anzahl der Points
    	config.put("numberOfPoints", 24);

    	// Place Größe
    	config.put("pointWidth", 40);
    	// Place Größe
    	config.put("barWidth", 40);
    	// Place Größe
    	config.put("goalWidth", 40);
    	// Checker Größe
    	
    	
    	config.put("checkerWidth", 15);
    	// Board Hintergrund
    	config.put("bgImage", "res/map1.jpg");
    	// Point
    	config.put("pointImage", "res/point.png");
    	// Bar
    	config.put("bar0Image", "res/bar0.png");
    	config.put("bar1Image", "res/bar1.png");
    	// Goal
    	config.put("goal0Image", "res/goal0.png");
    	config.put("goal1Image", "res/goal1.png");
    	// Checker
    	config.put("checker0Image", "res/checker0.png");
    	config.put("checker1Image", "res/checker1.png");
    	
    	// Menu
    	config.put("menuButtonImage", "res/menu3.png");
    	config.put("menuButtonWidth", 120);
    	config.put("menuButtonPositionX", -335);
    	config.put("menuButtonPositionY", 170);
    	

    	// Dice
    	config.put("dicePositionX", -335d);
    	config.put("dicePositionY", 120d);
    	config.put("diceWidth", 30);
    	config.put("diceImage", "res/dice");
    	// Point Position
    	config.put("point0PositionX", -300);
    	config.put("point0PositionY", -180);
    	
    	config.put("point1PositionX", -230);
    	config.put("point1PositionY", -180);

    	config.put("point2PositionX", -230);
    	config.put("point2PositionY", -80);

    	config.put("point3PositionX", -230);
    	config.put("point3PositionY", 20);

    	config.put("point4PositionX", -230);
    	config.put("point4PositionY", 120);

    	config.put("point5PositionX", -160);
    	config.put("point5PositionY", 120);

    	config.put("point6PositionX", -90);
    	config.put("point6PositionY", 120);

    	config.put("point7PositionX", -90);
    	config.put("point7PositionY", 20);

    	config.put("point8PositionX", -90);
    	config.put("point8PositionY", -80);

    	config.put("point9PositionX", -90);
    	config.put("point9PositionY", -180);

    	config.put("point10PositionX", -20);
    	config.put("point10PositionY", -180);

    	config.put("point11PositionX", 50);
    	config.put("point11PositionY", -180);

    	config.put("point12PositionX", 50);
    	config.put("point12PositionY", -80);

    	config.put("point13PositionX", 50);
    	config.put("point13PositionY", 20);

    	config.put("point14PositionX", 50);
    	config.put("point14PositionY", 120);

    	config.put("point15PositionX", 120);
    	config.put("point15PositionY", 120);

    	config.put("point16PositionX", 190);
    	config.put("point16PositionY", 120);

    	config.put("point17PositionX", 190);
    	config.put("point17PositionY", 20);

    	config.put("point18PositionX", 190);
    	config.put("point18PositionY", -80);

    	config.put("point19PositionX", 190);
    	config.put("point19PositionY", -180);

    	config.put("point20PositionX", 260);
    	config.put("point20PositionY", -180);

    	config.put("point21PositionX", 330);
    	config.put("point21PositionY", -180);

    	config.put("point22PositionX", 330);
    	config.put("point22PositionY", -80);

    	config.put("point23PositionX", 330);
    	config.put("point23PositionY", 20);
    	
    	// Bar 1 Position
    	config.put("bar0PositionX", -370);
    	config.put("bar0PositionY", -180);
    	// Bar 2 Position
    	config.put("bar1PositionX", 300);
    	config.put("bar1PositionY", 120);

    	// Goal 1 Position
    	config.put("goal0PositionX", 370);
    	config.put("goal0PositionY", 120);
    	// Goal 2 Position
    	config.put("goal1PositionX", -370);
    	config.put("goal1PositionY", -120);
    	
    	// Checkers hinzufügen
    	config.put("point0CheckersNumber", 2);
    	config.put("point0CheckersPlayer", 0);

    	config.put("point5CheckersNumber", 5);
    	config.put("point5CheckersPlayer", 1);

    	config.put("point7CheckersNumber", 3);
    	config.put("point7CheckersPlayer", 1);

    	config.put("point11CheckersNumber", 5);
    	config.put("point11CheckersPlayer", 0);

    	config.put("point12CheckersNumber", 5);
    	config.put("point12CheckersPlayer", 1);

    	config.put("point16CheckersNumber", 3);
    	config.put("point16CheckersPlayer", 0);

    	config.put("point18CheckersNumber", 5);
    	config.put("point18CheckersPlayer", 0);

    	config.put("point23CheckersNumber", 2);
    	config.put("point23CheckersPlayer", 1);

    	
    	return config;
    }
}
