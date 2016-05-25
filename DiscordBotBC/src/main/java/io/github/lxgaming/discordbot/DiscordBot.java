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
	
	public static DiscordBot instance;
	public static Configuration config, messages;
	public static JDA api;
	public static String dbversion = "0.5.4 ('East')";
	public static String apiversion = "JDA v2.0.0, Build 242";
	
	@Override
	public void onEnable() {
		instance = this;
		loadConfig();
		getProxy().getPluginManager().registerCommand(this, new DiscordBotCommand());
		getProxy().getPluginManager().registerCommand(this, new DiscordChatCommand());
		getProxy().getPluginManager().registerListener(this, new PlayerEvent());
		loadDiscord();
	}
	
	@Override
	public void onDisable() {
		instance = null;
		if (api != null) {
			api.shutdown(true);
		}
	}
	
	private void loadDiscord() {
		this.getProxy().getScheduler().runAsync(this, new Runnable() {
			@Override
			public void run() {
				try {
					api = new JDABuilder()
							.setBotToken(config.getString("DiscordBot.Credentials.BotToken"))
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
		File configFile = new File(getDataFolder(), "config.yml");
		File messagesFile = new File(getDataFolder(), "messages.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				try (InputStream is = getResourceAsStream("config.yml"); OutputStream os = new FileOutputStream(configFile)) {
					ByteStreams.copy(is, os);
					getLogger().info("Config file created!");
				}
			} catch (IOException ex) {
				getLogger().severe("Unable to create configuration file!");
			}
		}
		
		if (!messagesFile.exists()) {
			try {
				messagesFile.createNewFile();
				try (InputStream is = getResourceAsStream("messages.yml"); OutputStream os = new FileOutputStream(messagesFile)) {
					ByteStreams.copy(is, os);
					getLogger().info("Messages file created!");
				}
			} catch (IOException ex) {
				getLogger().severe("Unable to create messages file!");
			}
		}
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
			messages = ConfigurationProvider.getProvider(YamlConfiguration.class).load(messagesFile);
		} catch (IOException ex) {
			getLogger().severe("Error while loading files!");
		}
		saveConfig();
	}
	
	private void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(messages, new File(getDataFolder(), "messages.yml"));
		} catch (IOException ex) {
			getLogger().severe("Error while saving files!");
		}
	}
}