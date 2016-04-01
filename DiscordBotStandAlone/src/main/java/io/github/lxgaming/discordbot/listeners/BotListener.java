package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Date;
import net.dv8tion.jda.events.DisconnectEvent;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.ReconnectedEvent;
import net.dv8tion.jda.events.ShutdownEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	private String botTC = DiscordBot.config.props.getProperty("BotChannel");
	
	@Override
	public void onDisconnect(DisconnectEvent D) {
		System.out.println("DiscordBot Disconnected!");
	}
	
	@Override
	public void onReady(ReadyEvent R) {
		if (botTC.equals("") || botTC.contains("[a-zA-Z]+") == true) {
			System.out.println("Please make sure you are using the Channel ID in the config");
			System.out.println("List of available TextChannels " + R.getJDA().getTextChannels());
			System.exit(0);
		}
		R.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``DiscordBot`` **Connected!**");
		System.out.println("DiscordBot Connected!");
	}
	
	@Override
	public void onReconnect(ReconnectedEvent R) {
		System.out.println("DiscordBot Reconnected!");
	}
	
	@Override
	public void onShutdown(ShutdownEvent S) {
		System.out.println("DiscordBot Shutdown!");
	}
}