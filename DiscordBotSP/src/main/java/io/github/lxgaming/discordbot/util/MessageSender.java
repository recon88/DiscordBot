package io.github.lxgaming.discordbot.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class MessageSender {
	
	private static String LOCALE = DiscordBot.CONFIG.getString("DiscordBot.Messages.Locale");
	private static String BOTTEXTCHANNEL = DiscordBot.CONFIG.getString("DiscordBot.TextChannels.Bot");
	private static Boolean SENDINGAME = DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.SendInGame");
	private static Boolean SENDDISCORD = DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.SendDiscord");
	private static Boolean SENDCONSOLE = DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public static void sendMessage(String MESSAGE, String USER, String USERNICK, String FORMAT, Boolean DISCORD, Boolean INGAME, Boolean CONSOLE) {
		if (USERNICK == null || USERNICK.equals("")) {
			USERNICK = USER;
		}
		if (DISCORD == true && SENDDISCORD == true) {
			if (!DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".DiscordFormat").equals("") || !DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".DiscordFormat").equals("null")) {
				sendMessageDiscord(MESSAGE, USER, USERNICK, FORMAT);
			}
		}
		if (INGAME == true && SENDINGAME == true) {
			if (!DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".InGameFormat").equals("") || !DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".InGameFormat").equals("null")) {
				sendMessageInGame(MESSAGE, USER, USERNICK, FORMAT);
			}
		}
		if (CONSOLE == true && SENDCONSOLE == true) {
			sendMessageConsole(MESSAGE);
		}
	}
	
	public static void sendCommand(TextChannel CHANNEL, User AUTHOR, String GROUP, String COMMAND, String NUMBER, String NAME) {
		CHANNEL.sendMessage(DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + ".Commands." + GROUP + "." + COMMAND).replaceAll("%sender%", AUTHOR.getUsername()).replaceAll("%number%", NUMBER).replaceAll("%name%", NAME));
	}
	
	private static void sendMessageDiscord(String MESSAGE, String USER, String USERNICK, String FORMAT) {
		try {
			DiscordBot.API.getTextChannelById(BOTTEXTCHANNEL).sendMessage(DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".DiscordFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", USER).replaceAll("%usernick%", USERNICK).replaceAll("%message%", MESSAGE).replaceAll("§.", ""));
		} catch (Exception ex) {
			DiscordBot.INSTANCE.getLogger().severe("Unable to send message!");
		}
		return;
	}
	
	private static void sendMessageInGame(String MESSAGE, String USER, String USERNICK, String FORMAT) {
		for (Player PLAYER : Bukkit.getOnlinePlayers()) {
			if (PLAYER.hasPermission("DiscordBot.ReceiveDiscordChat")) {
				PLAYER.sendMessage(ChatColor.translateAlternateColorCodes('&', DiscordBot.MESSAGES.getString("DiscordBot." + LOCALE + "." + FORMAT + ".InGameFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", USER).replaceAll("%usernick%", USERNICK).replaceAll("%message%", MESSAGE).replaceAll("§", "&")));
			}
		}
		return;
	}
	
	private static void sendMessageConsole(String MESSAGE) {
		DiscordBot.INSTANCE.getLogger().info(MESSAGE);
		return;
	}
}