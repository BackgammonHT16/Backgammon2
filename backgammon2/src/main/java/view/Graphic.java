/**
 * 
 */
package view;

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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author philipp
 *
 */
public class Graphic {
	private GameController engine;
	private Stage stage;
	private Scene scene;
	
	private GBoard board;

	private int width = 800;
	private int height = 400;
	private int minWidth = 600;
	private int minHeight = 300;
	
	public Graphic(
			GameController engine, 
			Stage stage, 
			LinkedHashMap<String, Object> config
			)
	{
		this.engine = engine;
		this.stage = stage;
		

		// Root element initialisieren
        StackPane root = new StackPane();
        
		// Board
        board = new GBoard(config, root);
		//root.getChildren().add(board.getImage());
		
		// Places
		final Timeline timeline = new Timeline();
		ImageView p1 = loadImage("res/point.png", 50);
		p1.setTranslateX(-200);
		p1.setTranslateY(0);
		root.getChildren().add(p1);
		root.getChildren().add(new Circle(0, 0, 10));
		// MouseClick wird auch auf transperentem Hintergrund ausgelöst.
		p1.setPickOnBounds(true);
		p1.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        timeline.play();
                    	System.out.println("I was hit!");
                    }
                });
		
		
		
		// Checkers
		ImageView c1 = loadImage("res/checker.png", 50);
		c1.setTranslateX(-100);
		c1.setTranslateY(0);
		root.getChildren().add(c1);
		// MouseClick wird auch auf transparentem Hintergrund ausgelöst.
		c1.setMouseTransparent(true);
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, // set start position at 0
                new KeyValue(c1.translateXProperty(), c1.getTranslateX()),
                new KeyValue(c1.translateYProperty(), c1.getTranslateY())
            ),
            new KeyFrame(new Duration(2000), // set end position at 40s
                new KeyValue(c1.translateXProperty(), 0),
                new KeyValue(c1.translateYProperty(), 0)
            )
        );
		c1.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                        
                        // play 40s of animation
                    	System.out.println("I was hit!");
                    }
                });
		
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

}
