package io.github.lxgaming.discordbot;

import javax.security.auth.login.LoginException;

import org.json.JSONObject;

import io.github.lxgaming.discordbot.listeners.MessageListener;
import io.github.lxgaming.discordbot.listeners.ReadyListener;
import io.github.lxgaming.discordbot.listeners.UserListener;
import io.github.lxgaming.discordbot.listeners.VoiceListener;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class DiscordBot {
	
	static JSONObject config = ExampleUtils.getConfig();
	public static JDA api;
	
	public static void main(String[] args) {
		System.out.println("Welcome to Discord Bot.");
		System.out.println("Created by Alex Thomson.");
		System.out.println("Discord Bot is Starting...");
			try {
				api = new JDABuilder()
						.setEmail(config.getString("email"))
						.setPassword(config.getString("password"))
						.addListener(new UserListener())
						.addListener(new MessageListener())
						.addListener(new ReadyListener())
						.addListener(new VoiceListener())
						.buildAsync();
			} catch (LoginException e) {
				e.printStackTrace();
				System.out.println("The provided email / password combination was incorrect. Please provide valid details.");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				System.out.println("The config was not populated. Please enter an email and password.");
			}
	}
}