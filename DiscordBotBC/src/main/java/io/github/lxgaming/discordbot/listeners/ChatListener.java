package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {
	
	private static String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	private static Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	@EventHandler
	public void onPlayerChat(ChatEvent C) {
		if (!C.isCommand() && !C.isCancelled()) {
			ProxiedPlayer player = (ProxiedPlayer) C.getSender();
			String message = C.getMessage();
			try {
				DiscordBot.api.getTextChannelById(botTextChannel).sendMessage(player.getName() + ": " + message);
			} catch (Exception ex) {
				DiscordBot.instance.getLogger().severe("Unable to send message!");
				DiscordBot.instance.getLogger().severe("Make sure 'DiscordBot.TextChannels.Bot' Is using an ID and not a name!");
			}
			
			if (consoleOutput == true) {
				DiscordBot.instance.getLogger().info(player.getName() + ": " + message);
			}
		}
		return;
	}
}