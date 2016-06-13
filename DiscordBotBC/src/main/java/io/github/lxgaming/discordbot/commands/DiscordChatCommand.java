package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class DiscordChatCommand extends Command {
	
	public DiscordChatCommand() {
		super("discordchat", "DiscordBot.CommandChat", "dcc");
	}
	
	@Override
	public void execute(CommandSender SENDER, String[] ARGS) {
		String MESSAGE = "";
		for (String ARG : ARGS) {
			MESSAGE = MESSAGE + ARG + " ";
		}
		
		if (SENDER instanceof ProxiedPlayer) {
			ProxiedPlayer PLAYER = (ProxiedPlayer) SENDER;
			MessageSender.sendMessage(MESSAGE, PLAYER.getName(), PLAYER.getDisplayName(), PLAYER.getServer().getInfo().getName(), "Message", true, true, true);
		} else {
			MessageSender.sendMessage(MESSAGE, SENDER.getName(), SENDER.getName(), SENDER.getName(), "Message", true, true, true);
		}
		return;
	}
}