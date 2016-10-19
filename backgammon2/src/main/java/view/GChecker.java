/**
 * 
 */
package view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
		//image.setRotate(p.rotation);
	}
	
	public void moveTo(GPlace place)
	{
		this.place.unregister(this);
		Position p = place.register(this);
		this.place = place;
		startAnimationTo(p);
	}
	
	private void startAnimationTo(Position p)
	{

		final Timeline t = new Timeline();
		t.getKeyFrames().addAll(
	            new KeyFrame(Duration.ZERO, // set start position at 0
	                new KeyValue(image.translateXProperty(), image.getTranslateX()),
	                new KeyValue(image.translateYProperty(), image.getTranslateY())/*,
	                new KeyValue(image.rotateProperty(), image.getRotate())*/
	            ),
	            new KeyFrame(new Duration(2000), // set end position at 40s
	                new KeyValue(image.translateXProperty(), p.x),
	                new KeyValue(image.translateYProperty(), p.y)/*,
	                new KeyValue(image.rotateProperty(), p.rotation)*/
	            )
	        );
		t.play();
	}

}
