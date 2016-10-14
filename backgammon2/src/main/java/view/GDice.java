/**
 * 
 */
package view;

import java.util.Arrays;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import model.Dice;

/**
 * @author philipp
 *
 */
public class GDice {
	private Image[] image;
	private Image[] imageUsed;
	private ImageView[] imageView;
	private Group box;
	
	public GDice(Group box, Image[] image, Image[] imageUsed, int width)
	{
		this.box = box;
		this.image = image;
		this.imageUsed = imageUsed;
		imageView = new ImageView[4];
		for(int i = 0; i < 4; i++)
		{
			imageView[i] = new ImageHelper(image[0], width, false);
			imageView[i].setTranslateX((double)i* width);
		}
		imageView[0].setImage(image[2]);
		this.box = box;
		this.box.getChildren().addAll(imageView);
		box.setPickOnBounds(true);
		box.setMouseTransparent(false);
	}
	
	public void setDice(Dice dice)
	{
		for(int i = 0; i < 4; i++)
		{
			if(i < dice.size())
			{
				if(dice.isUsed(i))
				{
					imageView[i].setImage(imageUsed[dice.getAbsValue(i)]);
				}
				else
				{
					imageView[i].setImage(image[dice.getAbsValue(i)]);
				}
			}
			else
			{
				imageView[i].setImage(image[0]);
			}
		}
	}

	public void updateDice(Dice dice) {
		for(int i = 0; i < 4; i++)
		{
			if(i < dice.size())
			{
				if(dice.isUsed(i))
				{
					imageView[i].setImage(imageUsed[dice.getAbsValue(i)]);
				}
				else
				{
					imageView[i].setImage(image[dice.getAbsValue(i)]);
				}
			}
			else
			{
				imageView[i].setImage(image[0]);
			}
		}
	}
	
}
