package io.github.lxgaming.discordmusicbot.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.github.lxgaming.discordmusicbot.DiscordMusicBot;

public class AudioManager {
	
	private static String[] parameter = new String[2];
	private static String debug = DiscordMusicBot.config.getString("Debug");
	
	private static void setParameters() {
		parameter[0] = null;
		parameter[1] = null;
		if (Environment.getOSName().startsWith("Windows")) {
			parameter[0] = "cmd.exe";
			parameter[1] = "/c";
		} else if (Environment.getOSName().startsWith("Mac")) {
			parameter[0] = "/bin/sh";
			parameter[1] = "-c";
		} else if (Environment.getOSName().startsWith("Linux")) {
			parameter[0] = "/bin/bash";
			parameter[1] = "-c";
		} else {
			ConsoleOutput.error("Unsupported OS!");
			return;
		}
	}
	
	public static Boolean checkDependencies() {
		setParameters();
		String line = null;
		Boolean state = false;
		try {
			ProcessBuilder processbuilder = new ProcessBuilder(parameter[0], parameter[1], "pip", "install", "--upgrade", "youtube_dl");
			processbuilder.redirectErrorStream(true);
			Process process = processbuilder.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = in.readLine()) != null) {
				if (line.startsWith("Successfully installed youtube-dl") || line.startsWith("Requirement already up-to-date: youtube-dl")) {
					state = true;
				}
				if (debug.equals("true")) {
					ConsoleOutput.info(line);
				}
			}
			process.waitFor();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if (state == true) {
			ConsoleOutput.info("pip detected and youtube-dl successfully updated.");
			return true;
		}
		ConsoleOutput.error("Python/PIP was not detected or youtube-dl failed to install!");
		return false;
	}

	public static void getAudioFile(String url) {
		setParameters();
		String line = null;
		String name = null;
		String[] string = null;
		
		if (parameter[0] == null || parameter[1] == null) {
			return;
		}
		
		try {
			ProcessBuilder processbuilder = new ProcessBuilder(parameter[0], parameter[1], "youtube-dl", "-o", "audio\\%(id)s.%(ext)s", url);
			processbuilder.redirectErrorStream(true);
			Process process = processbuilder.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = in.readLine()) != null) {
				if (line.contains("Destination")) {
					string = line.split(" ");
					name = string[2].trim();
					ConsoleOutput.info("Music file name is " + name);
				}
				
				if (line.contains("downloaded")) {
					string = line.split(" ");
					name = string[1].trim();
					ConsoleOutput.info("Music file name is " + name);
				}
				
				if (line.startsWith("[download] 100%")) {
					if (name != null) {
						PlayerManager.play(name);
					}
				}
				if (debug.equals("true")) {
					ConsoleOutput.info(line);
				}
			}
			process.waitFor();
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return;
	}
}