package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	String botTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.Bot");
	MessageSender ms = new MessageSender();
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {
		if (VSM.getVoiceStatus().isServerMuted() == true) {
			ms.sendMessage(VSM.getUser().getUsername() + " has been muted.");
		} else {
			ms.sendMessage(VSM.getUser().getUsername() + " has been unmuted.");
		}
		return;
	}
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (VSD.getVoiceStatus().isServerDeaf() == true) {
			ms.sendMessage(VSD.getUser().getUsername() + " has been deafened.");
		} else {
			ms.sendMessage(VSD.getUser().getUsername() + " has been undeafened.");
		}
		return;
	}
}