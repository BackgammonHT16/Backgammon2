/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */
public class Goal extends Place {

	Player player;
	public Goal(Player player)
	{
		this.player = player;
	}

	@Override
	public Player getOwner()
	{
		return player;
	}
}
