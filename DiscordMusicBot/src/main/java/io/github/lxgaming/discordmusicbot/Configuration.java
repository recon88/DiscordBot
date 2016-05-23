package io.github.lxgaming.discordmusicbot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class Configuration {
	
	public static File config = new File("config.json");
	
	public static JSONObject loadConfig() {
		if (!config.exists()) {
			saveConfig();
		}
		
		try {
			JSONObject object = new JSONObject(new String(Files.readAllBytes(Paths.get(config.getPath())), "UTF-8"));
			System.out.println("Loaded config.");
			return object;
		} catch (Exception ex) {
			System.out.println("Failed to load config!");
		}
		return null;
	}
	
	private static void saveConfig() {
		try {
			Files.write(Paths.get(config.getPath()), new JSONObject()
					.put("BotToken", "")
					.put("GuildID", "")
					.put("OwnerID", "")
					.put("CommandPrefix", "!")
					.put("ConsoleOutput", "true")
					.put("Debug", "true")
					.toString(4).getBytes());
			System.out.println("Saved config.");
		} catch (Exception ex) {
			System.out.println("Failed to save config!");
		}
		return;
	}
}