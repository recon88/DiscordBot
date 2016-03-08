package io.github.lxgaming.discordbot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
	
	String ingameTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.InGame");
	String commandPrefix = DiscordBot.config.getString("DiscordBot.Messages.CommandPrefix");
	Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	Boolean inGameChat = DiscordBot.config.getBoolean("DiscordBot.Listeners.InGameChat");
	Boolean mainBot = DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot");
	String botID = DiscordBot.api.getSelfInfo().getId();
	BotCommand BC = new BotCommand();
	FunCommand FC = new FunCommand();
	LoveCommand LC = new LoveCommand();
	
	@Override
	public void onMessageReceived(MessageReceivedEvent MR) {
		TextChannel channel = MR.getTextChannel();
		Message message = MR.getMessage();
		User author = MR.getAuthor();
		
		if ((message.getContent().startsWith(commandPrefix) || message.getContent().startsWith("/")) && (!author.getId().equals(botID) && mainBot == true)) {
			String command = message.getContent().substring(commandPrefix.length());
			BC.Bot(channel, command, author);
			FC.Fun(channel, command, author);
			LC.Love(channel, command, author);
		} else {
			if (channel.getId().equals(ingameTextChannel) && !author.getId().equals(botID) && inGameChat == true) {
				for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					if (onlinePlayer.hasPermission("DiscordBot.Chat")) {
						onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', ingameFormat).replace("%author%", MR.getAuthor().getUsername()) + ChatColor.translateAlternateColorCodes('&', " " + message.getContent()));
					}
				}
				if (consoleOutput == true) {
					DiscordBot.instance.getLogger().info(author.getUsername() + ": " + message.getContent());
				}
				return;
			}
		}
		return;
	}
}