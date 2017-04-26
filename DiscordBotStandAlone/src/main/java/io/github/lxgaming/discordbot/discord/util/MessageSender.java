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

package io.github.lxgaming.discordbot.discord.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.ConsoleOutput;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class MessageSender {
	
	private List<Message> messages;
	
	public MessageSender() {
		messages = new LinkedList<Message>();
	}
	
	public void sendMessage(TextChannel textChannel, String username, String message) {
		sendMessage(textChannel, formatMessage(username, message));
	}
	
	public void sendMessage(TextChannel textChannel, String message) {
		textChannel.sendMessage(message).queue(new Consumer<Message>() {
			
			@Override
			public void accept(Message message) {
				ConsoleOutput.info("" + DiscordBot.getInstance().getConfig().isDeleteMessages());
				if (DiscordBot.getInstance().getConfig().isDeleteMessages()) {
					getMessages().add(message);
				}
			}
		});
	}
	
	private String formatMessage(String username, String message) {
		String messageFormat = DiscordBot.getInstance().getConfig().getMessageFormat();
		if (messageFormat.trim().equals("") || messageFormat.trim().equals("null")) {
			ConsoleOutput.error("DiscordBot format is null!");
			return null;
		}
		
		return messageFormat
				.replace("{TIME}", new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()))
				.replace("{USER}", username)
				.replace("{MESSAGE}", message);
	}
	
	public List<Message> getMessages() {
		return messages;
	}
}