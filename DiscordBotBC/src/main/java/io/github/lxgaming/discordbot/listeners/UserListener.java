package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	private static Boolean userAvatarUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserAvatarUpdate");
	private static Boolean userNameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserNameUpdate");
	private static Boolean userGameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserGameUpdate");
	private static Boolean userOnlineStatusUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserOnlineStatusUpdate");
	
	@Override
	public void onUserAvatarUpdate(UserAvatarUpdateEvent UAU) {
		if (userAvatarUpdate == true) {
			MessageSender.sendMessage(UAU.getUser().getAvatarId(), UAU.getUser().getUsername(), UAU.getUser().getId(), "UserAvatarUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if ((userGameUpdate == true) && !(UGU.getUser().getCurrentGame() == null)) {
			MessageSender.sendMessage(UGU.getUser().getCurrentGame(), UGU.getUser().getUsername(), UGU.getUser().getId(), "UserGameUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		if (userNameUpdate == true) {
			MessageSender.sendMessage(UNU.getUser().getUsername(), UNU.getPreviousUsername(), UNU.getUser().getId(), "UserNameUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (userOnlineStatusUpdate == true) {
			if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				MessageSender.sendMessage("Online", UOSU.getUser().getUsername(), UOSU.getUser().getId(), "UserOnlineStatusUpdate.Online", true, true, false);
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
				MessageSender.sendMessage("Away", UOSU.getUser().getUsername(), UOSU.getUser().getId(), "UserOnlineStatusUpdate.Away", true, true, false);
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				MessageSender.sendMessage("Offline", UOSU.getUser().getUsername(), UOSU.getUser().getId(), "UserOnlineStatusUpdate.Offline", true, true, false);
			}
		}
		return;
	}
}