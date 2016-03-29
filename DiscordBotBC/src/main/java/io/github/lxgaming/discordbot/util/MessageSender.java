package io.github.lxgaming.discordbot.util;

import io.github.lxgaming.discordbot.DiscordBot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageSender {
	
	private String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	private String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	private Boolean sendInGame = DiscordBot.config.getBoolean("DiscordBot.Listeners.SendInGame");
	private Boolean sendDiscord = DiscordBot.config.getBoolean("DiscordBot.Listeners.SendDiscord");
	
	public void sendMessage(String message) {
		if (sendInGame == true) {
			for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
				if (player.hasPermission("DiscordBot.Chat")) {
					player.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', ingameFormat).replace("%author%:", "")).append(ChatColor.translateAlternateColorCodes('&', message)).create());
				}
			}
		}
		
		if (sendDiscord == true) {
			try {
				DiscordBot.api.getTextChannelById(botTextChannel).sendMessage(message);
			} catch (Exception ex) {
				DiscordBot.instance.getLogger().severe("Unable to send message!");
				DiscordBot.instance.getLogger().severe("Make sure 'DiscordBot.TextChannels.Bot' Is using an ID and not a name!");
				DiscordBot.instance.getLogger().info("List of available TextChannels " + DiscordBot.api.getTextChannels());
			}
		}
		return;
	}
}