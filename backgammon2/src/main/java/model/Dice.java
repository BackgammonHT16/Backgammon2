package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Dice {
	private List<Integer> value;// = new ArrayList<Integer>(2);
	private List<Boolean> isUsed;
	private int sign = 1;

	public void setPlayer(Player player)
	{
		if(player != null)
		{
			if(player.getId().equals("0"))
			{
				sign = 1;
			}
			else
			{
				sign = -1;
			}
		}
	}
	
	
	public List<Integer> role()
	{
		value = new ArrayList<Integer>(Arrays.asList(singleRole(), singleRole()));
		isUsed = new ArrayList<Boolean>(Arrays.asList(false, false));
				
		// Pasch
		if(value.get(0).equals(value.get(1)))
		{
			value.add(value.get(0));
			isUsed.add(false);
			value.add(value.get(0));
			isUsed.add(false);
		}
		
		return value;
	}
	
	public Integer roleSingleNumber()
	{
		if(value == null)
		{
			value = new ArrayList<Integer>();
			isUsed = new ArrayList<Boolean>();
		}
		
		value.add(singleRole());
		isUsed.add(false);
		
		//Pasch
		if(value.size() == 2 && value.get(0).equals(value.get(1)))
		{
			value.add(value.get(0));
			isUsed.add(false);
			value.add(value.get(0));
			isUsed.add(false);
		}
		
		return value.get(value.size() - 1);
	}
	
	public Integer getValue(int index)
	{
		if(value != null)
		{
			if(value.size() > index)
			{
				return sign * value.get(index);
			}
		}
		
		return null;
	}
	
	public Integer getAbsValue(int index)
	{
		if(value != null)
		{
			if(value.size() > index)
			{
				return value.get(index);
			}
		}
		
		return null;
	}
	
	public List<Integer> getValues()
	{
		List<Integer> temp = new ArrayList<Integer>();
		for(Integer x : value)
		{
			temp.add(x * sign);
		}
		return temp;
	}
	
	private Integer singleRole()
	{
		Random r = new Random();
		// Zufällige Zahl zwischen 1 und 6
		return r.nextInt(6) + 1;
		//return 1;
	}
	
	public void removeDice(int i)
	{
		if(i < isUsed.size())
		{
			if(value.size() > 3 && value.get(i).equals(value.get(3)) && !isUsed.get(3))
			{
				isUsed.set(3, true);
			}
			else if(value.size() > 2 && value.get(i).equals(value.get(2)) && !isUsed.get(2))
			{
				isUsed.set(2, true);
			}
			else if(value.size() > 1 && value.get(i).equals(value.get(1)) && !isUsed.get(1))
			{
				isUsed.set(1, true);
			}
			else if(value.size() > 0 && value.get(i).equals(value.get(0)) && !isUsed.get(0))
			{
				isUsed.set(0, true);
			}
		}
	}
	
	public Boolean isUsed(int i)
	{
		if(i < isUsed.size())
		{
			return isUsed.get(i);
		}
		return true;
	}
	
	public int size()
	{
		return value.size();
	}
}
