package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.Event;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	MessageSender ms = new MessageSender();
	
	public void onEvent(Event event) {
		if (event instanceof ReadyEvent) {
			if (botTextChannel.equals("")) {
				DiscordBot.instance.getLogger().severe("BotTextChannelID is null in the config!");
				DiscordBot.instance.getLogger().info("List of available TextChannels " + event.getJDA().getTextChannels());
				return;
			}
			if (DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot") == true) {
				DiscordBot.api.addEventListener(new MessageListener());
				DiscordBot.api.addEventListener(new UserListener());
				DiscordBot.api.addEventListener(new VoiceListener());
				ms.sendMessage("DiscordBot Connected!");
				DiscordBot.instance.getLogger().info("DiscordBot Connected!");
			} else {
				DiscordBot.instance.getLogger().info("DiscordBot Connected!");
				DiscordBot.instance.getLogger().info("Running as secondary!");
			}
		}
		return;
	}
}