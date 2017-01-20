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

package io.github.lxgaming.discordbot;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.security.auth.login.LoginException;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;

import io.github.lxgaming.discordbot.commands.Command;
import io.github.lxgaming.discordbot.configuration.Configuration;
import io.github.lxgaming.discordbot.listeners.AudioListener;
import io.github.lxgaming.discordbot.listeners.DiscordBotListener;
import io.github.lxgaming.discordbot.util.Audio;
import io.github.lxgaming.discordbot.util.ConsoleOutput;
import io.github.lxgaming.discordbot.util.DiscordThread;
import io.github.lxgaming.discordbot.util.MessageSender;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class DiscordBot {
	
	private static DiscordBot instance;
	private Configuration configuration;
	private MessageSender messageSender;
	private JDA jda;
	private AudioPlayerManager audioPlayerManager;
	private AudioPlayer audioPlayer;
	private BlockingQueue<Audio> blockingQueue;
	private Command command;
	private DiscordThread discordThread;
	
	public DiscordBot() {
		instance = this;
	}
	
	public void loadDiscordBot() {
		this.configuration = new Configuration();
		this.configuration.loadConfig();
		loadJDA();
	}
	
	public void loadJDA() {
		try {
			this.messageSender = new MessageSender();
			this.discordThread = new DiscordThread();
			this.discordThread.start();
			
			this.jda = new JDABuilder(AccountType.BOT)
					.setToken(DiscordBot.getInstance().getConfiguration().getClient().getToken())
					.addListener(new DiscordBotListener())
					.setAudioEnabled(true)
					.setBulkDeleteSplittingEnabled(false)
					.buildAsync();
			
			this.audioPlayerManager = new DefaultAudioPlayerManager();
			AudioSourceManagers.registerRemoteSources(getAudioPlayerManager());
			this.audioPlayer = getAudioPlayerManager().createPlayer();
			getAudioPlayer().setVolume(DiscordBot.getInstance().getConfiguration().getClient().getDefaultVolume());
			getAudioPlayer().addListener(new AudioListener());
			this.blockingQueue = new LinkedBlockingQueue<Audio>();
			this.command = new Command();
			command.loadCommand();
			ConsoleOutput.info("Successfully loaded MusicBot");
		} catch (IllegalArgumentException | LoginException | RateLimitedException ex) {
			ConsoleOutput.error("Exception loading MusicBot!");
			ex.printStackTrace();
		}
	}
	
	public void stopJDA() {
		if (getJDA() != null) {
			getJDA().shutdown(true);
		}
	}
	
	public static DiscordBot getInstance() {
		return instance;
	}
	
	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	public MessageSender getMessageSender() {
		return this.messageSender;
	}
	
	public JDA getJDA() {
		return this.jda;
	}
	
	public AudioPlayerManager getAudioPlayerManager() {
		return this.audioPlayerManager;
	}
	
	public AudioPlayer getAudioPlayer() {
		return this.audioPlayer;
	}
	
	public BlockingQueue<Audio> getBlockingQueue() {
		return this.blockingQueue;
	}
	
	public Command getCommand() {
		return this.command;
	}
}
