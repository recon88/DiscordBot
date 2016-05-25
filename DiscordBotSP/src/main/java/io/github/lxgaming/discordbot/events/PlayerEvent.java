package io.github.lxgaming.discordbot.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;

public class PlayerEvent implements Listener {
	
	private static Boolean playerChat = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerChat");
	private static Boolean playerJoin = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerJoin");
	private static Boolean playerQuit = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerQuit");
	private static Boolean playerDeath = DiscordBot.config.getBoolean("DiscordBot.Events.PlayerDeath");
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent APC) {
		
		if (APC.isCancelled()) {
			return;
		}
		
		if (playerChat == true && APC.getPlayer().hasPermission("DiscordBot.GlobalChat")) {
			MessageSender.sendMessage(APC.getMessage(), APC.getPlayer().getName(), APC.getPlayer().getDisplayName(), "Message", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent PJ) {
		if (playerJoin == true) {
			MessageSender.sendMessage("Joined", PJ.getPlayer().getName(), PJ.getPlayer().getDisplayName(), "Player.Join", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent PQ) {
		if (playerQuit == true) {
			MessageSender.sendMessage("Quit", PQ.getPlayer().getName(), PQ.getPlayer().getDisplayName(), "Player.Quit", true, false, false);
		}
		return;
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent PD) {
		if (playerDeath == true) {
			MessageSender.sendMessage(PD.getDeathMessage(), PD.getEntity().getName(), PD.getEntity().getDisplayName(), "Player.Death", true, false, false);
		}
		return;
	}
}