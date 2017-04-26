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
import io.github.lxgaming.discordbot.discord.util.DiscordHelper;
import io.github.lxgaming.discordbot.entries.Audio;
import io.github.lxgaming.discordbot.entries.ICommand;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class QueueCommand implements ICommand {
	
	@Override
	public void execute(TextChannel textChannel, Member member, Message message, List<String> arguments) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Currently Queued:\n");
		
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
			stringBuilder.append("and " + count + " more...");
		}
		
		DiscordBot.getInstance().getDiscord().getMessageSender().sendMessage(textChannel, member.getEffectiveName(), stringBuilder.toString());
	}
	
	@Override
	public String getDescription() {
		return null;
	}
}