package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.DisconnectEvent;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.ReconnectedEvent;
import net.dv8tion.jda.events.ShutdownEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class BotListener extends ListenerAdapter {
	
	private static String guildID = DiscordBot.config.getString("DiscordBot.Credentials.Guild");
	private static String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	
	@Override
	public void onDisconnect(DisconnectEvent event) {
		if (DiscordBot.config.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
			MessageSender.sendMessage("DiscordBot Disconnected", "", "", DiscordBot.jda.getGuildById(guildID).getName(), "Disconnet", false, true, true);
		}
		return;
	}
	
	@Override
	public void onReady(ReadyEvent event) {
		if (botTextChannel.equals("") || botTextChannel.contains("[a-zA-Z]+") == true) {
			DiscordBot.instance.getLogger().severe("Please make sure you are using the Channel ID in the config");
			DiscordBot.instance.getLogger().info("List of available TextChannels " + event.getJDA().getTextChannels());
			return;
		}
		
		if (guildID.equals("") || guildID.contains("[a-zA-Z]+") == true) {
			DiscordBot.instance.getLogger().info("Setting Guild ID.");
			DiscordBot.config.set("DiscordBot.Credentials.Guild", DiscordBot.jda.getTextChannelById(botTextChannel).getGuild().getId());
			try {
				ConfigurationProvider.getProvider(YamlConfiguration.class).save(DiscordBot.config, DiscordBot.configFile);
			} catch (Exception ex) {
				DiscordBot.instance.getLogger().severe("Failed to save GuildID to Config!");
				return;
			}
		}
		
		if (DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot") == true) {
			DiscordBot.jda.addEventListener(new MessageListener());
			DiscordBot.jda.addEventListener(new UserListener());
			DiscordBot.jda.addEventListener(new VoiceListener());
			if (DiscordBot.config.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
				MessageSender.sendMessage("DiscordBot Connected", "", "", DiscordBot.jda.getGuildById(guildID).getName(), "Ready", true, true, true);
			}
		} else {
			if (DiscordBot.config.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
				MessageSender.sendMessage("DiscordBot Connected Not running as Main!", "", "", DiscordBot.jda.getGuildById(guildID).getName(), "Ready", true, true, true);
			}
		}
		return;
	}
	
	@Override
	public void onReconnect(ReconnectedEvent event) {
		if (DiscordBot.config.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
			MessageSender.sendMessage("DiscordBot Reconnected", "", "", DiscordBot.jda.getGuildById(guildID).getName(), "Reconnect", true, true, true);
		}
		return;
	}
	
	@Override
	public void onShutdown(ShutdownEvent event) {
		if (DiscordBot.config.getBoolean("DiscordBot.Messages.ConnectionMessage") == true) {
			MessageSender.sendMessage("DiscordBot Shutdown", "", "", DiscordBot.jda.getGuildById(guildID).getName(), "Shutdown", false, false, false);
		}
		return;
	}
}