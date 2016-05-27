package com.sermo.thread.pool;

import java.util.Random;

public class SimplePrintJob implements Runnable{
	
	private String jobName;
	
	public SimplePrintJob(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public void run() {
		System.out.println("jobName: " + jobName + " start ...");
		int random = 0;
		try {
			Random r = new Random();
			random = r.nextInt(10);
			Thread.sleep(random * 1000L); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("jobName: " + jobName + " end with sleep " +random);
	}

}
