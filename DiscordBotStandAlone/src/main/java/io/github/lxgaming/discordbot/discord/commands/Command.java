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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.entries.ICommand;
import io.github.lxgaming.discordbot.util.ConsoleOutput;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class Command {
	
	private HashMap<String, ICommand> registeredCommands;
	
	public Command() {
		registeredCommands = new HashMap<String, ICommand>();
	}
	
	public void registerCommands() {
		getRegisteredCommands().put("clear", new ClearCommand());
		getRegisteredCommands().put("djhelp", new HelpCommand());
		getRegisteredCommands().put("info", new InfoCommand());
		getRegisteredCommands().put("join", new JoinCommand());
		getRegisteredCommands().put("nowplaying", new NowPlayingCommand());
		getRegisteredCommands().put("np", new NowPlayingCommand());
		getRegisteredCommands().put("pause", new PauseCommand());
		getRegisteredCommands().put("play", new PlayCommand());
		getRegisteredCommands().put("queue", new QueueCommand());
		getRegisteredCommands().put("list", new QueueCommand());
		getRegisteredCommands().put("resume", new ResumeCommand());
		getRegisteredCommands().put("skip", new SkipCommand());
		getRegisteredCommands().put("next", new SkipCommand());
		getRegisteredCommands().put("stop", new StopCommand());
		getRegisteredCommands().put("volume", new VolumeCommand());
		getRegisteredCommands().put("vol", new VolumeCommand());
	}
	
	public void execute(TextChannel textChannel, Member member, Message message) {
		List<String> arguments;
		if (message.getContent().startsWith(DiscordBot.getInstance().getConfig().getCommandPrefix())) {
			arguments = new ArrayList<String>(Arrays.asList(message.getContent().substring(DiscordBot.getInstance().getConfig().getCommandPrefix().length()).split(" ")));
		} else {
			arguments = new ArrayList<String>(Arrays.asList(message.getContent().substring(1).split(" ")));
		}
		
		if (arguments == null || arguments.size() < 1) {
			return;
		}
		
		if (getRegisteredCommands().containsKey(arguments.get(0))) {
			ConsoleOutput.debug("Processing Command '" + message.getContent() + "' For '" + member.getEffectiveName() + "'.");
			getRegisteredCommands().get(arguments.remove(0)).execute(textChannel, member, message, arguments);
			DiscordBot.getInstance().getDiscord().getMessageSender().addMessage(message);
		}
	}
	
	public HashMap<String, ICommand> getRegisteredCommands() {
		return registeredCommands;
	}
}