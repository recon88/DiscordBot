package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class FunCommand {
	
	private static Random RANDOM = new Random();
	
	public static void fun(TextChannel CHANNEL, String COMMAND, User AUTHOR) {
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Number.Command"))) {
			MessageSender.sendCommand(CHANNEL, AUTHOR, "Fun", "Number.Message", String.valueOf(RANDOM.nextInt(100) + 1), "");
		}
		
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Roll.Command"))) {
			MessageSender.sendCommand(CHANNEL, AUTHOR, "Fun", "Roll.Message", String.valueOf(RANDOM.nextInt(6) + 1), "");
		}
		
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Coin.Command"))) {
			int NUMBER = RANDOM.nextInt(2);
			if (NUMBER == 0) {
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Fun", "Coin.MessageHeads", "", "");
			} else if (NUMBER == 1) {
				MessageSender.sendCommand(CHANNEL, AUTHOR, "Fun", "Coin.MessageTails", "", "");
			}
		}
		
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Version.Command"))) {
			MessageSender.sendCommand(CHANNEL, AUTHOR, "Fun", "Version.Message", String.valueOf(RANDOM.nextInt(10)) + "." + String.valueOf(RANDOM.nextInt(10)) + "." + String.valueOf(RANDOM.nextInt(10)), "");
		}
		return;
	}
}