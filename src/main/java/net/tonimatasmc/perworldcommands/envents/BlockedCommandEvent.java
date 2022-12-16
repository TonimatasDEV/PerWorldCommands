package net.tonimatasmc.perworldcommands.envents;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandEvent implements Listener {

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().split(" ")[0].replace("/", "");
        Player player = event.getPlayer();

        if (!event.getPlayer().hasPermission("pwc.bypass") || !event.getPlayer().hasPermission("perworldcommands.bypass"))
            return;
        if (PerWorldCommands.getPlugin().getConfig().getConfigurationSection("commands." + message.toLowerCase()) != null)
            return;

        if (PerWorldCommands.getPlugin().getConfig().getBoolean("isworldblacklist")) {
            if (!PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(player.getWorld().getName().toLowerCase())) {
                event.setCancelled(false);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PerWorldCommands.getPlugin().getConfig().getString("Prefix") + " " + PerWorldCommands.getPlugin().getConfig().getString("globalblockmessage")));
                event.setCancelled(true);
            }
        } else {
            if (PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(player.getWorld().getName().toLowerCase())) {
                event.setCancelled(false);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PerWorldCommands.getPlugin().getConfig().getString("Prefix") + " " + PerWorldCommands.getPlugin().getConfig().getString("globalblockmessage")));
                event.setCancelled(true);
            }
        }
    }
}

