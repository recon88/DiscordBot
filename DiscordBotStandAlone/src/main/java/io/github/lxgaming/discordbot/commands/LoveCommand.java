package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class LoveCommand {
	
	private static Random rand = new Random();
	
	public static void love(TextChannel channel, String command, User author) {
		
		String sender = author.getUsername();
		String name = "";
		
		if (command.toLowerCase().startsWith("kiss")) {
			if (command.length() > 4) {
				name = command.substring(4);
				int number = rand.nextInt(3);
				if (number == 0) {
					channel.sendMessage(sender + " kissed" + name);
				} else if (number == 1) {
					channel.sendMessage("Awww" + name + " ran away, no kiss for you.");
				} else if (number == 2) {
					channel.sendMessage("Oh god, get a room you two!");
				}
			} else {
				channel.sendMessage("Got nobody to kiss?");
			}
		}
		
		if (command.toLowerCase().startsWith("hug")) {
			if (command.length() > 3) {
				name = command.substring(3);
				channel.sendMessage(sender + " hugged" + name);
			} else {
				channel.sendMessage("Got nobody to hug?");
			}
		}
		
		if (command.toLowerCase().startsWith("slap")) {
			if (command.length() > 4) {
				name = command.substring(4);
				int number = rand.nextInt(2);
				if (number == 0) {
					channel.sendMessage(sender + " slapped" + name);
				} else if (number == 1) {
					name = command.substring(5);
					channel.sendMessage(name + " got RKO'D outta nowhere!");
				}
			} else {
				channel.sendMessage("Got nobody to slap?");
			}
		}
		
		if (command.toLowerCase().startsWith("lick")) {
			if (command.length() > 4) {
				name = command.substring(4);
				channel.sendMessage(sender + " licked" + name);
			} else {
				channel.sendMessage("Got nothing to lick?");
			}
		}
		return;
	}
}