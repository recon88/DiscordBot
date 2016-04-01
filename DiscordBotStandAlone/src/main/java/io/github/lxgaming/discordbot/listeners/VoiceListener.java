package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Date;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	private String botTC = DiscordBot.config.props.getProperty("BotChannel");
	private String voiceServerDeaf = DiscordBot.config.props.getProperty("VoiceServerDeaf");
	private String voiceServerMute = DiscordBot.config.props.getProperty("VoiceServerMute");
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (voiceServerDeaf.toLowerCase().equals("true")) {
			if (VSD.getVoiceStatus().isServerDeaf() == true) {
				VSD.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSD.getUser().getUsername() + "** ``Voice:`` **Server Deafened**");
			} else {
				VSD.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSD.getUser().getUsername() + "** ``Voice:`` **Server Undeafened**");
			}
		}
		return;
	}
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {		
		if (voiceServerMute.toLowerCase().equals("true")) {
			if (VSM.getVoiceStatus().isServerMuted() == true) {
				VSM.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSM.getUser().getUsername() + "** ``Voice:`` **Server Muted**");
			} else {
				VSM.getJDA().getTextChannelById(botTC).sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSM.getUser().getUsername() + "** ``Voice:`` **Server Unmuted**");
			}
		}
		return;
	}
}