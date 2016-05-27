package com.sermo.thread.pool;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
// http://www.micmiu.com/lang/java/java-threadpoolexecutor/
public class JavaThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
				3, 5, 60, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 10; i++) {
			System.out.println("add job " + i + " at: " + new Date());
			SimplePrintJob job = new SimplePrintJob("job" + i);
			poolExecutor.execute(job);
		}
		System.out.println("execute all job");
		poolExecutor.shutdown();
		System.out.println("main program end");
	}
}
