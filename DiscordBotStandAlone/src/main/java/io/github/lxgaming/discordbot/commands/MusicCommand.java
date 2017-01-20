/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.discordbot.commands;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.AudioPlayerLoadResultHandler;
import io.github.lxgaming.discordbot.util.ConsoleOutput;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;

public class MusicCommand implements ICommand {
	
	@Override
	public boolean execute(TextChannel textChannel, Member member, Message message) {
		String command = message.getContent().substring(DiscordBot.getInstance().getConfiguration().getClient().getCommandPrefix().length());
		
		if (command.toLowerCase().startsWith("join")) {
			List<VoiceChannel> voiceChannels = textChannel.getJDA().getVoiceChannelByName(command.substring(4).trim(), false);
			if (voiceChannels.size() == 0) {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Cannot find voice channel with that name!");
			}
			
			try {
				if (textChannel.getGuild().getAudioManager().getConnectedChannel() != null) {
					textChannel.getGuild().getAudioManager().closeAudioConnection();
				}
				
				textChannel.getGuild().getAudioManager().openAudioConnection(voiceChannels.get(0));
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Joining channel '" + voiceChannels.get(0).getName() + "'.");
			} catch (IllegalArgumentException | IllegalStateException | PermissionException ex) {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Unable to join channel '" + voiceChannels.get(0).getName() + "' - " + ex.getMessage());
				ConsoleOutput.debug("Exception joining channel!");
			}
			return true;
		}
		
		if (command.toLowerCase().startsWith("play") && command.contains("https://")) {
			if (textChannel.getGuild().getAudioManager().getConnectedChannel() == null) {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Not connected to voice!");
			} else {
				DiscordBot.getInstance().getAudioPlayerManager().loadItem(command.substring(4).trim(), new AudioPlayerLoadResultHandler(textChannel.getId(), member.getEffectiveName(), member.getNickname()));
			}
			return true;
		}
		
		if (command.equalsIgnoreCase("resume")) {
			if (DiscordBot.getInstance().getAudioPlayer().isPaused()) {
				DiscordBot.getInstance().getAudioPlayer().setPaused(false);
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Player resumed.");
			} else {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Player already playing!");
			}
			return true;
		}
		
		if (command.equalsIgnoreCase("pause")) {
			if (!DiscordBot.getInstance().getAudioPlayer().isPaused()) {
				DiscordBot.getInstance().getAudioPlayer().setPaused(true);
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Player paused.");
			} else {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Player already paused!");
			}
			return true;
		}
		
		if (command.equalsIgnoreCase("volume")) {
			DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Volume is currently " + DiscordBot.getInstance().getAudioPlayer().getVolume());
			return true;
		}
		
		if (command.toLowerCase().startsWith("volume")) {
			try {
				Integer volume = Integer.parseInt(command.substring(6).trim());
				if (volume <= 100 && volume >= 0) {
					DiscordBot.getInstance().getAudioPlayer().setVolume(volume);
					DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Volume is now " + volume);
					return true;
				}
				throw new NumberFormatException();
			} catch (NullPointerException | NumberFormatException ex) {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Invalid volume!");
			}
			return true;
		}
		
		if (command.equalsIgnoreCase("skip")) {
			DiscordBot.getInstance().getAudioPlayer().playTrack(DiscordBot.getInstance().getBlockingQueue().poll().getAudioTrack());
			return true;
		}
		
		if (command.equalsIgnoreCase("clear")) {
			DiscordBot.getInstance().getBlockingQueue().clear();
			DiscordBot.getInstance().getAudioPlayer().stopTrack();
			DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Queue cleared.");
			return true;
		}
		
		if (command.equalsIgnoreCase("nowplaying")) {
			if (DiscordBot.getInstance().getAudioPlayer().getPlayingTrack() == null) {
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Nothing playing.");
			} else {
				AudioTrack audioTrack = DiscordBot.getInstance().getAudioPlayer().getPlayingTrack();
				DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), "Now playing - " + audioTrack.getInfo().title + " (" + (audioTrack.getPosition() / 1000) + "/" + (audioTrack.getDuration() / 1000) + ")");
			}
			return true;
		}
		return false;
	}
}
