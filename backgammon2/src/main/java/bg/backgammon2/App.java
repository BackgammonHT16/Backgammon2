package bg.backgammon2;

import java.io.File;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controller.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.Graphic;

/**
 * Einstiegsklasse des Spiels. 
 * 
 */
public class App extends Application
{
	protected static final Logger log = LogManager.getLogger(App.class);

	/**
	 * main Einstiegspunkt des Programms
	 * 
	 * @param args Wird nicht verwendet.
	 */
    public static void main( String[] args )
    {
    	launch(args);
    }
    
    
    // obsolete
    @Override
    public void start(Stage stage) {
    	LinkedHashMap<String, Object> config;
    	
    	
    	config = readFromXML("config.xml");
    	
    	final GameEngine engine = new GameEngine(stage, config);
    }
    
    // obsolete
    private static LinkedHashMap<String, Object> readFromXML(String filename){
		  try {

				File file = new File("res/config1.xml");

				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
			                             .newDocumentBuilder();

				Document doc = dBuilder.parse(file);

				
				if (doc.hasChildNodes()) {

					NodeList nodeList=doc.getChildNodes().item(0).getChildNodes();
					LinkedHashMap<String, Object> config = new LinkedHashMap<String, Object>();
					  for (int count = 0; count < nodeList.getLength(); count++) {
						  	
							Node tempNode = nodeList.item(count);
							// make sure it's element node.
							if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

								System.out.println(tempNode.getNodeName()+ " " + tempNode.getTextContent());
								if(((String)tempNode.getTextContent()).matches("^-?\\d+$"))
								config.put(tempNode.getNodeName(), Integer.parseInt(tempNode.getTextContent()));
								else
								{
									config.put(tempNode.getNodeName(), tempNode.getTextContent());
								}
							}
							
					  }
					  return config;

				}

			    } catch (Exception e) {
				System.out.println(e.getMessage());
			    }

			  
		  return null;
	  }
    
    
    // obsolete
    private LinkedHashMap<String, Object> readConfigStatic(String filename)
    {
    	// TODO XML Parser Implementieren
    	LinkedHashMap<String, Object> config = new LinkedHashMap<String, Object>();
    	
    	// Spieler beginnt mit Farbe blau
		config.put("colorHuman", 1);
    	
    	// Sound File
    	config.put("soundFile", "res/sound_long.wav");
    	
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
    	config.put("pointSelectedImage", "res/pointGlow.png");
    	// Bar
    	config.put("bar0Image", "res/bar0.png");
    	config.put("bar0SelectedImage", "res/bar0Glow.png");
    	config.put("bar1Image", "res/bar1.png");
    	config.put("bar1SelectedImage", "res/bar1Glow.png");
    	// Goal
    	config.put("goal0Image", "res/goal0.png");
    	config.put("goal0SelectedImage", "res/goal0Glow.png");
    	config.put("goal1Image", "res/goal1.png");
    	config.put("goal1SelectedImage", "res/goal1Glow.png");
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
    	config.put("diceImageUsed", "res/diceUsed");
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
    	
/**/    	// Checkers hinzufügen
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
/**/
/*
    	config.put("point0CheckersNumber", 2);
    	config.put("point0CheckersPlayer", 1);

    	config.put("point23CheckersNumber", 2);
    	config.put("point23CheckersPlayer", 0);
*/    	
    	return config;
    }
}
