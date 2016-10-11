package bg.backgammon2;

import java.util.LinkedHashMap;

import controller.*;
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
	
    public static void main( String[] args )
    {
    	launch(args);
    }
    
    @Override
    public void start(Stage stage) {
    	LinkedHashMap<String, Object> config;
    	
    	config = readConfig("config.xml");
    	
    	GameController engine = new GameController();

    	Graphic graphic = new Graphic(engine, stage, config);

        
    }
    
    private LinkedHashMap<String, Object> readConfig(String filename)
    {
    	// TODO XML Parser Implementieren
    	LinkedHashMap<String, Object> config = new LinkedHashMap<String, Object>();
    	// Innere Fenster Größe
    	config.put("width", 800);
    	config.put("height", 400);
    	// Mindestgröße des Fensters
    	config.put("minwidth", 600);
    	config.put("minheight", 300);

    	// Place Größe
    	config.put("pointWidth", 30);
    	// Place Größe
    	config.put("barWidth", 30);
    	// Place Größe
    	config.put("goalWidth", 30);
    	// Checker Größe
    	config.put("checkerWidth", 10);
    	// Board Hintergrund
    	config.put("bgImage", "res/map1.jpg");
    	// Point
    	config.put("pointImage", "res/point.png");
    	// Bar
    	config.put("bar0Image", "res/bar.png");
    	config.put("bar1Image", "res/bar.png");
    	// Goal
    	config.put("goal0Image", "res/goal.png");
    	config.put("goal1Image", "res/goal.png");
    	// Checker
    	config.put("checker0Image", "res/checker.png");
    	config.put("checker1Image", "res/checker.png");
    	

    	// Point Position
    	config.put("point0PositionX", -230);
    	config.put("point0PositionY", -190);
    	
    	config.put("point1PositionX", -200);
    	config.put("point1PositionY", -110);

    	config.put("point2PositionX", -180);
    	config.put("point2PositionY", -50);

    	config.put("point3PositionX", -270);
    	config.put("point3PositionY", -150);

    	config.put("point4PositionX", -270);
    	config.put("point4PositionY", -150);

    	config.put("point4PositionX", -270);
    	config.put("point4PositionY", -150);

    	config.put("point5PositionX", -270);
    	config.put("point5PositionY", -150);

    	config.put("point6PositionX", -270);
    	config.put("point6PositionY", -150);

    	config.put("point7PositionX", -270);
    	config.put("point7PositionY", -150);

    	config.put("point8PositionX", -270);
    	config.put("point8PositionY", -150);

    	config.put("point9PositionX", -270);
    	config.put("point9PositionY", -150);

    	config.put("point10PositionX", -270);
    	config.put("point10PositionY", -150);

    	config.put("point11PositionX", -270);
    	config.put("point11PositionY", -150);

    	config.put("point12PositionX", -270);
    	config.put("point12PositionY", -150);

    	config.put("point13PositionX", -270);
    	config.put("point13PositionY", -150);

    	config.put("point14PositionX", -270);
    	config.put("point14PositionY", -150);

    	config.put("point15PositionX", 100);
    	config.put("point15PositionY", 120);

    	config.put("point16PositionX", 100);
    	config.put("point16PositionY", 100);

    	config.put("point17PositionX", 100);
    	config.put("point17PositionY", 120);

    	config.put("point18PositionX", 100);
    	config.put("point18PositionY", 120);

    	config.put("point19PositionX", 100);
    	config.put("point19PositionY", 210);

    	config.put("point20PositionX", 100);
    	config.put("point20PositionY", 210);

    	config.put("point21PositionX", 100);
    	config.put("point21PositionY", 210);

    	config.put("point22PositionX", 120);
    	config.put("point22PositionY", 210);

    	config.put("point23PositionX", 120);
    	config.put("point23PositionY", 230);
    	
    	// Bar 1 Position
    	config.put("bar0PositionX", -290);
    	config.put("bar0PositionY", -160);
    	// Bar 2 Position
    	config.put("bar1PositionX", 270);
    	config.put("bar1PositionY", 150);

    	// Goal 1 Position
    	config.put("goal0PositionX", -260);
    	config.put("goal0PositionY", -140);
    	// Goal 2 Position
    	config.put("goal1PositionX", 260);
    	config.put("goal1PositionY", 140);
    	
    	// Checkers hinzufügen
    	config.put("point0CheckersNumber", 3);
    	config.put("point0CheckersPlayer", 0);

    	config.put("point1CheckersNumber", 0);
    	config.put("point1CheckersPlayer", 0);

    	config.put("point2CheckersNumber", 10);
    	config.put("point2CheckersPlayer", 1);
    	
    	return config;
    }
}
