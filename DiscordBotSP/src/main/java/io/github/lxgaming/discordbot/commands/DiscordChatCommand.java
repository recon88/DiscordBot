package io.github.lxgaming.discordbot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.ChatColor;

public class DiscordChatCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender SENDER, Command CMD, String LABEL, String[] ARGS) {
		if (CMD.getName().equalsIgnoreCase("discordchat") || CMD.getName().equalsIgnoreCase("dcc")) {
			if (SENDER.hasPermission("DiscordBot.CommandChat")) {
				String MESSAGE = "";
				for (String ARG : ARGS) {
					MESSAGE = MESSAGE + ARG + " ";
				}
				
				if (SENDER instanceof Player) {
					Player PLAYER = (Player) SENDER;
					MessageSender.sendMessage(MESSAGE, PLAYER.getName(), PLAYER.getDisplayName(), "Message", true, true, true);
				} else {
					MessageSender.sendMessage(MESSAGE, SENDER.getName(), SENDER.getName(), "Message", true, true, true);
				}
				return true;
			}
			SENDER.sendMessage(ChatColor.RED + "You do not have permission!");
			return true;
		}
		return false;
	}
}