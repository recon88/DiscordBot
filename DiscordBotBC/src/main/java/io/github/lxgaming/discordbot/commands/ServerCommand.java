package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Environment;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class ServerCommand {
	
	public static void server(TextChannel channel, String command, User author) {
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Server.List.Command"))) {
			MessageSender.sendCommand(channel, author, "Server", "List.Message", "", Environment.getPlayers());
		}
		
		if (command.equalsIgnoreCase(DiscordBot.messages.getString("DiscordBot." + DiscordBot.config.getString("DiscordBot.Messages.Locale") + ".Commands.Server.ServerInfo.Command"))) {
			MessageSender.sendCommand(channel, author, "Server", "ServerInfo.Message", Environment.getServerVersion(), Environment.getServerName());
		}
	}
}