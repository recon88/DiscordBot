package io.github.lxgaming.discordbot;

import io.github.lxgaming.discordbot.listeners.MessageListener;
import io.github.lxgaming.discordbot.listeners.ReadyListener;
import io.github.lxgaming.discordbot.listeners.UserListener;
import io.github.lxgaming.discordbot.listeners.VoiceListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class DiscordBot {
	
	public static Configuration config = new Configuration();
	public static JDA api;
	public static String dbversion = "0.4.0 ('Dunedin')";
	public static String apiversion = "JDA v1.3.0, Build 188";
	
	public static void main(String[] args) {
		
		System.out.println("DiscordBot");
		System.out.println("Version - " + dbversion);
		System.out.println("API - " + apiversion);
		System.out.println("Author - Alex Thomson");
		System.out.println("Starting...");
		config.config();
		loadDiscord();
	}
	
	public static void loadDiscord() {
		try {
			api = new JDABuilder()
					.setEmail(config.props.getProperty("Email"))
					.setPassword(config.props.getProperty("Password"))
					.addListener(new MessageListener())
					.addListener(new ReadyListener())
					.addListener(new UserListener())
					.addListener(new VoiceListener())
					.buildAsync();
		} catch (Exception ex) {
			System.out.println("Connection Failed! Invaild Username/Password");
			return;
		}
		System.out.println("Successfully started!");
	}
}