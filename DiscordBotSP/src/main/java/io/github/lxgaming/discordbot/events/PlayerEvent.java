package io.github.lxgaming.discordbot.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.DatabaseManager;
import io.github.lxgaming.discordbot.util.MessageSender;

public class PlayerEvent implements Listener {
	
	private static boolean forceChat = DiscordBot.config.getBoolean("DiscordBot.Messages.ForceChat");
	private static boolean playerChat = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerChat");
	private static boolean playerJoin = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerJoin");
	private static boolean playerQuit = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerQuit");
	private static boolean playerDeath = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerDeath");
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (event.isCancelled() == true && forceChat != true) {
			return;
		}
		
		if (playerChat == true && event.getPlayer().hasPermission("DiscordBot.GlobalChat") && !DatabaseManager.checkDatabase(event.getPlayer().getUniqueId().toString())) {
			MessageSender.sendMessage(event.getMessage(), event.getPlayer().getName(), event.getPlayer().getDisplayName(), event.getPlayer().getServer().getServerName(), "Message", true, false, false);
		}
		return;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (playerJoin == true && !event.getPlayer().hasPermission("DiscordBot.Silent")) {
			MessageSender.sendMessage("Joined", event.getPlayer().getName(), event.getPlayer().getDisplayName(), event.getPlayer().getServer().getServerName(), "Player.Join", true, false, false);
		}
		return;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (playerQuit == true && !event.getPlayer().hasPermission("DiscordBot.Silent")) {
			MessageSender.sendMessage("Quit", event.getPlayer().getName(), event.getPlayer().getDisplayName(), event.getPlayer().getServer().getServerName(), "Player.Quit", true, false, false);
		}
		return;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (playerDeath == true && !event.getEntity().hasPermission("DiscordBot.Silent")) {
			MessageSender.sendMessage(event.getDeathMessage(), event.getEntity().getName(), event.getEntity().getDisplayName(), event.getEntity().getServer().getServerName(), "Player.Death", true, false, false);
		}
		return;
	}
}