package io.github.lxgaming.discordmusicbot.commands;

import io.github.lxgaming.discordmusicbot.util.AudioManager;
import io.github.lxgaming.discordmusicbot.util.PlayerManager;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class AudioCommand {
	
	public static void audio(TextChannel channel, String command, User author) {
		if (command.equals("summon")) {
			PlayerManager.connect(channel, author);
			return;
		}
		
		if (command.startsWith("play")) {
			if (command.substring(5).startsWith("https://www.youtube.com/")) {
				AudioManager.getAudioFile(command.substring(4).trim());
			} else {
				channel.sendMessage("Only Youtube links are allowed");
			}
			return;
		}
		
		if (command.startsWith("volume")) {
			PlayerManager.control(channel, command, Float.valueOf(command.substring(6).trim()));
			return;
		}
		
		if (command.equals("resume")) {
			PlayerManager.control(channel, command, 0);
			return;
		}
		
		if (command.equals("pause")) {
			PlayerManager.control(channel, command, 0);
			return;
		}
		
		if (command.equals("stop")) {
			PlayerManager.control(channel, command, 0);
			return;
		}
		return;
	}
}
