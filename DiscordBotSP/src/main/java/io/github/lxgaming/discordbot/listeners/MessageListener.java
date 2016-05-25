package io.github.lxgaming.discordbot.listeners;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.commands.BotCommand;
import io.github.lxgaming.discordbot.commands.FunCommand;
import io.github.lxgaming.discordbot.commands.LoveCommand;
import io.github.lxgaming.discordbot.commands.ServerCommand;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;
import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	private static String ingameTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.InGame");
	private static String commandPrefix = DiscordBot.config.getString("DiscordBot.Messages.CommandPrefix");
	private static Boolean mainBot = DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot");
	
	@Override
	public void onMessageReceived(MessageReceivedEvent MR) {
		TextChannel channel = MR.getTextChannel();
		Message message = MR.getMessage();
		User author = MR.getAuthor();
		
		if (MR.isPrivate()) {
			return;
		}
		
		if ((message.getContent().startsWith(commandPrefix) || message.getContent().startsWith("/")) && (!author.getId().equals(DiscordBot.api.getSelfInfo().getId()) && mainBot == true)) {
			String command = message.getContent().substring(commandPrefix.length());
			BotCommand.bot(channel, command, author);
			FunCommand.fun(channel, command, author);
			LoveCommand.love(channel, command, author);
			ServerCommand.server(channel, command, author);
			return;
		}
		
		if (channel.getId().equals(ingameTextChannel) && !author.getId().equals(DiscordBot.api.getSelfInfo().getId())) {
			MessageSender.sendMessage(message.getContent(), author.getUsername(), author.getId(), "Message", false, true, true);
			return;
		}
		return;
	}
}