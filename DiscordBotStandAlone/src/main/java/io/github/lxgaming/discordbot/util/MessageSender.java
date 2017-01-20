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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.core.entities.Message;

public class MessageSender {
	
	private List<Message> messages = new LinkedList<Message>();
	
	public void sendMessage(String textChannel, String userName, String nickName, String string) {
		DiscordBot.getInstance().getJDA().getTextChannelById(textChannel).sendMessage(getMessage(userName, nickName, string)).queue(new Consumer<Message>() {
			
			@Override
			public void accept(Message message) {
				if (DiscordBot.getInstance().getConfiguration().getClient().deleteMessages()) {
					messages.add(message);
				}
			}
		});
	}
	
	private String getMessage(String user, String usernick, String message) {
		String messageFormat = DiscordBot.getInstance().getConfiguration().getClient().getMessageFormat();
		if (messageFormat.trim().equals("") || messageFormat.trim().equals("null")) {
			ConsoleOutput.error("DiscordBot format is null!");
			return null;
		}
		
		if (usernick == null || usernick.equals("")) {
			usernick = user;
		}
		
		return messageFormat
				.replace("%time%", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()))
				.replace("%user%", user)
				.replace("%usernick%", usernick)
				.replace("%message%", message);
	}
	
	public void addMessage(Message message) {
		getMessages().add(message);
	}
	
	public List<Message> getMessages() {
		return this.messages;
	}
}
