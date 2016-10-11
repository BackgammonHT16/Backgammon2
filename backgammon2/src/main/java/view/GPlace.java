/**
 * 
 */
package view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javafx.scene.image.ImageView;

/**
 * @author philipp
 *
 */
public abstract class GPlace {
	private ImageView image;
	private ArrayList<GChecker> checkers;
	final String id;
	
	public GPlace(ImageView image, String id)
	{
		this.image = image;
		this.id = id;
		checkers = new ArrayList<GChecker>();
	}
	
	public Position register(GChecker checker)
	{
		checkers.add(checker);
		return getNewPosition();
	}
	
	private Position getNewPosition()
	{
		double x = image.getTranslateX() - 15 + 15 * (int)((checkers.size()-1) % 3);
		double y = image.getTranslateY() + 20 + 10 * (int)((checkers.size()-1) / 3);
		return new Position(x, y);
	}
	
	public void moveCheckerTo(GPlace place)
	{
		getLastChecker().moveTo(place);
	}
	
	public void unregister(GChecker checker)
	{
		checkers.remove(checker);
	}
	
	private GChecker getLastChecker()
	{
		return checkers.get(checkers.size() - 1);
	}

}
