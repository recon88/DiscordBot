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

package io.github.lxgaming.discordbot.configuration;

import java.util.LinkedList;
import java.util.List;

public class Group {
	
	private String name;
	private String commandBlacklist;
	private String commandWhitelist;
	private boolean ignoreNonVoice;
	private String roles;
	private String users;
	private int maxSongs;
	private int maxSongLength;
	private boolean allowPlaylists;
	private int maxPlaylistLength;
	private int skipsRequired;
	private float skipRatio;
	private boolean instantSkip;
	
	public String getName() {
		return this.name;
	}
	
	public String getCommandBlacklist() {
		return this.commandBlacklist;
	}
	
	public String getCommandWhitelist() {
		return this.commandWhitelist;
	}
	
	public boolean ignoreNonVoice() {
		return this.ignoreNonVoice;
	}
	
	public String getRoles() {
		return this.roles;
	}
	
	public List<String> getRoleList() {
		List<String> roleList = new LinkedList<String>();
		for (String role : this.roles.split(", ")) {
			roleList.add(role);
		}
		return roleList;
	}
	
	public String getUsers() {
		return this.users;
	}
	
	public List<String> getUserList() {
		List<String> userList = new LinkedList<String>();
		for (String user : this.users.split(", ")) {
			userList.add(user);
		}
		return userList;
	}
	
	public int getMaxSongs() {
		return this.maxSongs;
	}
	
	public int getMaxSongLength() {
		return this.maxSongLength;
	}
	
	public boolean getAllowPlaylists() {
		return this.allowPlaylists;
	}
	
	public int getMaxPlaylistLength() {
		return this.maxPlaylistLength;
	}
	
	public int getSkipsRequired() {
		return this.skipsRequired;
	}
	
	public float getSkipRatio() {
		return this.skipRatio;
	}
	
	public boolean getInstantSkip() {
		return this.instantSkip;
	}
}
