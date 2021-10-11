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
        if (ConfigManager.messages == null) {
            ConfigManager.reloadMessages();
        }
        return ConfigManager.messages;
    }

    public static void reloadMessages() {
        if (ConfigManager.messages == null) {
            ConfigManager.messagesFile = new File(PerWorldCommands.getPlugin().getDataFolder(), "messages.yml");
        }

        ConfigManager.messages = YamlConfiguration.loadConfiguration(ConfigManager.messagesFile);
        Reader defConfigStream = new InputStreamReader(PerWorldCommands.getPlugin().getResource("messages.yml"), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        ConfigManager.messages.setDefaults(defConfig);
    }

    public static void saveMessages() {
        try {
            ConfigManager.messages.save(ConfigManager.messagesFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void registerMessages() {
        ConfigManager.messagesFile = new File(PerWorldCommands.getPlugin().getDataFolder(), "messages.yml");
        if (!ConfigManager.messagesFile.exists()) {
            ConfigManager.getMessages().options().copyDefaults(true);
            ConfigManager.saveMessages();
        }

    }
}
