package io.github.lxgaming.discordbot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import io.github.lxgaming.discordbot.DiscordBot;

public class DiscordBotCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("discordbot")) {
			sender.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "DiscordBot - Spigot Edition" + ChatColor.GOLD + " ===== ");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Version " + DiscordBot.dbversion);
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "API " + DiscordBot.apiversion);
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "Author - LX_Gaming");
			sender.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Commands" + ChatColor.GOLD + " ===== ");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "/discordbot");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "/discordchat + /dcc");
			sender.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Permissions" + ChatColor.GOLD + " ===== ");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.CommandChat");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.GlobalChat");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.ReceiveDiscordChat");
			return true;
		}
		return false;
	}
}