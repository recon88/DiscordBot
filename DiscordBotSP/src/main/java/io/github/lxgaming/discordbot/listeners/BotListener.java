package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.DisconnectEvent;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.ReconnectedEvent;
import net.dv8tion.jda.events.ShutdownEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	private static String GUILDID = DiscordBot.CONFIG.getString("DiscordBot.Credentials.Guild");
	private static String BOTTEXTCHANNEL = DiscordBot.CONFIG.getString("DiscordBot.TextChannels.Bot");
	
	@Override
	public void onDisconnect(DisconnectEvent D) {
		if (DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
			MessageSender.sendMessage("DiscordBot Disconnected", "", "", DiscordBot.API.getGuildById(GUILDID).getName(), "Disconnet", false, true, true);
		}
		return;
	}
	
	@Override
	public void onReady(ReadyEvent R) {
		if (BOTTEXTCHANNEL.equals("") || BOTTEXTCHANNEL.contains("[a-zA-Z]+") == true) {
			DiscordBot.INSTANCE.getLogger().severe("Please make sure you are using the Channel ID in the config");
			DiscordBot.INSTANCE.getLogger().info("List of available TextChannels " + R.getJDA().getTextChannels());
			return;
		}
		
		if (GUILDID.equals("") || GUILDID.contains("[a-zA-Z]+") == true) {
			DiscordBot.INSTANCE.getLogger().info("Setting Guild ID.");
			DiscordBot.CONFIG.set("DiscordBot.Credentials.Guild", DiscordBot.API.getTextChannelById(BOTTEXTCHANNEL).getGuild().getId());
			try {
				DiscordBot.CONFIG.save(DiscordBot.CONFIGFILE);
			} catch (Exception ex) {
				DiscordBot.INSTANCE.getLogger().severe("Failed to save GuildID to Config!");
				return;
			}
		}
		
		if (DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.MainBot") == true) {
			DiscordBot.API.addEventListener(new MessageListener());
			DiscordBot.API.addEventListener(new UserListener());
			DiscordBot.API.addEventListener(new VoiceListener());
			if (DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
				MessageSender.sendMessage("DiscordBot Connected", "", "", DiscordBot.API.getGuildById(GUILDID).getName(), "Ready", true, true, true);
			}	
		} else {
			if (DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
				MessageSender.sendMessage("DiscordBot Connected Not running as Main!", "", "", DiscordBot.API.getGuildById(GUILDID).getName(), "Ready", true, true, true);
			}
		}
		return;
	}
	
	@Override
	public void onReconnect(ReconnectedEvent R) {
		if (DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
			MessageSender.sendMessage("DiscordBot Reconnected", "", "", DiscordBot.API.getGuildById(GUILDID).getName(), "Reconnect", true, true, true);
		}
		return;
	}
	
	@Override
	public void onShutdown(ShutdownEvent S) {
		if (DiscordBot.CONFIG.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
			MessageSender.sendMessage("DiscordBot Shutdown", "", "", DiscordBot.API.getGuildById(GUILDID).getName(), "Shutdown", false, false, false);
		}
		return;
	}
}