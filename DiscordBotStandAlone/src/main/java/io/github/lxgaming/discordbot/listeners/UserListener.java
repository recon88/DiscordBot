package io.github.lxgaming.discordbot.listeners;

import org.json.JSONObject;

import io.github.lxgaming.discordbot.ExampleUtils;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	JSONObject config = ExampleUtils.getConfig();
	public String textChannelID = config.getString("textchannelid");
	
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		UNU.getJDA().getTextChannelById(textChannelID).sendMessage(UNU.getPreviousUsername() + " name has changed to " + UNU.getUser().getUsername());
	}
	
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if (!(UGU.getUser().getCurrentGame() == null)) {
			UGU.getJDA().getTextChannelById(textChannelID).sendMessage(UGU.getUser().getUsername() + " is now playing " + UGU.getUser().getCurrentGame());
		}
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
			UOSU.getJDA().getTextChannelById(textChannelID).sendMessage(UOSU.getUser().getUsername() + " is now Online");
		}
		
		if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
			UOSU.getJDA().getTextChannelById(textChannelID).sendMessage(UOSU.getUser().getUsername() + " is now Away");
		}
		
		if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
			UOSU.getJDA().getTextChannelById(textChannelID).sendMessage(UOSU.getUser().getUsername() + " is now Offline");
		}
		return;
	}
}
