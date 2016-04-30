package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class BotCommand {
	
	public static void bot(TextChannel channel, String command, User author) {
		if (DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Custom." + command) != null) {
			channel.sendMessage(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Custom." + command));
		}
		
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Bot.BotInfo"))) {
			channel.sendMessage("DiscordBot - Spigot Edition, Version " + DiscordBot.dbversion + ", Created by LX_Gaming\nAPI - " + DiscordBot.apiversion);
		}
		
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Bot.Website"))) {
			channel.sendMessage("*http://lxgaming.github.io/*");
		}
		return;
	}
}