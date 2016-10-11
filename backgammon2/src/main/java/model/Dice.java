package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Dice {
	private List<Integer> value;// = new ArrayList<Integer>(2);
	
	
	public List<Integer> role()
	{
		value = new ArrayList<Integer>(Arrays.asList(singleRole(), singleRole()));
				
		// Pasch
		if(value.get(0) == value.get(1))
		{
			value.add(value.get(0));
			value.add(value.get(0));
		}
		
		return value;
	}
	
	public Integer roleSingleNumber()
	{
		if(value == null)
		{
			value = new ArrayList<Integer>();
		}
		
		value.add(singleRole());
		
		//Pasch
		if(value.size() == 2 && value.get(0) == value.get(1))
		{
			value.add(value.get(0));
			value.add(value.get(0));
		}
		
		return value.get(value.size() - 1);
	}
	
	private void resetDice() {
		value = new ArrayList<Integer>();
	}

	public Integer getValue(int index)
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
		return value;
	}
	
	private Integer singleRole()
	{
		Random r = new Random();
		// Zufällige Zahl zwischen 1 und 6
		return r.nextInt(6) + 1;
	}
}
