/**
 * 
 */
package view;

import javafx.scene.image.ImageView;

/**
 * @author philipp
 *
 */
public class GChecker {
	private ImageView image;
	private GPlace place;
	final String id;
	
	public GChecker(GPlace place, ImageView image, String id)
	{
		this.image = image;
		this.place = place;
		this.id = id;
		setPosition(place.register(this));
	}
	
	private void setPosition(Position p)
	{
		image.setTranslateX(p.x);
		image.setTranslateY(p.y);
	}

}
