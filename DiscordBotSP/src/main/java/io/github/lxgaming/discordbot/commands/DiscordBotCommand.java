package io.github.lxgaming.discordbot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.lxgaming.discordbot.DiscordBot;

public class DiscordBotCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender SENDER, Command CMD, String LABEL, String[] ARGS) {
		if (CMD.getName().equalsIgnoreCase("discordbot")) {
			SENDER.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "DiscordBot - Spigot Edition" + ChatColor.GOLD + " ===== ");
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Version " + DiscordBot.DBVERSION);
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "API " + DiscordBot.APIVERSION);
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Author - LX_Gaming");
			SENDER.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Commands" + ChatColor.GOLD + " ===== ");
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "/discordbot");
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "/discordchat + /dcc");
			SENDER.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Permissions" + ChatColor.GOLD + " ===== ");
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.CommandChat");
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.GlobalChat");
			SENDER.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.ReceiveDiscordChat");
			return true;
		}
		return false;
	}
}