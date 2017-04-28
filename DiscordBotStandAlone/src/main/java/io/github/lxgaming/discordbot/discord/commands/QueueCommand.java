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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.discord.util.DiscordHelper;
import io.github.lxgaming.discordbot.entries.Audio;
import io.github.lxgaming.discordbot.entries.ICommand;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class QueueCommand implements ICommand {
	
	@Override
	public void execute(TextChannel textChannel, Member member, Message message, List<String> arguments) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setAuthor(textChannel.getJDA().getSelfUser().getName(), null, textChannel.getJDA().getSelfUser().getEffectiveAvatarUrl());
		embedBuilder.setColor(Color.decode("#7289DA"));
		
		if (DiscordBot.getInstance().getDiscord().getAudioQueue().getQueue() == null || DiscordBot.getInstance().getDiscord().getAudioQueue().getQueue().isEmpty()) {
			embedBuilder.setTitle("Nothing Queued", null);
			DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, embedBuilder.build(), true);
			return;
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		int count = 0;
		for (Iterator<Audio> iterator = DiscordBot.getInstance().getDiscord().getAudioQueue().getQueue().iterator(); iterator.hasNext();) {
			Audio audio = iterator.next();
			if (count >= 10) {
				break;
			}
			stringBuilder.append("`[ " + DiscordHelper.getTimestamp(audio.getAudioTrack().getInfo().length) + " ]` " + audio.getAudioTrack().getInfo().title + "\n");
			count++;
		}
		
		count = DiscordBot.getInstance().getDiscord().getAudioQueue().getQueue().size() - count;
		if (count > 0) {
			embedBuilder.setFooter("and " + count + " more...", null);
		}
		
		embedBuilder.addField("Currently Queued", stringBuilder.toString(), false);
		DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, embedBuilder.build(), true);
	}
	
	@Override
	public String getName() {
		return "Queue";
	}
	
	@Override
	public String getDescription() {
		return "Displays current media in queue.";
	}
	
	@Override
	public String getUsage() {
		return DiscordBot.getInstance().getConfig().getCommandPrefix() + "Queue";
	}
	
	@Override
	public List<String> getAliases() {
		List<String> aliases = new ArrayList<String>();
		aliases.add("List");
		return aliases;
	}
}