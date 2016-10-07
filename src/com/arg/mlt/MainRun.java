package com.arg.mlt;

import java.util.ArrayList;
import java.util.List;

public class MainRun {

	public static void main(String[] agrs) 
	{
		RandomNumber random = new RandomNumber();
		ArrayList<Integer> lst = random.createRandom(10000, 20);
		Task task = new Task();
		List<Integer> result = task.findNumDivideByThree(lst, 5, 4);
		
		System.out.println("-----\nFinal Result:\n" + result + "\n---------");
	}
}
