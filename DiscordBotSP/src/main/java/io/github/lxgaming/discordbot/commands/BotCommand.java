package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class BotCommand {
	
	public static void bot(TextChannel CHANNEL, String COMMAND, User AUTHOR) {
		if (DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Custom." + COMMAND) != null) {
			if (!DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Custom." + COMMAND).equals("")) {
				CHANNEL.sendMessage(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Custom." + COMMAND));
			}
		}
		
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Bot.BotInfo"))) {
			CHANNEL.sendMessage("DiscordBot - Spigot Edition, Version " + DiscordBot.DBVERSION + ", Created by LX_Gaming\nAPI - " + DiscordBot.APIVERSION);
		}
		
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Bot.Website"))) {
			CHANNEL.sendMessage("*http://lxgaming.github.io/*");
		}
		return;
	}
}