package io.github.lxgaming.discordbot.commands;

import java.util.Random;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class FunCommand {
	
	private static Random rand = new Random();
	
	public static void fun(TextChannel channel, String command, User author) {
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Number.Command"))) {
			MessageSender.sendCommand(channel, author, "Fun", "Number.Message", String.valueOf(rand.nextInt(100) + 1), "");
		}
		
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Roll.Command"))) {
			MessageSender.sendCommand(channel, author, "Fun", "Roll.Message", String.valueOf(rand.nextInt(6) + 1), "");
		}
		
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Coin.Command"))) {
			int number = rand.nextInt(2);
			if (number == 0) {
				MessageSender.sendCommand(channel, author, "Fun", "Coin.MessageHeads", "", "");
			} else if (number == 1) {
				MessageSender.sendCommand(channel, author, "Fun", "Coin.MessageTails", "", "");
			}
		}
		
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Fun.Version.Command"))) {
			MessageSender.sendCommand(channel, author, "Fun", "Version.Message", String.valueOf(rand.nextInt(10)) + "." + String.valueOf(rand.nextInt(10)) + "." + String.valueOf(rand.nextInt(10)), "");
		}
		return;
	}
}