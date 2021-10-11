package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ConfigManager {
    public static FileConfiguration messages = null;
    public static File messagesFile = null;

    public static FileConfiguration getMessages() {
        if (messages == null) {
            reloadMessages();
        }

        return ConfigManager.messages;
    }

    public static void reloadMessages() {
        if (ConfigManager.messages == null) {
            messagesFile = new File(PerWorldCommands.plugin.getDataFolder(), "messages.yml");
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream = new InputStreamReader(PerWorldCommands.plugin.getResource("messages.yml"), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        messages.setDefaults(defConfig);
    }

    public static void saveMessages() {
        try {
            messages.save(messagesFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void registerMessages() {
        messagesFile = new File(PerWorldCommands.plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }
}
