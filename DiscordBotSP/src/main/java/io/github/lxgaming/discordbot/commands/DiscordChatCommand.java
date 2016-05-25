package io.github.lxgaming.discordbot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.ChatColor;

public class DiscordChatCommand implements CommandExecutor {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("discordchat") || cmd.getName().equalsIgnoreCase("dcc")) {
			if (sender.hasPermission("DiscordBot.CommandChat")) {
				String message = "";
				for (String arg : args) {
					message = message + arg + " ";
				}
				
				if (sender instanceof Player) {
					Player player = (Player) sender;
					MessageSender.sendMessage(message, player.getName(), player.getDisplayName(), "Message", true, true, true);
				} else {
					MessageSender.sendMessage(message, sender.getName(), sender.getName(), "Message", true, true, true);
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You do not have permission!");
			return true;
		}
		return false;
	}
}