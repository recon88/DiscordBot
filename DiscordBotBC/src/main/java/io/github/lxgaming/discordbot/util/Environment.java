package io.github.lxgaming.discordbot.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Environment {
	
	public static String getPlayers() {
		String onlinePlayers = "";
		for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
			onlinePlayers = onlinePlayers + player.getName() + ", ";
		}
		return onlinePlayers.trim();
	}
	
	public static String getServerName() {
		return ProxyServer.getInstance().getName();
	}
	
	public static String getServerVersion() {
		return ProxyServer.getInstance().getVersion().substring(25, 28);
	}
}