package io.github.lxgaming.discordbot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import io.github.lxgaming.discordbot.commands.DiscordBotCommand;
import io.github.lxgaming.discordbot.commands.DiscordChatCommand;
import io.github.lxgaming.discordbot.events.PlayerEvent;
import io.github.lxgaming.discordbot.listeners.BotListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class DiscordBot extends Plugin {
	
	public static DiscordBot INSTANCE;
	public static Configuration CONFIG, MESSAGES;
	public static File CONFIGFILE, MESSAGESFILE;
	public static JDA API;
	public static String DBVERSION = "0.6.1 ('Forest')";
	public static String APIVERSION = "JDA v2.1.0, Build 293 - Recompiled";
	
	@Override
	public void onEnable() {
		INSTANCE = this;
		loadConfig();
		getProxy().getPluginManager().registerCommand(this, new DiscordBotCommand());
		getProxy().getPluginManager().registerCommand(this, new DiscordChatCommand());
		getProxy().getPluginManager().registerListener(this, new PlayerEvent());
		loadDiscord();
	}
	
	@Override
	public void onDisable() {
		INSTANCE = null;
		if (API != null) {
			API.shutdown(true);
		}
	}
	
	private void loadDiscord() {
		this.getProxy().getScheduler().runAsync(this, new Runnable() {
			@Override
			public void run() {
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
		});
	}
	
	private void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		CONFIGFILE = new File(getDataFolder(), "config.yml");
		MESSAGESFILE = new File(getDataFolder(), "messages.yml");
		if (!CONFIGFILE.exists()) {
			try {
				CONFIGFILE.createNewFile();
				try (InputStream IS = getResourceAsStream("config.yml"); OutputStream OS = new FileOutputStream(CONFIGFILE)) {
					ByteStreams.copy(IS, OS);
					getLogger().info("Config file created!");
				}
			} catch (IOException ex) {
				getLogger().severe("Unable to create configuration file!");
			}
		}
		
		if (!MESSAGESFILE.exists()) {
			try {
				MESSAGESFILE.createNewFile();
				try (InputStream IS = getResourceAsStream("messages.yml"); OutputStream OS = new FileOutputStream(MESSAGESFILE)) {
					ByteStreams.copy(IS, OS);
					getLogger().info("Messages file created!");
				}
			} catch (IOException ex) {
				getLogger().severe("Unable to create messages file!");
			}
		}
		try {
			CONFIG = ConfigurationProvider.getProvider(YamlConfiguration.class).load(CONFIGFILE);
			MESSAGES = ConfigurationProvider.getProvider(YamlConfiguration.class).load(MESSAGESFILE);
		} catch (IOException ex) {
			getLogger().severe("Error while loading files!");
		}
		saveConfig();
	}
	
	private void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(CONFIG, CONFIGFILE);
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(MESSAGES, MESSAGESFILE);
		} catch (IOException ex) {
			getLogger().severe("Error while saving files!");
		}
	}
}