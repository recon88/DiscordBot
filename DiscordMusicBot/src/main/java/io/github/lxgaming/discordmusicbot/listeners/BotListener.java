package io.github.lxgaming.discordmusicbot.listeners;

import io.github.lxgaming.discordmusicbot.util.ConsoleOutput;
import net.dv8tion.jda.events.DisconnectEvent;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.events.ReconnectedEvent;
import net.dv8tion.jda.events.ShutdownEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter {
	
	@Override
	public void onDisconnect(DisconnectEvent d) {
		ConsoleOutput.info("DiscordMusicBot Disconnected.");
		return;
	}
	
	@Override
	public void onReady(ReadyEvent r) {
		ConsoleOutput.info("DiscordMusicBot Ready.");
		return;
	}
	
	@Override
	public void onReconnect(ReconnectedEvent r) {
		ConsoleOutput.info("DiscordMusicBot Reconnected.");
		return;
	}
	
	@Override
	public void onShutdown(ShutdownEvent s) {
		ConsoleOutput.info("DiscordMusicBot Shutdown.");
		return;
	}
}