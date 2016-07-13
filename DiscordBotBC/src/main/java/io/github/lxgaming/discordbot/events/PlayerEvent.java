package io.github.lxgaming.discordbot.events;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.DatabaseManager;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerEvent implements Listener {
	
	private static boolean forceChat = DiscordBot.config.getBoolean("DiscordBot.Messages.ForceChat");
	private static boolean playerChat = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerChat");
	private static boolean playerJoin = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerJoin");
	private static boolean playerQuit = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerQuit");
	
	@EventHandler
	public void onPlayerChat(ChatEvent event) {
		ProxiedPlayer player = (ProxiedPlayer) event.getSender();
		
		if (event.isCommand()) {
			return;
		}
		
		if (event.isCancelled() == true && forceChat != true) {
			return;
		}
		
		if (playerChat == true && player.hasPermission("DiscordBot.GlobalChat") && !DatabaseManager.checkDatabase(player.getUniqueId().toString())) {
			MessageSender.sendMessage(event.getMessage(), player.getName(), player.getDisplayName(), player.getServer().getInfo().getName(), "Message", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onServerConnect(ServerConnectEvent event) {
		if (playerJoin == true && !event.getPlayer().hasPermission("DiscordBot.Silent")) {
			MessageSender.sendMessage("Joined", event.getPlayer().getName(), event.getPlayer().getDisplayName(), event.getTarget().getName(), "Player.Join", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onServerDisconnect(ServerDisconnectEvent event) {
		if (playerQuit == true && !event.getPlayer().hasPermission("DiscordBot.Silent")) {
			MessageSender.sendMessage("Quit", event.getPlayer().getName(), event.getPlayer().getDisplayName(), event.getPlayer().getServer().getInfo().getName(), "Player.Quit", true, false, false);
		}
		return;
	}
}