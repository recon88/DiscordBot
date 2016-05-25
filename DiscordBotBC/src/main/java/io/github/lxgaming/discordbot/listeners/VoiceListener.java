package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	private static Boolean voiceServerDeaf = DiscordBot.config.getBoolean("DiscordBot.Listeners.VoiceServerDeaf");
	private static Boolean voiceServerMute = DiscordBot.config.getBoolean("DiscordBot.Listeners.VoiceServerMute");
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (voiceServerDeaf == true) {
			if (VSD.getVoiceStatus().isServerDeaf() == true) {
				MessageSender.sendMessage("Deafened", VSD.getUser().getUsername(), VSD.getUser().getId(), "VoiceServerDeaf.Deafened", true, true, false);
			} else {
				MessageSender.sendMessage("Undeafened", VSD.getUser().getUsername(), VSD.getUser().getId(), "VoiceServerDeaf.Undeafened", true, true, false);
			}
		}
		return;
	}
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {
		if (voiceServerMute == true) {
			if (VSM.getVoiceStatus().isServerMuted() == true) {
				MessageSender.sendMessage("Muted", VSM.getUser().getUsername(), VSM.getUser().getId(), "VoiceServerMute.Muted", true, true, false);
			} else {
				MessageSender.sendMessage("Unmuted", VSM.getUser().getUsername(), VSM.getUser().getId(), "VoiceServerMute.Unmuted", true, true, false);
			}
		}
		return;
	}
}