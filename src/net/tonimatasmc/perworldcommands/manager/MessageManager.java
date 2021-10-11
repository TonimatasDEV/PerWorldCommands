package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageManager {
    public static void registerMessage() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PerWorldCommands.plugin.prefix + ChatColor.DARK_GREEN + " The plugin was activated (Version: " + PerWorldCommands.plugin.version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "Visit: https://tonimatasmc.com");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
    }
}
