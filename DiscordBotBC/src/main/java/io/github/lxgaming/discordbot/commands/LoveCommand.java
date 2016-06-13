package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class LoveCommand {
	
	private static Random RANDOM = new Random();
	
	public static void love(TextChannel CHANNEL, String COMMAND, User AUTHOR) {
		
		String NAME = "";
		
		if (COMMAND.toLowerCase().startsWith(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Kiss.Command"))) {
			if (COMMAND.length() > 4) {
				NAME = COMMAND.substring(4);
				int NUMBER = RANDOM.nextInt(2);
				if (NUMBER == 0) {
					MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Kiss.Message1", "", NAME);
				} else if (NUMBER == 1) {
					MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Kiss.Message2", "", NAME);
				}
			} else {
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Kiss.Invaild", "", NAME);
			}
		}
		
		if (COMMAND.toLowerCase().startsWith(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Hug.Command"))) {
			if (COMMAND.length() > 3) {
				NAME = COMMAND.substring(3);
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Hug.Message", "", NAME);
			} else {
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Hug.Invaild", "", NAME);
			}
		}
		
		if (COMMAND.toLowerCase().startsWith(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Slap.Command"))) {
			if (COMMAND.length() > 4) {
				NAME = COMMAND.substring(4);
				int NUMBER = RANDOM.nextInt(2);
				if (NUMBER == 0) {
					MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Slap.Message1", "", NAME);
				} else if (NUMBER == 1) {
					NAME = COMMAND.substring(5);
					MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Slap.Message2", "", NAME);
				}
			} else {
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Slap.Invaild", "", NAME);
			}
		}
		
		if (COMMAND.toLowerCase().startsWith(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Love.Lick.Command"))) {
			if (COMMAND.length() > 4) {
				NAME = COMMAND.substring(4);
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Lick.Message", "", NAME);
			} else {
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Love", "Lick.Invaild", "", NAME);
			}
		}
		return;
	}
}