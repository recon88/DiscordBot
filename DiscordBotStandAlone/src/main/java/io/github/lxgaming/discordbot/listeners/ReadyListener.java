package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.events.Event;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
	
	public String botTC = DiscordBot.config.props.getProperty("BotChannel");

	public void onEvent(Event event) {
		if (event instanceof ReadyEvent) {
			if (botTC.equals("") || botTC.contains("[a-zA-Z]+") == true) {
				System.out.println("Please make sure you are using the Channel ID in the config");
				System.out.println("List of available TextChannels " + event.getJDA().getTextChannels());
				System.exit(0);
			}
			event.getJDA().getTextChannelById(botTC).sendMessage("Discord Bot has Started!");
			System.out.println("Discord Bot has Started!");
		}
		return;
	}
}