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

package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class BotCommand implements ICommand {
	
	@Override
	public boolean execute(TextChannel textChannel, Member member, Message message) {
		String command = message.getContent().substring(DiscordBot.getInstance().getConfiguration().getClient().getCommandPrefix().length());
		
		if (command.equalsIgnoreCase("djhelp")) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("DJHelp: (CommandPrefix: ``" + DiscordBot.getInstance().getConfiguration().getClient().getCommandPrefix() + "``)\n");
			stringBuilder.append("  Music:``Join <Channel>, Play <URL>, Resume, Pause, Volume [Volume], Skip, Clear, NowPlaying``\n");
			stringBuilder.append("  Bot:``Info``\n");
			stringBuilder.append("  <> = Required Argument, [] = Optional Argument\n");
			DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), stringBuilder.toString().trim());
			return true;
		}
		
		if (command.equalsIgnoreCase("info")) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(DiscordBot.getInstance().getConfiguration().getVersion() + "\n");
			stringBuilder.append("  Author - ``LX_Gaming``\n");
			stringBuilder.append("  Source - ``https://github.com/LXGaming/DiscordBot/``\n");
			stringBuilder.append("  Dependencies:\n");
			stringBuilder.append("    ``" + DiscordBot.getInstance().getConfiguration().getJDAVersion() + "``\n");
			stringBuilder.append("    ``" + DiscordBot.getInstance().getConfiguration().getLavaPlayerVersion() + "``\n");
			DiscordBot.getInstance().getMessageSender().sendMessage(textChannel.getId(), member.getEffectiveName(), member.getNickname(), stringBuilder.toString().trim());
			return true;
		}
		return false;
	}
}
