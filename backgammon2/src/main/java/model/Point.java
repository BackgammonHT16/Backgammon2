/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */
public class Point extends Place {
	public Point(String id)
	{
		super(id);
	}

	@Override
	public int getIntId()
	{
		return Integer.parseInt(id.substring(5, id.length()));
	}
}
