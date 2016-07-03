package io.github.lxgaming.discordbot.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Environment {
	
	public static String getPlayers() {
		String onlinePlayers = "";
		for (Player player : Bukkit.getOnlinePlayers()) {
			onlinePlayers = onlinePlayers + player.getName() + ", ";
		}
		return onlinePlayers.trim();
	}
	
	public static String getServerName() {
		return Bukkit.getServer().getServerName();
	}
	
	public static String getServerVersion() {
		return Bukkit.getServer().getBukkitVersion().substring(0, 5);
	}
}