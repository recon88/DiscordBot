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

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class Audio {
	
	private String textChannel;
	private String userName;
	private String nickName;
	private AudioTrack audioTrack;
	
	public Audio setTextChannel(String textChannel) {
		this.textChannel = textChannel;
		return this;
	}
	
	public String getTextChannel() {
		return this.textChannel;
	}
	
	public Audio setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public Audio setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public Audio setAudioTrack(AudioTrack audioTrack) {
		this.audioTrack = audioTrack;
		return this;
	}
	
	public AudioTrack getAudioTrack() {
		return this.audioTrack;
	}
}
