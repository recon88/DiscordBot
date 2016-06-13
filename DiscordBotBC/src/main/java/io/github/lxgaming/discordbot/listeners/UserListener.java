package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	private static String GUILDID = DiscordBot.CONFIG.getString("DiscordBot.Credentials.Guild");
	private static Boolean USERAVATARUPDATE = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.UserAvatarUpdate");
	private static Boolean USERNAMEUPDATE = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.UserNameUpdate");
	private static Boolean USERGAMEUPDATE = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.UserGameUpdate");
	private static Boolean USERONLINESTATUSUPDATE = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.UserOnlineStatusUpdate");
	
	@Override
	public void onUserAvatarUpdate(UserAvatarUpdateEvent UAU) {
		if (USERAVATARUPDATE == true) {
			MessageSender.sendMessage(UAU.getUser().getAvatarId(), UAU.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(UAU.getUser()), "UserAvatarUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent UGU) {
		if ((USERNAMEUPDATE == true) && !(UGU.getUser().getCurrentGame() == null)) {
			MessageSender.sendMessage(UGU.getUser().getCurrentGame().getName(), UGU.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(UGU.getUser()), "UserGameUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent UNU) {
		if (USERGAMEUPDATE == true) {
			MessageSender.sendMessage(UNU.getUser().getUsername(), UNU.getPreviousUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(UNU.getUser()), "UserNameUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent UOSU) {
		if (USERONLINESTATUSUPDATE == true) {
			if (UOSU.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				MessageSender.sendMessage("Online", UOSU.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(UOSU.getUser()), "UserOnlineStatusUpdate.Online", true, true, false);
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("AWAY")) {
				MessageSender.sendMessage("Away", UOSU.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(UOSU.getUser()), "UserOnlineStatusUpdate.Away", true, true, false);
			}
			if (UOSU.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				MessageSender.sendMessage("Offline", UOSU.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(UOSU.getUser()), "UserOnlineStatusUpdate.Offline", true, true, false);
			}
		}
		return;
	}
}