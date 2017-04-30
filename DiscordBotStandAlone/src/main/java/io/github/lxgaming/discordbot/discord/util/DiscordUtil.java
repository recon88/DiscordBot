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

package io.github.lxgaming.discordbot.discord.util;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscordUtil {
	
	public static final Color DEFAULT = Color.decode("#7289DA");
	public static final Color SUCCESS = Color.decode("#00AA00");
	public static final Color ERROR = Color.decode("#FF0000");
	
	public static String getTimestamp(long time) {
		return (new SimpleDateFormat("mm'm' ss's'").format(new Date(time)));
	}
}