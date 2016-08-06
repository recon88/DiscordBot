package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.user.UserAvatarUpdateEvent;
import net.dv8tion.jda.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.events.user.UserNameUpdateEvent;
import net.dv8tion.jda.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class UserListener extends ListenerAdapter {
	
	private static String guildID = DiscordBot.config.getString("DiscordBot.Credentials.Guild");
	private static boolean mainBot = DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot");
	private static boolean userAvatarUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserAvatarUpdate");
	private static boolean userGameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserGameUpdate");
	private static boolean userNameUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserNameUpdate");
	private static boolean userOnlineStatusUpdate = DiscordBot.config.getBoolean("DiscordBot.Listeners.UserOnlineStatusUpdate");
	
	@Override
	public void onUserAvatarUpdate(UserAvatarUpdateEvent event) {
		if (userAvatarUpdate == true && mainBot == true) {
			MessageSender.sendMessage(event.getUser().getAvatarId(), event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "UserAvatarUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserGameUpdate(UserGameUpdateEvent event) {
		if (userGameUpdate == true && event.getUser().getCurrentGame() != null && mainBot == true) {
			MessageSender.sendMessage(event.getUser().getCurrentGame().getName(), event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "UserGameUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserNameUpdate(UserNameUpdateEvent event) {
		if (userNameUpdate == true && mainBot == true) {
			MessageSender.sendMessage(event.getUser().getUsername(), event.getPreviousUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "UserNameUpdate", true, true, false);
		}
		return;
	}
	
	@Override
	public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent event) {
		if (userOnlineStatusUpdate == true && mainBot == true) {
			if (event.getUser().getOnlineStatus().toString().equals("ONLINE")) {
				MessageSender.sendMessage("Online", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "UserOnlineStatusUpdate.Online", true, true, false);
			}
			if (event.getUser().getOnlineStatus().toString().equals("AWAY")) {
				MessageSender.sendMessage("Away", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "UserOnlineStatusUpdate.Away", true, true, false);
			}
			if (event.getUser().getOnlineStatus().toString().equals("OFFLINE")) {
				MessageSender.sendMessage("Offline", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "UserOnlineStatusUpdate.Offline", true, true, false);
			}
		}
		return;
	}
}