package io.github.lxgaming.discordmusicbot.listeners;

import io.github.lxgaming.discordmusicbot.DiscordMusicBot;
import io.github.lxgaming.discordmusicbot.commands.AudioCommand;
import io.github.lxgaming.discordmusicbot.commands.BotCommand;
import io.github.lxgaming.discordmusicbot.util.ConsoleOutput;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	private String commandprefix = DiscordMusicBot.config.getString("CommandPrefix");
	private String consoleoutput = DiscordMusicBot.config.getString("ConsoleOutput");
	
	@Override
	public void onMessageReceived(MessageReceivedEvent mr) {
		TextChannel channel = mr.getTextChannel();
		Message message = mr.getMessage();
		User author = mr.getAuthor();
		
		if (mr.isPrivate() || mr.getAuthor().isBot()) {
			return;
		}
		
		if (consoleoutput.equalsIgnoreCase("true")) {
			ConsoleOutput.info("[" + channel.getName() + "] " + author.getUsername() + ": " + message.getContent());
		}
		
		if (message.getContent().startsWith(commandprefix) || message.getContent().startsWith("/")) {
			String command = message.getContent().substring(commandprefix.length());
			BotCommand.bot(channel, command, author);
			AudioCommand.audio(channel, command, author);
		}
		return;
	}
}