/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author philipp
 *
 */
public abstract class GPlace {
	private ImageView image;
	private Image imageNormal;
	private Image imageSelected;
	private ArrayList<GChecker> checkers;
	final String id;
	int checkerSize;
	
	public GPlace(ImageView image, String id, int checkerSize, Image imageNormal, Image imageSelected)
	{
		this.image = image;
		this.imageNormal = imageNormal;
		this.imageSelected = imageSelected;
		this.id = id;
		checkers = new ArrayList<GChecker>();
		this.checkerSize = checkerSize;
	}
	
	public void setSelected()
	{
		image.setImage(imageSelected);
	}

	public void setNormal()
	{
		image.setImage(imageNormal);
	}
	
	public Position register(GChecker checker)
	{
		checkers.add(checker);
		System.out.println("GRAPHIC Checker " + checker.id + " at " + this.id + 
				" registered. Current size is " + checkers.size());
		return getNewPosition();
	}
	
	private Position getNewPosition()
	{
		double x = - image.getFitWidth() / 2 + 1.0 * checkerSize * (int)((checkers.size()-1) % 3 - 1);
		double y = - image.getFitHeight() / 2 + 1.0 * checkerSize * (int)((checkers.size()-1) / 3 + 2);

		x = Math.cos(image.getRotate() / 180 * Math.PI) * x - Math.sin(image.getRotate() / 180 * Math.PI) * y;
		y = Math.sin(image.getRotate() / 180 * Math.PI) * x + Math.cos(image.getRotate() / 180 * Math.PI) * y;

		x = image.getTranslateX() + image.getFitWidth() / 2 + x;
		y = image.getTranslateY() + image.getFitHeight() / 2 + y;
		
		// x = image.getTranslateX() + 1.0 * checkerSize * (int)((checkers.size()-1) % 3 - 1);
		// y = image.getTranslateY() + 1.0 * checkerSize * (int)((checkers.size()-1) / 3 + 2);
		return new Position(x, y, image.getRotate());
	}
	
	public void moveCheckerTo(GPlace place)
	{
		getLastChecker().moveTo(place);
	}
	
	public void unregister(GChecker checker)
	{
		checkers.remove(checker);
		System.out.println("GRAPHIC Checker " + checker.id + " from " + this.id + 
				" removed. Current size is " + checkers.size());
	}
	
	private GChecker getLastChecker()
	{
		return checkers.get(checkers.size() - 1);
	}

}
