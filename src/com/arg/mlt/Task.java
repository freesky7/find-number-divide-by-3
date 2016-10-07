package com.arg.mlt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
	public List<Integer> findNumDivideByThree(ArrayList<Integer> lst, int batchSize, int nThread) {

		int batchNum = (int) Math.ceil(lst.size() / batchSize);
		ExecutorService executor = Executors.newFixedThreadPool(nThread);
		
		Set<Future<List<Integer>>> futureLst = new HashSet<>();
		
		for (int i = 1; i <= batchNum; i++) {
			try {
				// TODO Break tasks and run parallel
				final int nBatch = i;

				Future<List<Integer>> future = executor.submit(() -> {
					System.out.println("Add task - " + nBatch);
					return doTask(lst, nBatch, batchSize);
				});
				futureLst.add(future);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		executor.shutdown();
		// executor.awaitTermination
		// (30, TimeUnit.MINUTES);
		while (!executor.isTerminated()) {
			
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
		List<Integer> batchRes = new ArrayList<Integer>();
		for (int index = batchSize * batchNum + 1; index < batchSize * (batchNum + 1); index++) {

			int lastIndex = (batchSize * (batchNum + 1) > lst.size()) ? lst.size() : batchSize * (batchNum + 1);
			if (index < lastIndex) {
				if ((lst.get(index - 1) % 3) == 0) {
					batchRes.add(lst.get(index - 1));
				}
			} else {
				if ((lst.get(lastIndex - 1) % 3) == 0) {
					batchRes.add(lst.get(lastIndex - 1));
					break;
				}
			}
		}
		synchronized (result) {
			result.addAll(batchRes);
		}
		return batchRes;
	}
}
