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
	
	private static String INGAMETEXTCHANNEL = DiscordBot.CONFIG.getString("DiscordBot.TextChannels.InGame");
	private static String COMMANDPREFIX = DiscordBot.CONFIG.getString("DiscordBot.Messages.CommandPrefix");
	private static Boolean MAINBOT = DiscordBot.CONFIG.getBoolean("DiscordBot.Listeners.MainBot");
	
	@Override
	public void onMessageReceived(MessageReceivedEvent MR) {
		TextChannel CHANNEL = MR.getTextChannel();
		Message MESSAGE = MR.getMessage();
		User AUTHOR = MR.getAuthor();
		
		if (MR.isPrivate()) {
			return;
		}
		
		if ((MESSAGE.getContent().startsWith(COMMANDPREFIX) || MESSAGE.getContent().startsWith("/")) && (!AUTHOR.getId().equals(DiscordBot.API.getSelfInfo().getId()) && MAINBOT == true)) {
			String COMMAND = MESSAGE.getContent().substring(COMMANDPREFIX.length());
			BotCommand.bot(CHANNEL, COMMAND, AUTHOR);
			FunCommand.fun(CHANNEL, COMMAND, AUTHOR);
			LoveCommand.love(CHANNEL, COMMAND, AUTHOR);
			ServerCommand.server(CHANNEL, COMMAND, AUTHOR);
			return;
		}
		
		if (CHANNEL.getId().equals(INGAMETEXTCHANNEL) && !AUTHOR.getId().equals(DiscordBot.API.getSelfInfo().getId())) {
			MessageSender.sendMessage(MESSAGE.getContent(), AUTHOR.getUsername(), AUTHOR.getId(), "Message", false, true, true);
			return;
		}
		return;
	}
}