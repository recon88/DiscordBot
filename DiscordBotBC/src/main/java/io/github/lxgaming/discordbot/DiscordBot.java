package io.github.lxgaming.discordbot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import io.github.lxgaming.discordbot.commands.DiscordBotCommand;
import io.github.lxgaming.discordbot.commands.DiscordChatCommand;
import io.github.lxgaming.discordbot.listeners.ReadyListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class DiscordBot extends Plugin {
	
	public static Configuration config;
	public static DiscordBot instance;
	public static JDA api;
	public static String dbversion = "0.3.4 ('Canterbury')";
	public static String apiversion = "JDA v1.3.0, Build 188";
	
	@Override
	public void onEnable() {
		instance = this;
		loadConfig();
		getProxy().getPluginManager().registerCommand(this, new DiscordBotCommand());
		if (DiscordBot.config.getBoolean("DiscordBot.Listeners.InGameChat") == true) {
			getProxy().getPluginManager().registerCommand(this, new DiscordChatCommand());
		}
		loadDiscord();
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public void loadDiscord() {
		this.getProxy().getScheduler().runAsync(this, new Runnable() {
			@Override
			public void run() {
				try {
					api = new JDABuilder()
							.setEmail(config.getString("DiscordBot.Credentials.Email"))
							.setPassword(config.getString("DiscordBot.Credentials.Password"))
							.addListener(new ReadyListener())
							.buildAsync();
				} catch (Exception ex) {
					ex.printStackTrace();
					getLogger().severe("Connection Failed! Invaild Username/Password");
					getLogger().severe("Please check config file!");
				}
			}
		});
	}
	
	public void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		File configFile = new File(getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				try (InputStream is = getResourceAsStream("config.yml"); OutputStream os = new FileOutputStream(configFile)) {
					ByteStreams.copy(is, os);
					getLogger().info("Config file created!");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
				getLogger().severe("Unable to create configuration file!");
			}
		}
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
		} catch (IOException ex) {
			ex.printStackTrace();
			getLogger().severe("Error while loading config!");
		}
		saveConfig();
	}
	
	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "config.yml"));
		} catch (IOException ex) {
			ex.printStackTrace();
			getLogger().severe("Error while saving config!");
		}
	}
}