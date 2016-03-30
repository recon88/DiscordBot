package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	public String botTC = DiscordBot.config.props.getProperty("BotChannel");
	
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		UNU.getJDA().getTextChannelById(botTC).sendMessage(UNU.getPreviousUsername() + " name has changed to " + UNU.getUser().getUsername());
	}
	
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if (!(UGU.getUser().getCurrentGame() == null)) {
			UGU.getJDA().getTextChannelById(botTC).sendMessage(UGU.getUser().getUsername() + " is now playing " + UGU.getUser().getCurrentGame());
		}
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
			UOSU.getJDA().getTextChannelById(botTC).sendMessage(UOSU.getUser().getUsername() + " is now Online");
		}
		
		if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
			UOSU.getJDA().getTextChannelById(botTC).sendMessage(UOSU.getUser().getUsername() + " is now Away");
		}
		
		if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
			UOSU.getJDA().getTextChannelById(botTC).sendMessage(UOSU.getUser().getUsername() + " is now Offline");
		}
		return;
	}
}
