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
	
	private String userAvatarUpdate = DiscordBot.config.getString("UserAvatarUpdate");
	private String userGameUpdate = DiscordBot.config.getString("UserGameUpdate");
	private String userNameUpdate = DiscordBot.config.getString("UserNameUpdate");
	private String userOnlineStatusUpdate = DiscordBot.config.getString("UserOnlineStatusUpdate");
	
	@Override
	public void onUserAvatarUpdate(UserAvatarUpdateEvent event) {
		if (userAvatarUpdate.toLowerCase().equals("true")) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``New Avatar:`` **" + event.getUser().getAvatarId() + "**");
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent event) {
		if (userGameUpdate.toLowerCase().equals("true") && !(event.getUser().getCurrentGame() == null)) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Game:`` **" + event.getUser().getCurrentGame() + "**");
		}
		return;
	}
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent event) {
		if (userNameUpdate.toLowerCase().equals("true")) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getPreviousUsername() + "** ``New name:`` **" + event.getUser().getUsername() + "**");
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
		if (userOnlineStatusUpdate.toLowerCase().equals("true")) {
			if (event.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Status:`` **Online**");
			}
			if (event.getUser().getOnlineStatus().toString().equals("AWAY")) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Status:`` **Away**");
			}
			if (event.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Status:`` **Offline**");
			}
		}
		return;
	}
}