/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */
public class Bar extends Place {
	Player player;
	
	public Bar(Player player, String id)
	{
		super(id);
		this.player = player;
	}
	
	@Override
	public Player getOwner()
	{
		return player;
	}
}
