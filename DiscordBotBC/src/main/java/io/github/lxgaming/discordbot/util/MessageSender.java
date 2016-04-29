package io.github.lxgaming.discordbot.util;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageSender {
	
	private static String locale = DiscordBot.config.getString("DiscordBot.Messages.Locale");
	private static String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	private static Boolean sendInGame = DiscordBot.config.getBoolean("DiscordBot.Messages.SendInGame");
	private static Boolean sendDiscord = DiscordBot.config.getBoolean("DiscordBot.Messages.SendDiscord");
	private static Boolean sendConsole = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public static void sendMessage(String message, String user, String format, Boolean discord, Boolean ingame, Boolean console) {
		if (discord == true && sendDiscord == true) {
			if (!DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".DiscordFormat").equals("") || !DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".DiscordFormat").equals("null")) {
				sendMessageDiscord(message, user, format);
			}
		}
		if (ingame == true && sendInGame == true) {
			if (!DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".InGameFormat").equals("") || !DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".InGameFormat").equals("null")) {
				sendMessageInGame(message, user, format);
			}
		}
		if (console == true && sendConsole == true) {
			sendMessageConsole(message);
		}
	}
	
	public static void sendCommand(TextChannel channel, User author, String group, String command, String number, String name) {
		channel.sendMessage(DiscordBot.messages.getString("DiscordBot." + locale + ".Commands." + group + "." + command).replaceAll("%sender%", author.getUsername()).replaceAll("%number%", number).replaceAll("%name%", name));
	}
	
	private static void sendMessageDiscord(String message, String user, String format) {
		try {
			DiscordBot.api.getTextChannelById(botTextChannel).sendMessage(DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".DiscordFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", user).replaceAll("%message%", message));
		} catch (Exception ex) {
			DiscordBot.instance.getLogger().severe("Unable to send message!");
		}
		return;
	}
	
	private static void sendMessageInGame(String message, String user, String format) {
		for (ProxiedPlayer onlinePlayer : ProxyServer.getInstance().getPlayers()) {
			if (onlinePlayer.hasPermission("DiscordBot.ReceiveDiscordChat")) {
				onlinePlayer.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".InGameFormat").replaceAll("%time%", Date.getTime()).replace("%user%", user).replaceAll("%message%", message))).create());
			}
		}
		return;
	}
	
	private static void sendMessageConsole(String message) {
		DiscordBot.instance.getLogger().info(message);
		return;
	}
}