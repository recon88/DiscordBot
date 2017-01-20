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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class Command implements ICommand {
	
	private List<ICommand> commands;
	
	public void loadCommand() {
		this.commands = new LinkedList<ICommand>();
		getCommands().add(new BotCommand());
		getCommands().add(new MusicCommand());
	}
	
	@Override
	public boolean execute(TextChannel textChannel, Member member, Message message) {
		if (getCommands() == null) {
			return false;
		}
		
		for (Iterator<ICommand> iterator = getCommands().iterator(); iterator.hasNext();) {
			ICommand command = iterator.next();
			if (command.execute(textChannel, member, message)) {
				return true;
			}
		}
		return false;
	}
	
	public List<ICommand> getCommands() {
		return this.commands;
	}
}
