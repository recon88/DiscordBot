package io.github.lxgaming.discordmusicbot.commands;

import io.github.lxgaming.discordmusicbot.DiscordMusicBot;
import io.github.lxgaming.discordmusicbot.util.PlayerManager;
import io.github.lxgaming.discordmusicbot.util.AudioManager;
import io.github.lxgaming.discordmusicbot.util.ConsoleOutput;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.entities.VoiceChannel;

public class AudioCommand {
	
	public static void audio(TextChannel channel, String command, User author) {
		if (command.equals("summon")) {
			for (VoiceChannel voice : DiscordMusicBot.api.getVoiceChannels()) {
				if (voice.getUsers().contains(author)) {
					DiscordMusicBot.api.getAudioManager(channel.getGuild()).openAudioConnection(voice);
					ConsoleOutput.info("DiscordMusicBot connected to " + voice.getName() + " Voice Channel.");
					return;
				}
			}
			channel.sendMessage("Unable to find voice channel!");
		}
		
		if (command.startsWith("volume")) {
			if (command.trim().length() == 6) {
				channel.sendMessage("Specific a volume level!");
				return;
			}
			try {
				PlayerManager.player.setVolume(Float.valueOf(command.substring(6).trim()));
			} catch (Exception ex) {
				channel.sendMessage("Error while setting volume!");
			}
		}
		
		if (command.startsWith("play")) {
			if (PlayerManager.player == null) {
				AudioManager.getAudioFile(command.substring(5));
				DiscordMusicBot.api.getAudioManager(channel.getGuild()).setSendingHandler(PlayerManager.player);
				return;
			}
			channel.sendMessage("Player is already playing!");
		}
		
		if (command.equals("resume")) {
			if (PlayerManager.player != null && PlayerManager.player.isPaused() == true) {
				PlayerManager.player.play();
				return;
			}
			channel.sendMessage("Player is not paused!");
		}
		
		if (command.equals("pause")) {
			if (PlayerManager.player != null && PlayerManager.player.isPaused() != true) {
				PlayerManager.player.pause();
				return;
			}
			channel.sendMessage("Player is not playing!");
		}
		
		if (command.equals("stop")) {
			PlayerManager.stop(channel);
			if (PlayerManager.player != null && PlayerManager.player.isStopped() != true) {
				PlayerManager.player.stop();
				return;
			}
			channel.sendMessage("Player is already stopped!");
		}
	}
}
