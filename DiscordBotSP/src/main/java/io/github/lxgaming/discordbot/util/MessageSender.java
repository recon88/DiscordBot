package io.github.lxgaming.discordbot.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;

public class MessageSender {
	
	private String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	private String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	private Boolean sendInGame = DiscordBot.config.getBoolean("DiscordBot.Listeners.SendInGame");
	private Boolean sendDiscord = DiscordBot.config.getBoolean("DiscordBot.Listeners.SendDiscord");
	
	public void sendMessage(String message) {
		if (sendInGame == true) {
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				if (onlinePlayer.hasPermission("DiscordBot.Chat")) {
					onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', ingameFormat).replace("%author%", "") + ChatColor.translateAlternateColorCodes('&', message));
				}
			}
		}
		
		if (sendDiscord == true) {
			try {
				DiscordBot.api.getTextChannelById(botTextChannel).sendMessage(message);
			} catch (Exception ex) {
				DiscordBot.instance.getLogger().severe("Unable to send message!");
				DiscordBot.instance.getLogger().severe("Make sure 'DiscordBot.TextChannels.Bot' is using an ID and not a name!");
				DiscordBot.instance.getLogger().info("List of available TextChannels " + DiscordBot.api.getTextChannels());
			}
		}
		return;
	}
}