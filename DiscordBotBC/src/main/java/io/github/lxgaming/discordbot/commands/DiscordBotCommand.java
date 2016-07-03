package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.DatabaseManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiscordBotCommand extends Command {
	
	public DiscordBotCommand() {
		super("discordbot");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(new ComponentBuilder("===== ").color(ChatColor.GOLD).append("DiscordBot - Bungeecord Edition").color(ChatColor.GREEN).append(" =====").color(ChatColor.GOLD).create());
			sender.sendMessage(new ComponentBuilder("Version - ").color(ChatColor.GOLD).append(DiscordBot.dbVersion).color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder("API - ").color(ChatColor.GOLD).append(DiscordBot.jdaVersion).color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder("Author - ").color(ChatColor.GOLD).append("LX_Gaming").color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder("===== ").color(ChatColor.GOLD).append("Commands").color(ChatColor.GREEN).append(" =====").color(ChatColor.GOLD).create());
			sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("/discordbot <toggle / t>").color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("/discordchat + /dcc").color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder("===== ").color(ChatColor.GOLD).append("Permissions").color(ChatColor.GREEN).append(" =====").color(ChatColor.GOLD).create());
			sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("DiscordBot.CommandChat").color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("DiscordBot.GlobalChat").color(ChatColor.AQUA).create());
			sender.sendMessage(new ComponentBuilder(" - ").color(ChatColor.GOLD).append("DiscordBot.ReceiveDiscordChat").color(ChatColor.AQUA).create());
			return;
		}
		
		if (!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new ComponentBuilder("Command cannot be run from Console").color(ChatColor.RED).create());
			return;
		}
		
		ProxiedPlayer player = (ProxiedPlayer) sender;
		
		if (args.length == 1 && (args[0].equalsIgnoreCase("toggle") || args[0].equalsIgnoreCase("t"))) {
			DatabaseManager.addPlayerDatabase(player);
		}
	}
}