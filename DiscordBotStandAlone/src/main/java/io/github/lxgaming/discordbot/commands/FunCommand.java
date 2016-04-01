package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class FunCommand {
	
	private static Random rand = new Random();
	
	public static void fun(TextChannel channel, String command, User author) {
		if (command.equalsIgnoreCase("number")) {
			int number = rand.nextInt(100) + 1;
			channel.sendMessage("Your lucky number is " + number + "/100!");
		}
		
		if (command.equalsIgnoreCase("roll")) {
			int number = rand.nextInt(6) + 1;
			channel.sendMessage("You rolled a " + number);
		}
		
		if (command.equalsIgnoreCase("coin")) {
			int number = rand.nextInt(2);
			if (number == 0) {
				channel.sendMessage("Heads\n" + "https://goo.gl/Pg5RQN");
			} else if (number == 1) {
				channel.sendMessage("Tails\n" + "https://goo.gl/wgHmwb");
			}
		}
		
		if (command.equalsIgnoreCase("version")) {
			int first = rand.nextInt(10);
			int second = rand.nextInt(10);
			int third = rand.nextInt(10);
			channel.sendMessage("Version " + first + "." + second + "." + third);
		}
		return;
	}
}