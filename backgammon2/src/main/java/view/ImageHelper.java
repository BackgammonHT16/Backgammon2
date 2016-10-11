/**
 * 
 */
package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author philipp
 *
 */
public class ImageHelper extends ImageView {
	public ImageHelper(String filename)
	{
		LoadImage(filename);
	}
	public ImageHelper(String filename, int width)
	{
		LoadImage(filename, width);
	}
	public ImageHelper(String filename, int width, boolean isClickable)
	{
		LoadImage(filename, width, isClickable);
	}
	public ImageHelper(
			String filename, 
			Integer width, 
			boolean isClickable, 
			Integer x, 
			Integer y)
	{
		LoadImage(filename, width, isClickable, x, y);
	}
	
	private void LoadImage(String filename)
	{
		setImage(new Image("file:" + filename));
        setPreserveRatio(true);
        setSmooth(true);
        setCache(true);
	}

	private void LoadImage(String filename, int width)
	{
		LoadImage(filename);
		setFitWidth(width);
	}

	private void LoadImage(String filename, int width, boolean isClickable)
	{
		LoadImage(filename, width);
		setMouseTransparent(!isClickable);
	}
	
	private void LoadImage(
			String filename, 
			int width, 
			boolean isClickable, 
			int x, 
			int y)
	{
		LoadImage(filename, width, isClickable);
		setTranslateX(x);
		setTranslateY(y);
	}
}
