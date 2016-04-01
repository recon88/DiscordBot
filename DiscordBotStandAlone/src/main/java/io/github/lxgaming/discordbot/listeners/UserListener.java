package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Date;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	private String botTC = DiscordBot.config.props.getProperty("BotChannel");
	private String userNameUpdate = DiscordBot.config.props.getProperty("UserNameUpdate");
	private String userGameUpdate = DiscordBot.config.props.getProperty("UserGameUpdate");
	private String userOnlineStatusUpdate = DiscordBot.config.props.getProperty("UserOnlineStatusUpdate");
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		if (userNameUpdate.toLowerCase().equals("true")) {
			UNU.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UNU.getPreviousUsername() + "** ``New name:`` **" + UNU.getUser().getUsername() + "**");
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if (userGameUpdate.toLowerCase().equals("true") &&!(UGU.getUser().getCurrentGame() == null)) {
			UGU.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UGU.getUser().getUsername() + "** ``Game:`` **" + UGU.getUser().getCurrentGame() + "**");
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (userOnlineStatusUpdate.toLowerCase().equals("true")) {
			if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				UOSU.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UOSU.getUser().getUsername() + "** ``Status:`` **Online**");
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
				UOSU.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UOSU.getUser().getUsername() + "** ``Status:`` **Away**");
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				UOSU.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + UOSU.getUser().getUsername() + "** ``Status:`` **Offline**");
			}
		}
		return;
	}
}