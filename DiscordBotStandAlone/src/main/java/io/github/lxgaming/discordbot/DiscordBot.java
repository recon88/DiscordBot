package io.github.lxgaming.discordbot;

import org.json.JSONObject;

import io.github.lxgaming.discordbot.listeners.BotListener;
import io.github.lxgaming.discordbot.listeners.MessageListener;
import io.github.lxgaming.discordbot.listeners.UserListener;
import io.github.lxgaming.discordbot.listeners.VoiceListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class DiscordBot {
	
	public static JSONObject config = Configuration.loadConfig();
	public static JDA api;
	public static String dbversion = "0.6.1 ('Frost')";
	public static String apiversion = "JDA v1.4.0, Build 238";
	
	public static void main(String[] args) {
		System.out.println("DiscordBot v" + dbversion);
		System.out.println("API - " + apiversion);
		System.out.println("Author - Alex Thomson");
		loadDiscord();
	}
	
	public static void loadDiscord() {
		if (!config.get("BotToken").equals("")) {
			loadBot();
			return;
		}
		
		if (!config.get("Email").equals("") && !config.get("Password").equals("")) {
			loadUser();
			return;
		}
		System.out.println("No Email, Password or BotToken. Check config!");
		return;
	}
	
	public static void loadUser() {
		System.out.println("Loading DiscordBot with User Account!");
		try {
			api = new JDABuilder()
					.setEmail(config.getString("Email"))
					.setPassword(config.getString("Password"))
					.addListener(new BotListener())
					.addListener(new MessageListener())
					.addListener(new UserListener())
					.addListener(new VoiceListener())
					.buildAsync();
		} catch (Exception ex) {
			System.out.println("Connection Failed! Invaild Username/Password");
			return;
		}
	}
	
	public static void loadBot() {
		System.out.println("Loading DiscordBot with BotToken!");
		try {
			api = new JDABuilder()
					.setBotToken(config.getString("BotToken"))
					.addListener(new BotListener())
					.addListener(new MessageListener())
					.addListener(new UserListener())
					.addListener(new VoiceListener())
					.buildAsync();
		} catch (Exception ex) {
			System.out.println("Connection Failed! Invaild BotToken");
			return;
		}
	}
}