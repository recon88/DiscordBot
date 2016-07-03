package io.github.lxgaming.discordbot.util;

import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import io.github.lxgaming.discordbot.DiscordBot;

public class DatabaseManager {
	
	public static boolean checkDatabase(String uuid) {
		if (DiscordBot.database.getStringList("DiscordBot.Database").contains(uuid)) {
			return true;
		}
		return false;
	}
	
	public static void addPlayerDatabase(Player player) {
		if (checkDatabase(player.getUniqueId().toString()) == true) {
			List<String> database = DiscordBot.database.getStringList("DiscordBot.Database");
			database.remove(player.getUniqueId().toString());
			DiscordBot.database.set("DiscordBot.Database", database);
			player.sendMessage(ChatColor.GREEN + "DiscordChat On");
		} else {
			List<String> database = DiscordBot.database.getStringList("DiscordBot.Database");
			database.add(player.getUniqueId().toString());
			DiscordBot.database.set("DiscordBot.Database", database);
			player.sendMessage(ChatColor.RED + "DiscordChat Off");
		}
		saveDatabase();
		return;
	}
	
	private static void saveDatabase() {
		try {
			DiscordBot.database.save(DiscordBot.databaseFile);
		} catch (IOException ex) {
			DiscordBot.instance.getLogger().severe("Failed to save database file!");
			ex.printStackTrace();
		}
		return;
	}
}