package io.github.lxgaming.discordbot.util;

import java.io.IOException;
import java.util.List;

import io.github.lxgaming.discordbot.DiscordBot;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class DatabaseManager {
	
	public static boolean checkDatabase(String uuid) {
		if (DiscordBot.database.getStringList("DiscordBot.Database").contains(uuid)) {
			return true;
		}
		return false;
	}
	
	public static void addPlayerDatabase(ProxiedPlayer player) {
		if (checkDatabase(player.getUniqueId().toString()) == true) {
			List<String> database = DiscordBot.database.getStringList("DiscordBot.Database");
			database.remove(player.getUniqueId().toString());
			DiscordBot.database.set("DiscordBot.Database", database);
			player.sendMessage(new ComponentBuilder("DiscordChat On").color(ChatColor.GREEN).create());
		} else {
			List<String> database = DiscordBot.database.getStringList("DiscordBot.Database");
			database.add(player.getUniqueId().toString());
			DiscordBot.database.set("DiscordBot.Database", database);
			player.sendMessage(new ComponentBuilder("DiscordChat Off").color(ChatColor.RED).create());
		}
		saveDatabase();
	}
	
	private static void saveDatabase() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(DiscordBot.database, DiscordBot.databaseFile);
		} catch (IOException ex) {
			DiscordBot.instance.getLogger().severe("Failed to save database file!");
			ex.printStackTrace();
		}
	}
}