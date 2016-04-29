package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class LoveCommand {
	
	private static Random rand = new Random();
	
	public static void love(TextChannel channel, String command, User author) {
		
		String name = "";
		
		if (command.toLowerCase().startsWith(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Kiss.Command"))) {
			if (command.length() > 4) {
				name = command.substring(4);
				int number = rand.nextInt(2);
				if (number == 0) {
					MessageSender.sendCommand(channel, author, "Love", "Kiss.Message1", "", name);
				} else if (number == 1) {
					MessageSender.sendCommand(channel, author, "Love", "Kiss.Message2", "", name);
				}
			} else {
				MessageSender.sendCommand(channel, author, "Love", "Kiss.Invaild", "", name);
			}
		}
		
		if (command.toLowerCase().startsWith(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Hug.Command"))) {
			if (command.length() > 3) {
				name = command.substring(3);
				MessageSender.sendCommand(channel, author, "Love", "Hug.Message", "", name);
			} else {
				MessageSender.sendCommand(channel, author, "Love", "Hug.Invaild", "", name);
			}
		}
		
		if (command.toLowerCase().startsWith(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Slap.Command"))) {
			if (command.length() > 4) {
				name = command.substring(4);
				int number = rand.nextInt(2);
				if (number == 0) {
					MessageSender.sendCommand(channel, author, "Love", "Slap.Message1", "", name);
				} else if (number == 1) {
					name = command.substring(5);
					MessageSender.sendCommand(channel, author, "Love", "Slap.Message2", "", name);
				}
			} else {
				MessageSender.sendCommand(channel, author, "Love", "Slap.Invaild", "", name);
			}
		}
		
		if (command.toLowerCase().startsWith(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Lick.Command"))) {
			if (command.length() > 4) {
				name = command.substring(4);
				MessageSender.sendCommand(channel, author, "Love", "Lick.Message", "", name);
			} else {
				MessageSender.sendCommand(channel, author, "Love", "Lick.Invaild", "", name);
			}
		}
		return;
	}
}