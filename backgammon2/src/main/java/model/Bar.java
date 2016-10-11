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
	public Bar(Player player)
	{
		this.player = player;
	}
	
	@Override
	public Player getOwner()
	{
		return player;
	}
}
