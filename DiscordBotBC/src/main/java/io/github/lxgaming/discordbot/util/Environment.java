package io.github.lxgaming.discordbot.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Environment {
	
	public static String getPlayers() {
		String ONLINEPLAYERS = "";
		for (ProxiedPlayer PLAYER : ProxyServer.getInstance().getPlayers()) {
			ONLINEPLAYERS = ONLINEPLAYERS + PLAYER.getName() + ", ";
		}
		return ONLINEPLAYERS.trim();
	}
	
	public static String getServerName() {
		return ProxyServer.getInstance().getName();
	}
	
	public static String getServerVersion() {
		return ProxyServer.getInstance().getVersion().substring(25, 28);
	}
}