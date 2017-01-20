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

package io.github.lxgaming.discordbot.listeners;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.Audio;
import io.github.lxgaming.discordbot.util.ConsoleOutput;

public class AudioListener extends AudioEventAdapter {
	
	@Override
	public void onPlayerPause(AudioPlayer audioPlayer) {
		ConsoleOutput.debug("Player Pause!");
	}
	
	@Override
	public void onPlayerResume(AudioPlayer audioPlayer) {
		ConsoleOutput.debug("Player Resume!");
	}
	
	@Override
	public void onTrackEnd(AudioPlayer audioPlayer, AudioTrack audioTrack, AudioTrackEndReason audioTrackEndReason) {
		ConsoleOutput.debug("Track End!");
		if (!audioTrackEndReason.mayStartNext) {
			return;
		}
		
		if (DiscordBot.getInstance().getBlockingQueue().isEmpty()) {
			return;
		}
		
		Audio audio = DiscordBot.getInstance().getBlockingQueue().poll();
		if (audio != null) {
			DiscordBot.getInstance().getAudioPlayer().playTrack(audio.getAudioTrack());
		}
	}
	
	@Override
	public void onTrackException(AudioPlayer audioPlayer, AudioTrack audioTrack, FriendlyException friendlyException) {
		ConsoleOutput.debug("Track Exception!");
		ConsoleOutput.error(friendlyException.getMessage());
		friendlyException.printStackTrace();
		
		if (DiscordBot.getInstance().getBlockingQueue().isEmpty()) {
			return;
		}
		
		Audio audio = DiscordBot.getInstance().getBlockingQueue().poll();
		if (audio != null) {
			DiscordBot.getInstance().getAudioPlayer().playTrack(audio.getAudioTrack());
		}
	}
	
	@Override
	public void onTrackStart(AudioPlayer audioPlayer, AudioTrack audioTrack) {
		ConsoleOutput.debug("Track Start!");
	}
	
	@Override
	public void onTrackStuck(AudioPlayer audioPlayer, AudioTrack audioTrack, long time) {
		ConsoleOutput.debug("Track Stuck!");
	}
}
