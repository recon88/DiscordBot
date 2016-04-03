package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Date;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	private static Boolean userAvatarUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserAvatarUpdate");
	private static Boolean userGameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserGameUpdate");
	private static Boolean userNameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserNameUpdate");
	private static Boolean userOnlineStatusUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserOnlineStatusUpdate");
	
	@Override
	public void onUserAvatarUpdate(UserAvatarUpdateEvent UAU) {
		if (userAvatarUpdate == true) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UAU.getUser().getUsername() + "** ``New Avatar:`` **" + UAU.getUser().getAvatarId() + "**");
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if ((userGameUpdate == true) && !(UGU.getUser().getCurrentGame() == null)) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UGU.getUser().getUsername() + "** ``Game:`` **" + UGU.getUser().getCurrentGame() + "**");
		}
		return;
	}
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		if (userNameUpdate == true) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UNU.getPreviousUsername() + "** ``New name:`` **" + UNU.getUser().getUsername() + "**");
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (userOnlineStatusUpdate == true) {
			if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UOSU.getUser().getUsername() + "** ``Status:`` **Online**");
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UOSU.getUser().getUsername() + "** ``Status:`` **Away**");
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UOSU.getUser().getUsername() + "** ``Status:`` **Offline**");
			}
		}
		return;
	}
}