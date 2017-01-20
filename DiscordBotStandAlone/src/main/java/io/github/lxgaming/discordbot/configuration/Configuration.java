/*
 * Copyright 2017 Alex Thomson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.discordbot.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import io.github.lxgaming.discordbot.DiscordBot;
import io.github.lxgaming.discordbot.util.ConsoleOutput;

public class Configuration {
	
	private final String version = "DiscordBotStandAlone v0.1.0";
	private final String jdaVersion = "JDA v3.0.BETA2_108";
	private final String lavaPlayerVersion = "LavaPlayer v1.1.38";
	
	private File configFile = new File("config.json");
	private Client client;
	private List<Group> groups;
	
	public void loadConfig() {
		try {
			if (!this.configFile.exists()) {
				this.configFile.createNewFile();
				InputStream inputStream = DiscordBot.class.getResourceAsStream("/config.json");
				Files.copy(inputStream, Paths.get(this.configFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
				ConsoleOutput.info("Successfully created configuration file.");
			}
			
			JsonObject config = new JsonParser().parse(new String(Files.readAllBytes(Paths.get(this.configFile.getAbsolutePath())), StandardCharsets.UTF_8)).getAsJsonObject();
			
			this.client = new Gson().fromJson(config.get("client").getAsJsonObject(), Client.class);
			
			List<Group> groups = new LinkedList<Group>();
			for (Iterator<JsonElement> iterator = config.get("groups").getAsJsonArray().iterator(); iterator.hasNext();) {
				Group group = new Gson().fromJson(iterator.next(), Group.class);
				ConsoleOutput.info("Successfully added '" + group.getName() + "'.");
				groups.add(group);
			}
			this.groups = groups;
			
			ConsoleOutput.info("Successfully loaded configuration file.");
		} catch (IllegalStateException | InvalidPathException | IOException | JsonParseException | NullPointerException | OutOfMemoryError | SecurityException | UnsupportedOperationException ex) {
			ConsoleOutput.error("Exception loading configuration file!");
			ex.printStackTrace();
		}
		return;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public String getJDAVersion() {
		return this.jdaVersion;
	}
	
	public String getLavaPlayerVersion() {
		return this.lavaPlayerVersion;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public List<Group> getGroups() {
		return this.groups;
	}
}
