package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Environment;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class ServerCommand {
	
	public static void server(TextChannel CHANNEL, String COMMAND, User AUTHOR) {
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Server.List.Command"))) {
			MessageSender.sendCommand(CHANNEL, AUTHOR, "Server", "List.Message", "", Environment.getPlayers());
		}
		
		if (COMMAND.equalsIgnoreCase(DiscordBot.MESSAGES.getString("DiscordBot." + DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale") + ".Commands.Server.ServerInfo.Command"))) {
			MessageSender.sendCommand(CHANNEL, AUTHOR, "Server", "ServerInfo.Message", Environment.getServerVersion(), Environment.getServerName());
		}
	}
}