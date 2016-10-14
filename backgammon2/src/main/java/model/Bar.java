/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */
public class Bar extends Place {
	
	public Bar(Player player, String id)
	{
		super(player, id);
	}
	

	@Override
	public int getIntId()
	{
		return -1;
	}
}
