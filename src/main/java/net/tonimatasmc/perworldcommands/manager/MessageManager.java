package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.utils.PluginDescription;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageManager {
    public static void registerMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PluginDescription.prefix + ChatColor.DARK_GREEN + " The plugin has been enabled (Version: " + PluginDescription.version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
    }

    public static void unregisterMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PluginDescription.prefix + ChatColor.DARK_RED + " The plugin has been disabled (Version: " + PluginDescription.version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "<---------------------------------------->");
    }
}
