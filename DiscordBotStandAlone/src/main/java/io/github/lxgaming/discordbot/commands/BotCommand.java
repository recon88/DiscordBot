package io.github.lxgaming.discordbot.commands;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.EnvironmentManager;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class BotCommand {
	
	private static String commandPrefix = DiscordBot.config.getString("CommandPrefix");
	
	public static void bot(TextChannel channel, String command, User author) {
		if (command.equalsIgnoreCase("ping")) {
			channel.sendMessage("Pong!");
		}
		
		if (command.equalsIgnoreCase("botinfo")) {
			channel.sendMessage("DiscordStandAlone, Version " + DiscordBot.dbversion + ", Created by LX_Gaming\nAPI - " + DiscordBot.apiversion + "\nOS - " + EnvironmentManager.getOS() + ", Java - " + EnvironmentManager.getJavaVersion());
		}
		
		if (command.equalsIgnoreCase("restartbot")) {
			if (author.getId().equals("122600245480783874")) {
				channel.sendMessage("Attempting Restart...");
				EnvironmentManager.restartBot();
				return;
			}
			channel.sendMessage("You are not permitted to use this command!");
		}
		
		if (command.equalsIgnoreCase("request")) {
			channel.sendMessage("*https://trello.com/c/DGx9tron* \nComment with your Request!");
		}
		
		if (command.equalsIgnoreCase("website")) {
			channel.sendMessage("*http://lxgaming.github.io/*");
		}
		
		if (command.startsWith("bothelp")) {
			String helpOption = command.substring(7).trim();
			if (helpOption.equalsIgnoreCase("bot")) {
				channel.sendMessage(""
						+ "`" + commandPrefix + "ping`\n" 
						+ "		Ping the bot.\n"
						+ "`" + commandPrefix + "botinfo`\n" 
						+ "		Display bot information.\n"
						+ "`" + commandPrefix + "request`\n" 
						+ "		Request a feature.\n"
						+ "`" + commandPrefix + "website`\n" 
						+ "		Link to LX's website.\n");
				return;
			} else if (helpOption.equalsIgnoreCase("fun")) {
				channel.sendMessage(""
						+ "`" + commandPrefix + "number`\n" 
						+ "		What's your lucky number?\n"
						+ "`" + commandPrefix + "roll`\n" 
						+ "		Roll the dice.\n"
						+ "`" + commandPrefix + "coin`\n" 
						+ "		Flip a coin.\n"
						+ "`" + commandPrefix + "version`\n" 
						+ "		All the versions.\n");
				return;
			} else if (helpOption.equalsIgnoreCase("love")) {
				channel.sendMessage(""
						+ "`" + commandPrefix + "kiss`\n" 
						+ "		Kiss your loved one.\n"
						+ "`" + commandPrefix + "hug`\n" 
						+ "		Embrace another.\n"
						+ "`" + commandPrefix + "slap`\n" 
						+ "		Slap a user!\n"
						+ "`" + commandPrefix + "lick`\n" 
						+ "		Claim it as yours!\n");
				return;
			} else {
				channel.sendMessage(""
						+ "`" + commandPrefix + "bothelp bot`\n" 
						+ "		List bot commands.\n"
						+ "`" + commandPrefix + "bothelp fun`\n" 
						+ "		List fun commands.\n"
						+ "`" + commandPrefix + "bothelp love`\n" 
						+ "		List love commands.\n");
			}
		}
		return;
	}
}