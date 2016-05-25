package io.github.lxgaming.discordmusicbot.util;

public class ThreadManager {
	
	public static void Thread() {
		//Download songs in Thread
		//Check if player is playing, wait till stop then play new song
		
		new Thread(new Runnable() {
			public void run() {
				loop1();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				loop2();
			}
		}).start();
	}
	
	private static void loop1() {
		while (true) {
			ConsoleOutput.info("HelloWorld1");
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				
			}
		}
	}
	
	private static void loop2() {
		while (true) {
			ConsoleOutput.info("HelloWorld2");
			try {
				Thread.sleep(5000);
			} catch (Exception ex) {
				
			}
		}
	}
}
