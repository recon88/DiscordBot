package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class FunCommand {
	
	private static Random random = new Random();
	
	public static void fun(TextChannel channel, String command, User author) {
		if (command.equalsIgnoreCase("number")) {
			int number = random.nextInt(100) + 1;
			channel.sendMessage("Your lucky number is " + number + "/100!");
		}
		
		if (command.equalsIgnoreCase("roll")) {
			int number = random.nextInt(6) + 1;
			channel.sendMessage("You rolled a " + number);
		}
		
		if (command.equalsIgnoreCase("coin")) {
			int number = random.nextInt(2);
			if (number == 0) {
				channel.sendMessage("Heads\n" + "https://goo.gl/Pg5RQN");
			} else if (number == 1) {
				channel.sendMessage("Tails\n" + "https://goo.gl/wgHmwb");
			}
		}
		
		if (command.equalsIgnoreCase("version")) {
			channel.sendMessage("Version " + String.valueOf(random.nextInt(10)) + "." + String.valueOf(random.nextInt(10)) + "." + String.valueOf(random.nextInt(10)));
		}
		return;
	}
}