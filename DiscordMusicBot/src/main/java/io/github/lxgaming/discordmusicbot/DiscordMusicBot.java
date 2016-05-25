package io.github.lxgaming.discordmusicbot;

import org.json.JSONObject;

import io.github.lxgaming.discordmusicbot.listeners.BotListener;
import io.github.lxgaming.discordmusicbot.listeners.MessageListener;
import io.github.lxgaming.discordmusicbot.util.AudioManager;
import io.github.lxgaming.discordmusicbot.util.ConsoleOutput;
import io.github.lxgaming.discordmusicbot.util.Environment;
import io.github.lxgaming.discordmusicbot.util.ThreadManager;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;

public class DiscordMusicBot {
	
	public static JSONObject config = Configuration.loadConfig();
	public static JDA api;
	public static String dmbversion = "0.0.2-EARLY_ALPHA";
	public static String apiversion = "JDA v2.0.0, Build 242";

	public static void main(String[] args) {
		ConsoleOutput.info("DiscordMusicBot v" + dmbversion);
		ConsoleOutput.info("API - " + apiversion);
		ConsoleOutput.info("Author - Alex Thomson");
		ConsoleOutput.info("Website - http://lxgaming.github.io");
		ConsoleOutput.info(Environment.getJavaVendor() + " - " + Environment.getJavaVersion());
		ConsoleOutput.info(Environment.getOSName() + " - " + Environment.getOSVersion() + " - " + Environment.getOSArch());
		if (AudioManager.checkDependencies() != true) {
			System.exit(1);
		}
		
		ThreadManager.Thread();
		loadDiscord();
	}
	
	public static void loadDiscord() {
		try {
			api = new JDABuilder()
					.addListener(new BotListener())
					.addListener(new MessageListener())
					.setBotToken(config.getString("BotToken"))
					.buildAsync();
		} catch (Exception ex) {
			ConsoleOutput.error("Connection Failed - Invalid BotToken!");
			return;
		}
	}
	
	public static void unloadDiscord() {
		if (api != null) {
			api.shutdown();
		}
		System.exit(0);
	}
}