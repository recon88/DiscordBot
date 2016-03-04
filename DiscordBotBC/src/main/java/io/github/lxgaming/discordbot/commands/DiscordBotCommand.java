package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class DiscordBotCommand extends Command {
	
	public DiscordBotCommand() {
		super("discordbot");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		sender.sendMessage(new ComponentBuilder("===== ").color(ChatColor.GOLD).append("DiscordBot").color(ChatColor.GREEN).append(" =====").color(ChatColor.GOLD).create());
		sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("Version " + DiscordBot.dbversion).color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("API - " + DiscordBot.apiversion).color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("Author - LX_Gaming").color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder("===== ").color(ChatColor.GOLD).append("Commands").color(ChatColor.GREEN).append(" =====").color(ChatColor.GOLD).create());
		sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("/discordbot").color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("/discordchat + /dcc").color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder("===== ").color(ChatColor.GOLD).append("Permissions").color(ChatColor.GREEN).append(" =====").color(ChatColor.GOLD).create());
		sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("DiscordBot.Chat").color(ChatColor.AQUA).create());
		return;
	}
}