package bg.backgammon2;

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
    	GameController engine = new GameController();

    	Graphic graphic = new Graphic(engine, stage);

        
    }
}
