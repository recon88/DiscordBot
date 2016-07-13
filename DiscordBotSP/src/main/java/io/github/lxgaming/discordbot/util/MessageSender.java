package io.github.lxgaming.discordbot.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class MessageSender {
	
	private static String locale = DiscordBot.config.getString("DiscordBot.Messages.Locale");
	private static String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	private static boolean sendInGame = DiscordBot.config.getBoolean("DiscordBot.Messages.SendInGame");
	private static boolean sendDiscord = DiscordBot.config.getBoolean("DiscordBot.Messages.SendDiscord");
	private static boolean sendConsole = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	
	public static void sendMessage(String message, String user, String userNick, String server, String format, boolean discord, boolean inGame, boolean console) {
		DiscordBot.instance.getServer().getScheduler().runTaskAsynchronously(DiscordBot.instance, new Runnable() {
			@Override
			public void run() {
				String userNickname = userNick;
				if (userNickname == null || userNickname.equals("")) {
					userNickname = user;
				}
				if (discord == true && sendDiscord == true) {
					if (!DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".DiscordFormat").equals("") || !DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".DiscordFormat").equals("null")) {
						sendMessageDiscord(message, user, userNickname, server, format);
					}
				}
				if (inGame == true && sendInGame == true) {
					if (!DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".InGameFormat").equals("") || !DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".InGameFormat").equals("null")) {
						sendMessageInGame(message, user, userNickname, server, format);
					}
				}
				if (console == true && sendConsole == true) {
					sendMessageConsole(message, user, server);
				}
			}
		});
	}
	
	public static void sendCommand(TextChannel channel, User author, String group, String command, String number, String name) {
		channel.sendMessage(DiscordBot.messages.getString("DiscordBot." + locale + ".Commands." + group + "." + command).replaceAll("%sender%", author.getUsername()).replaceAll("%number%", number).replaceAll("%name%", name));
	}
	
	private static void sendMessageDiscord(String message, String user, String userNick, String server, String format) {
		try {
			DiscordBot.jda.getTextChannelById(botTextChannel).sendMessage(DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".DiscordFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", user).replaceAll("%usernick%", userNick).replaceAll("%server%", server).replaceAll("%message%", message).replaceAll("§.", ""));
		} catch (Exception ex) {
			DiscordBot.instance.getLogger().severe("Unable to send message!");
		}
		return;
	}
	
	private static void sendMessageInGame(String message, String user, String userNick, String server, String format) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.hasPermission("DiscordBot.ReceiveDiscordChat") && !DatabaseManager.checkDatabase(player.getUniqueId().toString())) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', DiscordBot.messages.getString("DiscordBot." + locale + "." + format + ".InGameFormat").replaceAll("%time%", Date.getTime()).replaceAll("%user%", user).replaceAll("%usernick%", userNick).replaceAll("%server%", server).replaceAll("%message%", message).replaceAll("§", "&")));
			}
		}
		return;
	}
	
	private static void sendMessageConsole(String message, String user, String server) {
		DiscordBot.instance.getLogger().info("[" + server + "] " + user + ": " + message);
		return;
	}
}