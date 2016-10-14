/**
 * 
 */
package model;

/**
 * @author philipp
 *
 */
public class Goal extends Place {
	
	public Goal(Player player, String id)
	{
		super(player, id);
	}

	
	@Override
	public int getIntId()
	{
		return -1;
	}
}
