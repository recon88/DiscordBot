package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	public String botTC = DiscordBot.config.props.getProperty("BotChannel");
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {
		if (VSM.getVoiceStatus().isServerMuted() == true) {
			VSM.getJDA().getTextChannelById(botTC).sendMessage(VSM.getUser().getUsername() + " Has been Server Muted!");
		} else {
			VSM.getJDA().getTextChannelById(botTC).sendMessage(VSM.getUser().getUsername() + " Has been Server Unmuted!");
		}
	}
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (VSD.getVoiceStatus().isServerDeaf() == true) {
			VSD.getJDA().getTextChannelById(botTC).sendMessage(VSD.getUser().getUsername() + " Has been Server Deafened!");
		} else {
			VSD.getJDA().getTextChannelById(botTC).sendMessage(VSD.getUser().getUsername() + " Has been Server Undeafened!");
		}
	}
}
