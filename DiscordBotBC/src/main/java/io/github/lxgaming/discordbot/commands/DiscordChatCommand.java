package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiscordChatCommand extends Command {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public DiscordChatCommand() {
		super("discordchat", "DiscordBot.Chat", "dcc");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		String message = "";
		for (String arg : args) {
			message = message + arg + " ";
		}
		DiscordBot.api.getTextChannelById(botTextChannel).sendMessage(sender.getName() + ": " + message.trim());
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			if (player.hasPermission("DiscordBot.Chat")) {
				player.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', ingameFormat).replace("%author%", player.getName())).append(ChatColor.translateAlternateColorCodes('&', " " + message.trim())).create());
			}
		}
		if (consoleOutput == true) {
			DiscordBot.instance.getLogger().info(sender.getName() + ": " + message.trim());
		}
		return;
	}
}