package io.github.lxgaming.discordmusicbot.commands;

import io.github.lxgaming.discordmusicbot.DiscordMusicBot;
import io.github.lxgaming.discordmusicbot.util.ConsoleOutput;
import io.github.lxgaming.discordmusicbot.util.Environment;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class BotCommand {
	
	private static String commandprefix = DiscordMusicBot.config.getString("CommandPrefix");
	
	public static void bot(TextChannel channel, String command, User author) {
		if (command.equals("ping")) {
			channel.sendMessage("Pong!");
		}
		
		if (command.equals("botinfo")) {
			channel.sendMessage("DiscordStandAlone, Version " + DiscordMusicBot.dmbversion + ", Created by LX_Gaming\nAPI - " + DiscordMusicBot.apiversion + "\nOS - " + Environment.getOSName() + ", Java - " + Environment.getJavaVersion());
		}
		
		if (command.equals("shutdown")) {
			if (author.getId().equals(DiscordMusicBot.config.getString("OwnerID"))) {
				channel.sendMessage("Goodbye...");
				ConsoleOutput.info("Shutting down...");
				DiscordMusicBot.unloadDiscord();
			}
		}
		
		if (command.startsWith("help")) {
			channel.sendMessage(commandprefix + "commands coming soon!");
		}
		return;
	}
}