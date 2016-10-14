/**
 * 
 */
package controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.GBoard;


/**
 * @author philipp
 *
 */
public class Menu extends State{
	State oldState;
	JFrame menu;
	GameEngine engine;
	boolean gameActive = false;
	
	public Menu(GameEngine engine, State oldState) {
		super(engine);
		this.oldState = oldState;
		this.engine = engine;
		
		
		if(oldState != null)
		{
			gameActive = true;
		}
        
        StackPane root = new StackPane();

		final Stage stage = new Stage();
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        
        Label color = new Label("Color");
        grid.add(color, 0, 0);
        
        ObservableList<String> optionsColor = 
        	    FXCollections.observableArrayList(
        	        "Blue",
        	        "Red"
        	    );
        final ComboBox comboBoxColor = new ComboBox(optionsColor);
        comboBoxColor.setValue(optionsColor.get(0));
        grid.add(comboBoxColor, 1, 0);
        
        

        Label difficulty = new Label("Difficulty");
        grid.add(difficulty, 0, 1);
        
        ObservableList<String> optionsDifficulty = 
        	    FXCollections.observableArrayList(
        	        "Easy",
        	        "Hard"
        	    );
        final ComboBox comboBoxDifficulty = new ComboBox(optionsDifficulty);
        comboBoxDifficulty.setValue(optionsDifficulty.get(0));
        grid.add(comboBoxDifficulty, 1, 1);
        
        

        Label sound = new Label("Sound");
        grid.add(sound, 0, 2);

        //A checkbox with a string caption
        final CheckBox checkBoxSound = new CheckBox();
        grid.add(checkBoxSound, 1, 2);

        

        Label time = new Label("Time");
        grid.add(time, 0, 3);
        
        ObservableList<String> optionsTime = 
        	    FXCollections.observableArrayList(
            	    "Endless",
            	    "5s",
        	        "10s", 
        	        "20s", 
        	        "40s"
        	    );
        final ComboBox comboBoxTime = new ComboBox(optionsTime);
        comboBoxTime.setValue(optionsTime.get(0));
        grid.add(comboBoxTime, 1, 3);
        
        
        AnchorPane anchorpane = new AnchorPane();
        final Button buttonStartGame = new Button("Start Game");
        Button buttonContinue = new Button("Continue Game");
        Button buttonQuit = new Button("Quit");

        buttonStartGame.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	Menu.this.setOptions(
                    			(String) comboBoxColor.getValue(), 
                    			(String) comboBoxDifficulty.getValue(), 
                    			checkBoxSound.isSelected(), 
                    			(String)comboBoxTime.getValue());
                    	int color = ((String) comboBoxColor.getValue()).equals("Blue") ? 0 : 1;
						int difficulty = ((String) comboBoxColor.getValue()).equals("Easy") ? 0 : 1;
						int time = 0;
						if(((String) comboBoxColor.getValue()).equals("Endless"))
						{
							time = -1;
						}
						else if(((String) comboBoxColor.getValue()).equals("5s"))
						{
							time = 5;
						}
						else if(((String) comboBoxColor.getValue()).equals("10s"))
						{
							time = 10;
						}
						else if(((String) comboBoxColor.getValue()).equals("20s"))
						{
							time = 20;
						}
						else if(((String) comboBoxColor.getValue()).equals("40s"))
						{
							time = 40;
						}
                    	Menu.this.engine.resetGame(color, difficulty, time);
                    	Menu.this.engine.setSound(checkBoxSound.isSelected());
                    	stage.hide();
                    }
                });

        buttonContinue.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	Menu.this.setOptions(
                    			(String) comboBoxColor.getValue(), 
                    			(String) comboBoxDifficulty.getValue(), 
                    			checkBoxSound.isSelected(), 
                    			(String)comboBoxTime.getValue());
                    	Menu.this.engine.setState(Menu.this.oldState);
                    	Menu.this.engine.setSound(checkBoxSound.isSelected());
                    	stage.hide();
                    }
                });
        
        buttonQuit.setOnMouseClicked(
                new EventHandler<MouseEvent>()
                {
                    public void handle(MouseEvent e)
                    {
                    	stage.hide();
                    	Menu.this.engine.quit();
                    }
                });

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().add(buttonStartGame);
        if(oldState != null)
        {
        	buttonContinue.setDefaultButton(true);
        	hb.getChildren().add(buttonContinue);
        }
        else
        {
            buttonStartGame.setDefaultButton(true);
        }
        hb.getChildren().add(buttonQuit);

        anchorpane.getChildren().addAll(grid,hb); 
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);
        
        root.getChildren().add(anchorpane);
        
        Scene scene = new Scene(root, 250, 300);

        // mindestgröße ist der Inhalt
        stage.minHeightProperty().bind(
        		Bindings.max(
        				250, 
        				stage.heightProperty().subtract(
        						scene.heightProperty()).add(
        								root.minHeightProperty()
        								)
        				)
        		);
        
        stage.minWidthProperty().bind(
        		Bindings.max(
        				300, 
        				stage.widthProperty().subtract(
        						scene.widthProperty()).add(
        								root.minWidthProperty()
        								)
        				)
        		);
        
        if(oldState != null)
        {
        	buttonContinue.requestFocus();
        }
        else
        {
            buttonStartGame.requestFocus();
        }
        
        buttonStartGame.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ev) {
			    if (ev.getCode() == KeyCode.ENTER || ev.getCode() == KeyCode.SPACE) {
			    	buttonStartGame.fire();
			       ev.consume(); 
			    }
			}
		});
        
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();

        
	}
	
	public void setOptions(String color, String difficulty, Boolean sound, String time)
	{
		//engine.setSound(sound);
		if(!gameActive)
		{
			//engine.resetGame();
		}
	}
	
	public void onCloseMenu()
	{
		engine.setState(oldState);
	}
	
	public void onClickMenu()
	{
		
	}

}
