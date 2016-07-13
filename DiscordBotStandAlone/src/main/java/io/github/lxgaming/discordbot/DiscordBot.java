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
	public static JDA jda;
	public static String dbVersion = "0.7.1 ('Golden Bay')";
	public static String jdaVersion = "JDA v2.1.3 - Recompiled";
	
	public static void main(String[] args) {
		System.out.println("DiscordBot v" + dbVersion);
		System.out.println("API - " + jdaVersion);
		System.out.println("Author - Alex Thomson");
		loadDiscord();
	}
	
	public static void loadDiscord() {
		try {
			jda = new JDABuilder()
					.setBotToken(config.getString("BotToken"))
					.addListener(new BotListener())
					.addListener(new MessageListener())
					.addListener(new UserListener())
					.addListener(new VoiceListener())
					.setAudioEnabled(false)
					.buildAsync();
		} catch (Exception ex) {
			System.out.println("Connection Failed! Invaild BotToken");
			return;
		}
	}
}