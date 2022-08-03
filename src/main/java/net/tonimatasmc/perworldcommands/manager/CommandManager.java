package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CommandManager {
    public static boolean constructor(String message, Player player) {
        if (PerWorldCommands.getPlugin().getConfig().getConfigurationSection("commands." + message.toLowerCase()) != null) {
            String msg = PerWorldCommands.getPlugin().getConfig().getString("globalblockmessage");

            if (PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(player.getWorld().getName().toLowerCase())) {
                return false;
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PerWorldCommands.getPlugin().getConfig().getString("Prefix") + " " + msg));
                return true;
            }
        } else {
            return false;
        }
    }
}
