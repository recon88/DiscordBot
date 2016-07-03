package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Date;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	private String voiceServerMute = DiscordBot.config.getString("VoiceServerDeaf");
	private String voiceServerDeaf = DiscordBot.config.getString("VoiceServerMute");
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent event) {
		if (voiceServerDeaf.toLowerCase().equals("true")) {
			if (event.getVoiceStatus().isServerDeaf() == true) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Voice:`` **Server Deafened**");
			} else {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Voice:`` **Server Undeafened**");
			}
		}
		return;
	}
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent event) {		
		if (voiceServerMute.toLowerCase().equals("true")) {
			if (event.getVoiceStatus().isServerMuted() == true) {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Voice:`` **Server Muted**");
			} else {
				MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + event.getUser().getUsername() + "** ``Voice:`` **Server Unmuted**");
			}
		}
		return;
	}
}