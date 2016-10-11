/**
 * 
 */
package view;

import java.util.LinkedHashMap;

import javafx.scene.image.ImageView;

/**
 * @author philipp
 *
 */
public abstract class GPlace {
	private ImageView image;
	private LinkedHashMap<String, GChecker> checkers;
	final String id;
	
	public GPlace(ImageView image, String id)
	{
		this.image = image;
		this.id = id;
		checkers = new LinkedHashMap<String, GChecker>();
	}
	
	public Position register(GChecker checker)
	{
		checkers.put(checker.id, checker);
		return getNewPosition();
	}
	
	private Position getNewPosition()
	{
		double x = image.getTranslateX() - 15 + 15 * (int)((checkers.size()-1) % 3);
		double y = image.getTranslateY() + 20 + 10 * (int)((checkers.size()-1) / 3);
		return new Position(x, y);
	}
	

}
