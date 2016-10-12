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
	int checkerSize;
	
	public GPlace(ImageView image, String id, int checkerSize)
	{
		this.image = image;
		this.id = id;
		checkers = new ArrayList<GChecker>();
		this.checkerSize = checkerSize;
	}
	
	public Position register(GChecker checker)
	{
		checkers.add(checker);
		return getNewPosition();
	}
	
	private Position getNewPosition()
	{
		double x = image.getTranslateX() + 1.5 * checkerSize * (int)((checkers.size()-1) % 3 - 1);
		double y = image.getTranslateY() + 1.0 * checkerSize * (int)((checkers.size()-1) / 3 + 2);
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
