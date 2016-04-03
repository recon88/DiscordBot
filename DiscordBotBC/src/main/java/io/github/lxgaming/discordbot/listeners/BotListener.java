package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Date;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.DisconnectEvent;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.ReconnectedEvent;
import net.dv8tion.jda.events.ShutdownEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	private static String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	
	@Override
	public void onDisconnect(DisconnectEvent D) {
		DiscordBot.instance.getLogger().info("DiscordBot Disconnected!");
	}
	
	@Override
	public void onReady(ReadyEvent R) {
		if (botTextChannel.equals("") || botTextChannel.contains("[a-zA-Z]+") == true) {
			DiscordBot.instance.getLogger().severe("Please make sure you are using the Channel ID in the config");
			DiscordBot.instance.getLogger().info("List of available TextChannels " + R.getJDA().getTextChannels());
			return;
		}
		
		if (DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot") == true) {
			DiscordBot.api.addEventListener(new MessageListener());
			DiscordBot.api.addEventListener(new UserListener());
			DiscordBot.api.addEventListener(new VoiceListener());
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``DiscordBot`` **Connected!**");
			DiscordBot.instance.getLogger().info("DiscordBot Connected!");
		} else {
			DiscordBot.instance.getLogger().info("DiscordBot Connected!");
			DiscordBot.instance.getLogger().info("Running as secondary!");
		}
	}
	
	@Override
	public void onReconnect(ReconnectedEvent R) {
		DiscordBot.instance.getLogger().info("DiscordBot Reconnected!");
	}
	
	@Override
	public void onShutdown(ShutdownEvent S) {
		DiscordBot.instance.getLogger().info("DiscordBot Shutdown!");
	}
}