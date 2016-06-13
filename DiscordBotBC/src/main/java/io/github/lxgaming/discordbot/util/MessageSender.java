package io.github.lxgaming.discordbot.util;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageSender {
	
	private static String LOCALE = DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale");
	private static String BOTTEXTCHANNEL = DiscordBot.CONFIG.getString("DiscordBot.TextChannels.Bot");
	private static Boolean SENDINGAME = DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.SendInGame");
	private static Boolean SENDDISCORD = DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.SendDiscord");
	private static Boolean SENDCONSOLE = DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public static void sendMessage(String MESSAGE, String USER, String USERNICK, String SERVER, String FORMAT, Boolean DISCORD, Boolean INGAME, Boolean CONSOLE) {
		if (USERNICK == null || USERNICK.equals("")) {
			USERNICK = USER;
		}
		if (DISCORD == true && SENDDISCORD == true) {
			if (!DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".DiscordFormat").equals("") || !DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".DiscordFormat").equals("null")) {
				sendMessageDiscord(MESSAGE, USER, USERNICK, SERVER, FORMAT);
			}
		}
		if (INGAME == true && SENDINGAME == true) {
			if (!DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".InGameFormat").equals("") || !DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".InGameFormat").equals("null")) {
				sendMessageInGame(MESSAGE, USER, USERNICK, SERVER, FORMAT);
			}
		}
		if (CONSOLE == true && SENDCONSOLE == true) {
			sendMessageConsole(MESSAGE, USER, SERVER);
		}
	}
	
	public static void sendCommand(TextChannel CHANNEL, User AUTHOR, String GROUP, String COMMAND, String NUMBER, String NAME) {
		CHANNEL.sendMessage(DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + ".Commands." + GROUP + "." + COMMAND).replaceAll("%sender%", AUTHOR.getUsername()).replaceAll("%number%", NUMBER).replaceAll("%name%", NAME));
	}
	
	private static void sendMessageDiscord(String MESSAGE, String USER, String USERNICK, String SERVER, String FORMAT) {
		try {
			DiscordBot.API.getTextChannelById(BOTTEXTCHANNEL).sendMessage(DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".DiscordFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", USER).replaceAll("%usernick%", USERNICK).replaceAll("%server%", SERVER).replaceAll("%message%", MESSAGE).replaceAll("§.", ""));
		} catch (Exception ex) {
			DiscordBot.INSTANCE.getLogger().severe("Unable to send message!");
		}
		return;
	}
	
	private static void sendMessageInGame(String MESSAGE, String USER, String USERNICK, String SERVER, String FORMAT) {
		for (ProxiedPlayer PLAYER : ProxyServer.getInstance().getPlayers()) {
			if (PLAYER.hasPermission("DiscordBot.ReceiveDiscordChat")) {
				PLAYER.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".InGameFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", USER).replaceAll("%usernick%", USERNICK).replaceAll("%server%", SERVER).replaceAll("%message%", MESSAGE).replaceAll("§", "&"))).create());
			}
		}
		return;
	}
	
	private static void sendMessageConsole(String MESSAGE, String USER, String SERVER) {
		DiscordBot.INSTANCE.getLogger().info("[" + SERVER + "] " + USER + ": " + MESSAGE);
		return;
	}
}