package io.github.lxgaming.discordbot.listeners;

import org.json.JSONObject;

import io.github.lxgaming.discordbot.ExampleUtils;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	JSONObject config = ExampleUtils.getConfig();
	public String textChannelID = config.getString("textchannelid");
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {
		if (VSM.getVoiceStatus().isServerMuted() == true) {
			VSM.getJDA().getTextChannelById(textChannelID).sendMessage(VSM.getUser().getUsername() + " Has been Server Muted!");
		} else {
			VSM.getJDA().getTextChannelById(textChannelID).sendMessage(VSM.getUser().getUsername() + " Has been Server Unmuted!");
		}
	}
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (VSD.getVoiceStatus().isServerDeaf() == true) {
			VSD.getJDA().getTextChannelById(textChannelID).sendMessage(VSD.getUser().getUsername() + " Has been Server Deafened!");
		} else {
			VSD.getJDA().getTextChannelById(textChannelID).sendMessage(VSD.getUser().getUsername() + " Has been Server Undeafened!");
		}
	}
}
