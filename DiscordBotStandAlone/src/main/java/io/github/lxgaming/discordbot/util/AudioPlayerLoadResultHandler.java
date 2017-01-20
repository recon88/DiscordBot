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

package io.github.lxgaming.discordbot.util;

import java.util.Iterator;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import io.github.lxgaming.discordbot.DiscordBot;

public class AudioPlayerLoadResultHandler implements AudioLoadResultHandler {
	
	private final String textChannel;
	private final String userName;
	private final String nickName;
	
	public AudioPlayerLoadResultHandler(String textChannel, String userName, String nickName) {
		this.textChannel = textChannel;
		this.userName = userName;
		this.nickName = nickName;
	}
	
	@Override
	public void trackLoaded(AudioTrack audioTrack) {
		ConsoleOutput.debug("Track Loaded!");
		if (audioTrack == null) {
			return;
		}
		
		Audio audio = new Audio().setTextChannel(this.textChannel).setUserName(this.userName).setNickName(this.nickName).setAudioTrack(audioTrack);
		
		if (DiscordBot.getInstance().getAudioPlayer().startTrack(audio.getAudioTrack(), true)) {
			DiscordBot.getInstance().getMessageSender().sendMessage(audio.getTextChannel(), audio.getUserName(), audio.getNickName(), "Now playing - '" + audio.getAudioTrack().getInfo().title + "'.");
		} else if (DiscordBot.getInstance().getBlockingQueue().offer(audio.setAudioTrack(audioTrack))) {
			DiscordBot.getInstance().getMessageSender().sendMessage(audio.getTextChannel(), audio.getUserName(), audio.getNickName(), "'" + audio.getAudioTrack().getInfo().title + "' Has been added to the queue.");
		}
		
		audio = null;
	}
	
	@Override
	public void playlistLoaded(AudioPlaylist audioPlaylist) {
		ConsoleOutput.debug("Playlist Loaded!");
		for (Iterator<AudioTrack> iterator = audioPlaylist.getTracks().iterator(); iterator.hasNext();) {
			AudioTrack audioTrack = iterator.next();
			
			if (audioTrack == null) {
				continue;
			}
			
			Audio audio = new Audio().setTextChannel(this.textChannel).setUserName(this.userName).setNickName(this.nickName).setAudioTrack(audioTrack);
			
			if (DiscordBot.getInstance().getAudioPlayer().startTrack(audioTrack, true)) {
				DiscordBot.getInstance().getMessageSender().sendMessage(audio.getTextChannel(), audio.getUserName(), audio.getNickName(), "Now playing - '" + audio.getAudioTrack().getInfo().title + "'.");
			} else if (DiscordBot.getInstance().getBlockingQueue().offer(audio.setAudioTrack(audioTrack))) {
				ConsoleOutput.debug("'" + audio.getAudioTrack().getInfo().title + "' Has been added to the queue.");
			}
			
			audio = null;
		}
		ConsoleOutput.debug(audioPlaylist.getTracks().size() + " Songs have been added to the queue.");
		DiscordBot.getInstance().getMessageSender().sendMessage(this.textChannel, this.userName, this.nickName, audioPlaylist.getTracks().size() + " Songs have been added to the queue.");
	}
	
	@Override
	public void noMatches() {
		DiscordBot.getInstance().getMessageSender().sendMessage(this.textChannel, this.userName, this.nickName, "No matches found!");
	}
	
	@Override
	public void loadFailed(FriendlyException exception) {
		DiscordBot.getInstance().getMessageSender().sendMessage(this.textChannel, this.userName, this.nickName, "Failed to load - " + exception.getMessage());
		ConsoleOutput.error("Failed to load - " + exception.getMessage());
		exception.printStackTrace();
	}
}
