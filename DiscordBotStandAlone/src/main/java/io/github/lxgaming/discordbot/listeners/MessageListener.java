package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.commands.BotCommand;
import io.github.lxgaming.discordbot.commands.FunCommand;
import io.github.lxgaming.discordbot.commands.LoveCommand;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	private static String commandPrefix = DiscordBot.config.props.getProperty("CommandPrefix");
	
	@Override
	public void onMessageReceived(MessageReceivedEvent MR) {
		TextChannel channel = MR.getTextChannel();
		Message message = MR.getMessage();
		User author = MR.getAuthor();
		String command = message.getContent().substring(commandPrefix.length());
		
		BotCommand.bot(channel, command, author);
		FunCommand.fun(channel, command, author);
		LoveCommand.love(channel, command, author);
		
		System.out.println(author.getUsername() + ": " + message.getContent());
		return;
	}
}