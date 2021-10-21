package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageManager {
    public static void registerMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PerWorldCommands.getPlugin().prefix + ChatColor.DARK_GREEN + " The plugin has been enabled (Version: " + PerWorldCommands.getPlugin().version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "Visit: " + ChatColor.DARK_BLUE + "https://tonimatasmc.com");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
    }

    public static void unregisterMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PerWorldCommands.getPlugin().prefix + ChatColor.DARK_RED + " The plugin has been disabled (Version: " + PerWorldCommands.getPlugin().version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Visit: " + ChatColor.DARK_BLUE + "https://tonimatasmc.com");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "<---------------------------------------->");
    }
}
