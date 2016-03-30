package io.github.lxgaming.discordbot;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

public class Configuration {
	
	File configFile = new File("config.properties");
	public Properties props = new Properties();
	
	public void config() {
		if (!configFile.exists()) {
			saveConfig();
		}
		loadConfig();
	}
	
	public void loadConfig() {
		try {
			FileReader reader = new FileReader(configFile);
			props.load(reader);
			reader.close();
			System.out.println("Loaded config.");
		} catch (Exception ex) {
			System.out.println("Failed to load config!");
		}
	}
	
	public void saveConfig() {
		try {
			props.setProperty("Email", "");
			props.setProperty("Password", "");
			props.setProperty("BotChannel", "");
			props.setProperty("CommandPrefix", "!");
			props.setProperty("ConsoleOutput", "true");
			props.setProperty("UserNameUpdate", "true");
			props.setProperty("UserGameUpdate", "true");
			props.setProperty("UserOnlineStatusUpdate", "true");
			
			FileWriter writer = new FileWriter(configFile);
			props.store(writer, "DiscordBot Config.");
			writer.close();
			System.out.println("Created config.");
		} catch (Exception ex) {
			System.out.println("Failed to save config!");
		}
	}
}