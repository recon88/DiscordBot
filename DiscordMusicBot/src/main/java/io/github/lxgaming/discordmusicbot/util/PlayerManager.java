package io.github.lxgaming.discordmusicbot.util;

import java.io.File;

import io.github.lxgaming.discordmusicbot.DiscordMusicBot;
import net.dv8tion.jda.audio.player.FilePlayer;
import net.dv8tion.jda.audio.player.Player;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.TextChannel;

public class PlayerManager {
	
	public static Player player = null;
	public static Guild guild = DiscordMusicBot.api.getGuildById(DiscordMusicBot.config.getString("GuildID"));
	
	public static void connect() {

	}
	
	public static void play(String audiofile) {
		if (player == null) {
			File audioFile = null;
			
			try {
				audioFile = new File(audiofile);
				player = new FilePlayer(audioFile);
				player.play();
			} catch (Exception ex) {
				ConsoleOutput.error("Unable to play");
			}
		}
		return;
	}
	
	public static void control(TextChannel channel, String state, float volume) {
		if (player == null) {
			channel.sendMessage("Player is null!");
			ConsoleOutput.error("Player is null!");
			return;
		}
		
		if (state.equals("volume")) {
			if (volume > 0 && volume < 100) {
				player.setVolume(volume);
				channel.sendMessage("Player volume " + volume);
			} else {
				channel.sendMessage("Player volume must be between 0 - 100");
			}
			return;
		}
		
		if (state.equals("resume")) {
			if (player.isPaused() || player.isStopped()) {
				player.play();
				channel.sendMessage("Player resumed");
			} else if (player.isPlaying()) {
				channel.sendMessage("Player already playing!");
			}
			return;
		}
		
		if (state.equals("pause")) {
			if (player.isPlaying()) {
				player.pause();
				channel.sendMessage("Player paused.");
			} else if (player.isPaused()) {
				channel.sendMessage("Player already paused!");
			}
			return;
		}
		
		if (state.equals("stop")) {
			if (player.isPlaying()) {
				player.stop();
				channel.sendMessage("Player stopped.");
			} else if (player.isStopped()) {
				channel.sendMessage("Player already stopped!");
			}
			return;
		}
	}
}