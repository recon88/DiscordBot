package io.github.lxgaming.discordbot.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Environment {
	
	public static String getPlayers() {
		String ONLINEPLAYERS = "";
		for (Player PLAYER : Bukkit.getOnlinePlayers()) {
			ONLINEPLAYERS = ONLINEPLAYERS + PLAYER.getName() + ", ";
		}
		return ONLINEPLAYERS.trim();
	}
	
	public static String getServerName() {
		return Bukkit.getServer().getServerName();
	}
	
	public static String getServerVersion() {
		return Bukkit.getServer().getBukkitVersion().substring(0, 5);
	}
}