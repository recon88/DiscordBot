package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.util.DatabaseManager;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiscordChatCommand extends Command {
	
	public DiscordChatCommand() {
		super("discordchat", "DiscordBot.CommandChat", "dcc");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		String message = "";
		for (String arg : args) {
			message = message + arg + " ";
		}
		
		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) sender;
			if (DatabaseManager.checkDatabase(player.getUniqueId().toString())) {
				player.sendMessage(new ComponentBuilder("DiscordChat disabled. '/DiscordBot Toggle' to enable").color(ChatColor.RED).create());
				return;
			}
			MessageSender.sendMessage(message, player.getName(), player.getDisplayName(), player.getServer().getInfo().getName(), "Message", true, true, true);
		} else {
			MessageSender.sendMessage(message, sender.getName(), sender.getName(), sender.getName(), "Message", true, true, true);
		}
		return;
	}
}