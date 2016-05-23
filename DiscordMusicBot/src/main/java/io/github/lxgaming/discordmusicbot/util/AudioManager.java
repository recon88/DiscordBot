package io.github.lxgaming.discordmusicbot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AudioManager {

	private static File resource = new File("youtube-dl.exe");

	public static void getResource() {
		if (!resource.exists()) {
			ConsoleOutput.info("Resource does not exist, Downloading...");
			try {
				URL url = new URL("https://yt-dl.org/downloads/2016.05.21.2/youtube-dl.exe");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
					ConsoleOutput.error("Response Code Error!");
					return;
				}
				InputStream in = connection.getInputStream();
				FileOutputStream out = new FileOutputStream(resource);
				int bytesread = -1;
				byte[] buf = new byte[1024];
				while ((bytesread = in.read(buf)) != -1) {
					out.write(buf, 0, bytesread);
				}
				out.close();
				in.close();
			} catch (Exception ex) {
				ConsoleOutput.error("Error while downloading resources");
				ex.printStackTrace();
				return;
			}
			ConsoleOutput.info("Resource Downloaded.");
			return;
		}
		ConsoleOutput.info("Resource Exists!");
		return;
	}

	public static void getAudioFile(String url) {
		String line = null;
		String name = null;
		String[] string = null;
		try {
			ProcessBuilder processbuilder = new ProcessBuilder("cmd.exe", "/c", "youtube-dl.exe -o audio\\%(id)s.%(ext)s " + url);
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
				ConsoleOutput.info(line);
			}
			process.waitFor();
			in.close();
			return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}