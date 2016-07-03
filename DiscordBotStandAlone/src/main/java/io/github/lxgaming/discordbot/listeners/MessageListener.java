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
	
	private static String commandPrefix = DiscordBot.config.getString("CommandPrefix");
	private static String consoleOutput = DiscordBot.config.getString("ConsoleOutput");
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		TextChannel channel = event.getTextChannel();
		Message message = event.getMessage();
		User author = event.getAuthor();
		
		if (author.getId().equals(DiscordBot.jda.getSelfInfo().getId())) {
			return;
		}
		
		if ((message.getContent().startsWith(commandPrefix) || message.getContent().startsWith("/")) && !author.getId().equals(DiscordBot.jda.getSelfInfo().getId())) {
			String command = message.getContent().substring(commandPrefix.length());
			BotCommand.bot(channel, command, author);
			FunCommand.fun(channel, command, author);
			LoveCommand.love(channel, command, author);
		}
		
		if (consoleOutput.equalsIgnoreCase("true")) {
			System.out.println(author.getUsername() + ": " + message.getContent());
		}
		return;
	}
}