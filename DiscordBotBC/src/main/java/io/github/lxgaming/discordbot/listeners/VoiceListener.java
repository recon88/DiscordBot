package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	private static String guildID = DiscordBot.config.getString("DiscordBot.Credentials.Guild");
	private static boolean voiceServerDeaf = DiscordBot.config.getBoolean("DiscordBot.Listeners.VoiceServerDeaf");
	private static boolean vocieServerMute = DiscordBot.config.getBoolean("DiscordBot.Listeners.VoiceServerMute");
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent event) {
		if (voiceServerDeaf == true) {
			if (event.getVoiceStatus().isServerDeaf() == true) {
				MessageSender.sendMessage("Deafened", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "VoiceServerDeaf.Deafened", true, true, false);
			} else {
				MessageSender.sendMessage("Undeafened", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "VoiceServerDeaf.Undeafened", true, true, false);
			}
		}
		return;
	}
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent event) {
		if (vocieServerMute == true) {
			if (event.getVoiceStatus().isServerMuted() == true) {
				MessageSender.sendMessage("Muted", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "VoiceServerMute.Muted", true, true, false);
			} else {
				MessageSender.sendMessage("Unmuted", event.getUser().getUsername(), DiscordBot.jda.getGuildById(guildID).getNicknameForUser(event.getUser()), DiscordBot.jda.getGuildById(guildID).getName(), "VoiceServerMute.Unmuted", true, true, false);
			}
		}
		return;
	}
}