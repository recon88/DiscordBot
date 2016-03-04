package io.github.lxgaming.discordbot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class ExampleUtils {
	
    public static JSONObject getConfig() {
        File config = new File("config.json");
        if (!config.exists()) {
            try {
                Files.write(Paths.get(config.getPath()),
                        new JSONObject()
                                .put("email", "")
                                .put("password", "")
                                .put("proxyHost", "")
                                .put("proxyPort", 8080)
                                .put("version", 2)
                                .put("textchannelid", "")
                                .toString(4).getBytes());
                System.out.println("config.json created. Populate with login information.");
                System.exit(0);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            JSONObject object = new JSONObject(new String(Files.readAllBytes(Paths.get(config.getPath())), "UTF-8"));
            if (!object.has("version")) {
                object.put("version", 1);
            }
            switch(object.getInt("version")) {
                case 1:
                    object
                        .put("proxyHost", "")
                        .put("proxyPort", 8080);
                    object.put("version", 2);
                    Files.write(Paths.get(config.getPath()), object.toString(4).getBytes());
                    break;
                default:
            }
            return object;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}