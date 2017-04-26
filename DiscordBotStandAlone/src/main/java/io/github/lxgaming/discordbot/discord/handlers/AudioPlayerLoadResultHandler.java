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

package io.github.lxgaming.discordbot.discord.handlers;

import java.util.Iterator;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.entries.Audio;
import io.github.lxgaming.discordbot.util.ConsoleOutput;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class AudioPlayerLoadResultHandler implements AudioLoadResultHandler {
	
	private final TextChannel textChannel;
	private final Member member;
	
	public AudioPlayerLoadResultHandler(TextChannel textChannel, Member member) {
		this.textChannel = textChannel;
		this.member = member;
	}
	
	@Override
	public void trackLoaded(AudioTrack audioTrack) {
		if (audioTrack == null) {
			return;
		}
		
		Audio audio = new Audio(getTextChannel(), getMember(), audioTrack);
		if (DiscordBot.getInstance().getDiscord().getAudioPlayer().startTrack(audio.getAudioTrack(), true)) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(audio.getTextChannel(), audio.getMember().getEffectiveName(),
					"Now playing - '" + audio.getAudioTrack().getInfo().title + "'.");
		} else if (DiscordBot.getInstance().getDiscord().getAudioQueue().getQueue().add(audio)) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(audio.getTextChannel(), audio.getMember().getEffectiveName(),
					"'" + audio.getAudioTrack().getInfo().title + "' Has been added to the queue.");
		}
		audio = null;
	}
	
	@Override
	public void playlistLoaded(AudioPlaylist audioPlaylist) {
		for (Iterator<AudioTrack> iterator = audioPlaylist.getTracks().iterator(); iterator.hasNext();) {
			AudioTrack audioTrack = iterator.next();
			
			if (audioTrack == null) {
				continue;
			}
			
			Audio audio = new Audio(getTextChannel(), getMember(), audioTrack);
			if (DiscordBot.getInstance().getDiscord().getAudioPlayer().startTrack(audioTrack, true)) {
				DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(audio.getTextChannel(), audio.getMember().getEffectiveName(),
						"Now playing - '" + audio.getAudioTrack().getInfo().title + "'.");
			} else if (DiscordBot.getInstance().getDiscord().getAudioQueue().getQueue().add(audio)) {
				ConsoleOutput.debug("'" + audio.getAudioTrack().getInfo().title + "' Has been added to the queue.");
			}
			
			audio = null;
		}
		ConsoleOutput.debug(audioPlaylist.getTracks().size() + " Songs have been added to the queue.");
		DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(getTextChannel(), getMember().getEffectiveName(),
				audioPlaylist.getTracks().size() + " Songs have been added to the queue.");
	}
	
	@Override
	public void noMatches() {
		DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(getTextChannel(), getMember().getEffectiveName(), "No matches found!");
	}
	
	@Override
	public void loadFailed(FriendlyException exception) {
		DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(getTextChannel(), getMember().getEffectiveName(), "Failed to load - " + exception.getMessage());
		ConsoleOutput.error("Failed to load - " + exception.getMessage());
		exception.printStackTrace();
	}
	
	public TextChannel getTextChannel() {
		return textChannel;
	}
	
	public Member getMember() {
		return member;
	}
}