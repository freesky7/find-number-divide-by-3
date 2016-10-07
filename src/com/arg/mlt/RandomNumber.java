package com.arg.mlt;
import java.util.ArrayList;
import java.util.Random;

public class RandomNumber {

	public ArrayList<Integer> createRandom(int max, int number) 
	{
		if(number <0) 
		{
			return null;
		}
		Random random = new Random(5);
		ArrayList<Integer> lst = new ArrayList<>();
		for(int i = 0 ; i < number; i++) 
		{
			Integer num = random.nextInt(max);
			lst.add(num);
		}
		System.out.println(lst);
		return lst;
	}
}
