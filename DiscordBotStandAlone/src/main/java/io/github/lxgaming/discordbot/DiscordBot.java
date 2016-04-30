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
	public static String dbversion = "0.5.0 ('East')";
	public static String apiversion = "JDA v2.0.0, Build 242";
	
	public static void main(String[] args) {
		System.out.println("DiscordBot v" + dbversion);
		System.out.println("API - " + apiversion);
		System.out.println("Author - Alex Thomson");
		loadDiscord();
	}
	
	public static void loadDiscord() {
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