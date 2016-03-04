package io.github.lxgaming.discordbot.listeners;

import org.json.JSONObject;

import io.github.lxgaming.discordbot.ExampleUtils;
import net.dv8tion.jda.events.Event;
import net.dv8tion.jda.events.ReadyEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class ReadyListener extends ListenerAdapter {
	
	JSONObject config = ExampleUtils.getConfig();
	public String textChannelID = config.getString("textchannelid");

	public void onEvent(Event event) {
		if (event instanceof ReadyEvent) {
			event.getJDA().getTextChannelById(textChannelID).sendMessage("Discord Bot has Started!");
			System.out.println("Discord Bot has Started!");
		}
	}
}