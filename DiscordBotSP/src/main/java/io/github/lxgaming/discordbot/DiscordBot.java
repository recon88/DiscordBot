package io.github.lxgaming.discordbot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.lxgaming.discordbot.commands.DiscordBotCommand;
import io.github.lxgaming.discordbot.commands.DiscordChatCommand;
import io.github.lxgaming.discordbot.events.PlayerEvent;
import io.github.lxgaming.discordbot.listeners.BotListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class DiscordBot extends JavaPlugin {
	
	public static DiscordBot instance;
	public static FileConfiguration config, database, messages;
	public static File configFile, databaseFile, messagesFile;
	public static JDA jda;
	public static String dbVersion = "0.7.0 ('Golden Bay')";
	public static String jdaVersion = "JDA v2.1.1, Build 300 - Recompiled";
	
	@Override
	public void onEnable() {
		instance = this;
		loadConfig();
		this.getCommand("discordbot").setExecutor(new DiscordBotCommand());
		this.getCommand("discordchat").setExecutor(new DiscordChatCommand());
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerEvent(), this);
		loadDiscord();
		getLogger().info("DiscordBot has started!");
	}
	
	@Override
	public void onDisable() {
		instance = null;
		if (jda != null) {
			jda.shutdown(true);
		}
		getLogger().info("DiscordBot has stopped!");
	}
	
	public void loadDiscord() {
		try {
			jda = new JDABuilder()
					.setBotToken(config.getString("DiscordBot.Credentials.BotToken"))
					.addListener(new BotListener())
					.setAudioEnabled(false)
					.buildAsync();
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Connection Failed! Invaild BotToken");
		}
	}
	
	public void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		configFile = new File(getDataFolder(), "config.yml");
		databaseFile = new File(getDataFolder(), "database.yml");
		messagesFile = new File(getDataFolder(), "messages.yml");
		if (!configFile.exists()) {
			copy(getResource("config.yml"), configFile);
			getLogger().info("Config file created.");
		}
		if (!databaseFile.exists()) {
			copy(getResource("database.yml"), databaseFile);
			getLogger().info("Database file created.");
		}
		if (!messagesFile.exists()) {
			copy(getResource("messages.yml"), messagesFile);
			getLogger().info("Messages file created.");
		}
		config = new YamlConfiguration();
		database = new YamlConfiguration();
		messages = new YamlConfiguration();
		try {
			config.load(configFile);
			database.load(databaseFile);
			messages.load(messagesFile);
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to load files!");
		}
	}
	
	public void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to save files!");
		}
	}
}