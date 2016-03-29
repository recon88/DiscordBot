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
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MessageListener extends ListenerAdapter {
	
	private static String ingameTextChannel = DiscordBot.config.getString("DiscordBot.TextChannels.InGame");
	private static String commandPrefix = DiscordBot.config.getString("DiscordBot.Messages.CommandPrefix");
	private static Boolean consoleOutput = DiscordBot.config.getBoolean("DiscordBot.Messages.ConsoleOutput");
	private static String ingameFormat = DiscordBot.config.getString("DiscordBot.Messages.InGameFormat");
	private static Boolean inGameChat = DiscordBot.config.getBoolean("DiscordBot.Messages.InGameChat");
	private static Boolean mainBot = DiscordBot.config.getBoolean("DiscordBot.Listeners.MainBot");
	private static String botID = DiscordBot.api.getSelfInfo().getId();
	private static BotCommand BC = new BotCommand();
	private static FunCommand FC = new FunCommand();
	private static LoveCommand LC = new LoveCommand();
	
	@Override
	public void onMessageReceived(MessageReceivedEvent MR) {
		TextChannel channel = MR.getTextChannel();
		Message message = MR.getMessage();
		User author = MR.getAuthor();
		
		if (MR.isPrivate()) {
			return;
		}
		
		if ((message.getContent().startsWith(commandPrefix) || message.getContent().startsWith("/")) && (!author.getId().equals(botID) && mainBot == true)) {
			String command = message.getContent().substring(commandPrefix.length());
			BC.Bot(channel, command, author);
			FC.Fun(channel, command, author);
			LC.Love(channel, command, author);
			return;
		}
		
		if ((channel.getId().equals(ingameTextChannel) && inGameChat == true) && (!author.getId().equals(botID))) {
			for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
				if (player.hasPermission("DiscordBot.Chat")) {
					player.sendMessage(new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', ingameFormat).replace("%author%", MR.getAuthor().getUsername())).append(ChatColor.translateAlternateColorCodes('&', " " + message.getContent())).create());
				}
			}
			
			if (consoleOutput == true) {
				DiscordBot.instance.getLogger().info(author.getUsername() + ": " + message.getContent());
			}
		}
		return;
	}
}