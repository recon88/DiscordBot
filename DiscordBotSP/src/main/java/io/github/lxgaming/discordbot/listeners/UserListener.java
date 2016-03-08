package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	Boolean userNameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserNameUpdate");
	Boolean userGameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserGameUpdate");
	Boolean userOnlineStatusUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserOnlineStatusUpdate");
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		if (userNameUpdate == true) {
			UNU.getJDA().getTextChannelById(botTextChannel).sendMessage(UNU.getPreviousUsername() + " name has changed to " + UNU.getUser().getUsername());
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if ((userGameUpdate == true) && !(UGU.getUser().getCurrentGame() == null)) {
			UGU.getJDA().getTextChannelById(botTextChannel).sendMessage(UGU.getUser().getUsername() + " is now playing " + UGU.getUser().getCurrentGame());
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (userOnlineStatusUpdate == true) {
			if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				UOSU.getJDA().getTextChannelById(botTextChannel).sendMessage(UOSU.getUser().getUsername() + " is now Online.");
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
				UOSU.getJDA().getTextChannelById(botTextChannel).sendMessage(UOSU.getUser().getUsername() + " is now Away.");
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				UOSU.getJDA().getTextChannelById(botTextChannel).sendMessage(UOSU.getUser().getUsername() + " is now Offline.");
			}
		}
		return;
	}
}