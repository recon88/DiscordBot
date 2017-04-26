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

import java.util.List;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.entries.ICommand;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;

public class JoinCommand implements ICommand {
	
	@Override
	public void execute(TextChannel textChannel, Member member, Message message, List<String> arguments) {
		if (arguments == null || arguments.isEmpty()) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, "Invalid arguments!");
			return;
		}
		
		List<VoiceChannel> voiceChannels = member.getGuild().getVoiceChannelsByName(arguments.get(0), false);
		if (voiceChannels.size() < 1) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, "Unable to find specified voice channel!");
			return;
		}
		
		if (member.getGuild().getAudioManager().getConnectedChannel() != null) {
			member.getGuild().getAudioManager().closeAudioConnection();
		}
		
		try {
			member.getGuild().getAudioManager().openAudioConnection(voiceChannels.get(0));
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, member.getEffectiveName(), "Joining channel '" + voiceChannels.get(0).getName() + "'.");
		} catch (RuntimeException ex) {
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, "Cannot join channel '" + voiceChannels.get(0).getName() + "', " + ex.getMessage());
		}
	}
	
	@Override
	public String getDescription() {
		return "Connects the bot to a voice channel.";
	}
}