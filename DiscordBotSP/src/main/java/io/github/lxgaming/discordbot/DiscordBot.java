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
	
	public static DiscordBot INSTANCE;
	public static FileConfiguration CONFIG, MESSAGES;
	public static File CONFIGFILE, MESSAGESFILE;
	public static JDA API;
	public static String DBVERSION = "0.6.0 ('Forest')";
	public static String APIVERSION = "JDA v2.1.0, Build 293 - Recompiled";
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		loadConfig();
		this.getCommand("discordbot").setExecutor(new DiscordBotCommand());
		this.getCommand("discordchat").setExecutor(new DiscordChatCommand());
		this.getCommand("dcc").setExecutor(new DiscordChatCommand());
		PluginManager PM = getServer().getPluginManager();
		PM.registerEvents(new PlayerEvent(), this);
		loadDiscord();
		getLogger().info("DiscordBot has started!");
	}
	
	@Override
	public void onDisable() {
		INSTANCE = null;
		if (API != null) {
			API.shutdown(true);
		}
		getLogger().info("DiscordBot has stopped!");
	}
	
	public void loadDiscord() {
		try {
			API = new JDABuilder()
					.setBotToken(CONFIG.getString("DiscordBot.Credentials.BotToken"))
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
		CONFIGFILE = new File(getDataFolder(), "config.yml");
		MESSAGESFILE = new File(getDataFolder(), "messages.yml");
		if (!CONFIGFILE.exists()) {
			copy(getResource("config.yml"), CONFIGFILE);
			getLogger().info("Config file created.");
		}
		if (!MESSAGESFILE.exists()) {
			copy(getResource("messages.yml"), MESSAGESFILE);
			getLogger().info("Messages file created.");
		}
		CONFIG = new YamlConfiguration();
		MESSAGES = new YamlConfiguration();
		try {
			CONFIG.load(CONFIGFILE);
			MESSAGES.load(MESSAGESFILE);
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to load files!");
		}
	}
	
	public void copy(InputStream IN, File FILE) {
		try {
			OutputStream OUT = new FileOutputStream(FILE);
			byte[] BUF = new byte[1024];
			int LEN;
			while ((LEN = IN.read(BUF)) > 0) {
				OUT.write(BUF, 0, LEN);
			}
			OUT.close();
			IN.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			getLogger().severe("Failed to save files!");
		}
	}
}