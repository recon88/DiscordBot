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

package io.github.lxgaming.discordbot.discord.commands;

import java.util.Iterator;
import java.util.List;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.discord.handlers.AudioPlayerLoadResultHandler;
import io.github.lxgaming.discordbot.entries.ICommand;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class PlayCommand implements ICommand {
	
	@Override
	public void execute(TextChannel textChannel, Member member, Message message, List<String> arguments) {
		if (member.getGuild().getAudioManager().getConnectedChannel() == null) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, "Not connected to voice channel!");
			return;
		}
		
		if (arguments == null || arguments.isEmpty()) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, "Invalid arguments!");
			return;
		}
		
		for (Iterator<String> iterator = arguments.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			if (!string.startsWith("https://")) {
				continue;
			}
			
			DiscordBot.getInstance().getDiscord().getAudioPlayerManager().loadItem(string, new AudioPlayerLoadResultHandler(textChannel, member));
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, member.getEffectiveName(), "Processing...");
		}
	}
	
	@Override
	public String getDescription() {
		return "Play Music";
	}
}