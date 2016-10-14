/**
 * 
 */
package view;

import java.io.File;
import java.util.LinkedHashMap;

import controller.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Dice;
import model.Place;

/**
 * @author philipp
 *
 */
public class Graphic {
	private GameEngine engine;
	private Stage stage;
	private Scene scene;
	
	private GBoard board;
	private MediaPlayer mediaPlayer;

	private int width = 800;
	private int height = 400;
	private int minWidth = 600;
	private int minHeight = 300;
	
	public Graphic(
			GameEngine engine, 
			Stage stage, 
			LinkedHashMap<String, Object> config
			)
	{
		this.engine = engine;
		this.stage = stage;
		

		// Root element initialisieren
        StackPane root = new StackPane();
        
		// Board
        board = new GBoard(config, root, engine);

        width = (Integer)config.get("width");
        height = (Integer)config.get("height");
        minWidth = (Integer)config.get("minWidth");
        minHeight = (Integer)config.get("minHeight");
        
        // Sound
        initSound((String)config.get("soundFile"));
		
		// Places
		
		
		
		// Checkers
		
		// Würfel
		
		// Menu
		
		
		
        
        // Mindestgröße und Seitenverhältniss 
        scene = new Scene(root, width, height);

        // Seitenverhältniss
        // stage.minWidthProperty().bind(scene.heightProperty().multiply(2));
        // stage.minHeightProperty().bind(scene.widthProperty().divide(2));
        
        // mindestgröße ist der Inhalt
        stage.minHeightProperty().bind(
        		Bindings.max(
        				minHeight, 
        				stage.heightProperty().subtract(
        						scene.heightProperty()).add(
        								root.minHeightProperty()
        								)
        				)
        		);
        
        stage.minWidthProperty().bind(
        		Bindings.max(
        				minWidth, 
        				stage.widthProperty().subtract(
        						scene.widthProperty()).add(
        								root.minWidthProperty()
        								)
        				)
        		);
        

        stage.setScene(scene);
        stage.show();
	}
	
	private ImageView loadImage(String filename, int width)
	{
		ImageView iv = new ImageView();
		iv.setImage(new Image("file:" + filename));
		if(width > 0)
		{
			iv.setFitWidth(width);
		}
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
		return iv;
	}
	
	public void requestDiceRoll()
	{
		// TODO insert graphical dice activation
	}

	public void requestSelectStart(String[] points)
	{
		// TODO insert graphical point activation
	}

	public void requestSelectEnd(String[] ends)
	{
		// TODO insert graphical point activation
	}
	
	public void rollDice(Dice d)
	{
		board.rollDice(d);
	}
	
	public void moveCheckerFromTo(String from, String to)
	{
		board.moveCheckerFromTo(from, to);
	}

	public void updateDice(Dice dice) {
		board.updateDice(dice);
	}

	public void selectPlaces(LinkedHashMap<String, Place> places) {
		board.selectPlaces(places);
	}
	public void unselectAllPlaces() {
		board.unselectAllPlaces();
	}
	
	public void sound(Boolean play)
	{
		if(play)
		{
			mediaPlayer.play();
		}
		else
		{
			mediaPlayer.stop();
		}
	}
	

	private void initSound(String musicFile) {
		Media media = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
	}

}
