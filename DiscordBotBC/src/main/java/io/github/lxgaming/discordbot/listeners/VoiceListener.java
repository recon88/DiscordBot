package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.util.Date;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {
		if (VSM.getVoiceStatus().isServerMuted() == true) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSM.getUser().getUsername() + "** ``Voice:`` **Muted**");
		} else {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSM.getUser().getUsername() + "** ``Voice:`` **Unmuted**");
		}
		return;
	}
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (VSD.getVoiceStatus().isServerDeaf() == true) {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSD.getUser().getUsername() + "** ``Voice:`` **Deafened**");
		} else {
			MessageSender.sendMessage("``Time:`` **" + Date.getTime() + "** ``User:`` **" + VSD.getUser().getUsername() + "** ``Voice:`` **Undeafened**");
		}
		return;
	}
}