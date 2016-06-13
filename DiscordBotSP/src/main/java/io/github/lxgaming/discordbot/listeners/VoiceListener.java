package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.events.voice.VoiceServerDeafEvent;
import net.dv8tion.jda.events.voice.VoiceServerMuteEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class VoiceListener extends ListenerAdapter {
	
	private static String GUILDID = DiscordBot.CONFIG.getString("DiscordBot.Credentials.Guild");
	private static Boolean VOICESERVERDEAF = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.VoiceServerDeaf");
	private static Boolean VOICESERVERMUTE = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.VoiceServerMute");
	
	@Override
	public void onVoiceServerDeaf(VoiceServerDeafEvent VSD) {
		if (VOICESERVERDEAF == true) {
			if (VSD.getVoiceStatus().isServerDeaf() == true) {
				MessageSender.sendMessage("Deafened", VSD.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(VSD.getUser()), "VoiceServerDeaf.Deafened", true, true, false);
			} else {
				MessageSender.sendMessage("Undeafened", VSD.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(VSD.getUser()), "VoiceServerDeaf.Undeafened", true, true, false);
			}
		}
		return;
	}
	
	@Override
	public void onVoiceServerMute(VoiceServerMuteEvent VSM) {
		if (VOICESERVERMUTE == true) {
			if (VSM.getVoiceStatus().isServerMuted() == true) {
				MessageSender.sendMessage("Muted", VSM.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(VSM.getUser()), "VoiceServerMute.Muted", true, true, false);
			} else {
				MessageSender.sendMessage("Unmuted", VSM.getUser().getUsername(), DiscordBot.API.getGuildById(GUILDID).getNicknameForUser(VSM.getUser()), "VoiceServerMute.Unmuted", true, true, false);
			}
		}
		return;
	}
}