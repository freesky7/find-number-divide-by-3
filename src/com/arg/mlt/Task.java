package com.arg.mlt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task {

	List<Integer> result;

	public Task() {
		result = Collections.synchronizedList(new ArrayList<Integer>());
	}
	/**
	 * 
	 * @param lst
	 * @param batchSize
	 * @return
	 */
	public List<Integer> findNumDivideByThree(ArrayList<Integer> lst, int batchSize) {

		int batchNum = (int) Math.ceil(lst.size() / batchSize);

		for (int i = 1; i <= batchNum; i++) {
			//TODO Break tasks and run parallel
		}
		return result;
	}
	/**
	 * 
	 * @param lst
	 * @param batchNum
	 * @param batchSize
	 * @return
	 */
	public List<Integer> doTask(ArrayList<Integer> lst, int batchNum, int batchSize) {
		
		for (int index = batchSize * batchNum + 1; index < batchSize * (batchNum + 1); index++) {

			int lastIndex = (batchSize * (batchNum + 1) > lst.size()) ? lst.size() : batchSize * (batchNum + 1);
			if (index < lastIndex) {
				if ((lst.get(index - 1) % 3) == 0) {
					synchronized (result) {
						result.add(lst.get(index - 1));
					}
				}
			} else {
				if ((lst.get(lastIndex - 1) % 3) == 0) {
					synchronized (result) {
						result.add(lst.get(lastIndex - 1));
					}
					break;
				}
			}
		}
		return null;
	}
}
