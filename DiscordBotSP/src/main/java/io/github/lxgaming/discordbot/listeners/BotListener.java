package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
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
		MessageSender.sendMessage("DiscordBot Disconnected", "", "Disconnet", false, true, true);
		return;
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
			MessageSender.sendMessage("DiscordBot Connected", "", "Ready", true, true, true);
		} else {
			MessageSender.sendMessage("DiscordBot Connected Not running as Main!", null, "Ready", true, true, true);
		}
		return;
	}
	
	@Override
	public void onReconnect(ReconnectedEvent R) {
		MessageSender.sendMessage("DiscordBot Reconnected", "", "Reconnect", true, true, true);
		return;
	}
	
	@Override
	public void onShutdown(ShutdownEvent S) {
		MessageSender.sendMessage("DiscordBot Shutdown", "", "Shutdown", false, false, false);
		return;
	}
}