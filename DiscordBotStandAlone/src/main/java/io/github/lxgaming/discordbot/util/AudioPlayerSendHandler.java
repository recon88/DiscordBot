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

import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.core.audio.AudioSendHandler;

public class AudioPlayerSendHandler implements AudioSendHandler {
	
	private AudioFrame audioFrame;
	
	@Override
	public boolean canProvide() {
		if (this.audioFrame == null) {
			this.audioFrame = DiscordBot.getInstance().getAudioPlayer().provide();
		}
		
		if (this.audioFrame != null) {
			return true;
		}
		return false;
	}

	@Override
	public byte[] provide20MsAudio() {
		if (this.audioFrame == null) {
			this.audioFrame = DiscordBot.getInstance().getAudioPlayer().provide();
		}
		
		byte[] data = null;
		if (this.audioFrame != null && this.audioFrame.data != null) {
			data = this.audioFrame.data;
		}
		
		this.audioFrame = null;
		return data;
	}
	
	@Override
	public boolean isOpus() {
		return true;
	}
}
