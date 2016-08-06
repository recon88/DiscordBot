package io.github.lxgaming.discordbot.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.DatabaseManager;

public class DiscordBotCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("discordbot")) {
			return false;
		}
		
		if (args.length == 0) {
			sender.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "DiscordBot - Spigot Edition" + ChatColor.GOLD + " ===== ");
			sender.sendMessage(ChatColor.GOLD + "Version - " + ChatColor.AQUA + DiscordBot.dbVersion);
			sender.sendMessage(ChatColor.GOLD + "API - " + ChatColor.AQUA + DiscordBot.jdaVersion);
			sender.sendMessage(ChatColor.GOLD + "Author - " + ChatColor.AQUA + "LX_Gaming");
			sender.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Commands" + ChatColor.GOLD + " ===== ");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "/discordbot <toggle / t>");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "/discordchat + /dcc");
			sender.sendMessage(ChatColor.GOLD + "===== " + ChatColor.GREEN + "Permissions" + ChatColor.GOLD + " ===== ");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.Silent");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.CommandChat");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.GlobalChat");
			sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + "DiscordBot.ReceiveDiscordChat");
			return true;
		}
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Command cannot be run from Console!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (args.length == 1 && (args[0].equalsIgnoreCase("toggle") || args[0].equalsIgnoreCase("t"))) {
			DatabaseManager.addPlayerDatabase(player);
			return true;
		}
		sender.sendMessage(ChatColor.RED + "Usage: /DiscordBot <Toggle>");
		return true;
	}
}