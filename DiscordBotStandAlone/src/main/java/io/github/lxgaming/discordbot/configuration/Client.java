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

public class Client {
	
	private boolean debug;
	private String guildId;
	private String botChannel;
	private String commandPrefix;
	private String messageFormat;
	private String token;
	private String ownerId;
	private String autoJoinChannel;
	private int defaultVolume;
	private boolean autoPause;
	private boolean deleteMessages;
	private boolean deleteInvoking;
	private int deleteTime;
	
	public boolean getDebug() {
		return this.debug;
	}
	
	public String getGuildId() {
		return this.guildId;
	}
	
	public String getBotChannel() {
		return this.botChannel;
	}
	
	public String getCommandPrefix() {
		return this.commandPrefix;
	}
	
	public String getMessageFormat() {
		return this.messageFormat;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public String getOwnerId() {
		return this.ownerId;
	}
	
	public String getAutoJoinChannel() {
		return this.autoJoinChannel;
	}
	
	public int getDefaultVolume() {
		return this.defaultVolume;
	}
	
	public boolean getAutoPause() {
		return this.autoPause;
	}
	
	public boolean deleteMessages() {
		return this.deleteMessages;
	}
	
	public boolean deleteInvoking() {
		return this.deleteInvoking;
	}
	
	public int getDeleteTime() {
		return this.deleteTime;
	}
}
