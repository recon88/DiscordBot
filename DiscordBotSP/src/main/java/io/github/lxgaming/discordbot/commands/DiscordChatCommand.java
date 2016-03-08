package io.github.lxgaming.discordbot.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;

public class DiscordChatCommand implements CommandExecutor {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public DiscordChatCommand(DiscordBot instance) {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if ((cmd.getName().equalsIgnoreCase("discordchat") || cmd.getName().equalsIgnoreCase("dcc"))) {
			
			String message = "";
			for (String arg : args) {
				message = message + arg + " ";
			}
			DiscordBot.api.getTextChannelById(botTextChannel).sendMessage(sender.getName() + ": " + message.trim());
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				if (onlinePlayer.hasPermission("DiscordBot.Chat")) {
					onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', ingameFormat).replace("%player%", sender.getName()) + ChatColor.translateAlternateColorCodes('&', " " + message.trim()));
				}
			}
			if (consoleOutput == true) {
				DiscordBot.instance.getLogger().info(sender.getName() + ": " + message.trim());
			}
			return true;
		}
		return false;
	}
}