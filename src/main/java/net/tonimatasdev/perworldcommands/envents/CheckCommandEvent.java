package net.tonimatasdev.perworldcommands.envents;

import net.tonimatasdev.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CheckCommandEvent implements Listener {

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().split(" ")[0].replace("/", "");

        if (!event.getPlayer().hasPermission("pwc.bypass") || !event.getPlayer().hasPermission("perworldcommands.bypass")) {
            if (!PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message.toLowerCase()).isEmpty()) {
                if (PerWorldCommands.getPlugin().getConfig().getBoolean("isworldblacklist")) {
                    if (PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(event.getPlayer().getWorld().getName().toLowerCase())) {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', PerWorldCommands.getPlugin().getConfig().getString("PerWorldCommands.globalBlockMessage")));
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(false);
                    }
                } else {
                    if (!PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(event.getPlayer().getWorld().getName().toLowerCase())) {
                        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', PerWorldCommands.getPlugin().getConfig().getString("globalblockmessage")));
                        event.setCancelled(true);
                    } else {
                        event.setCancelled(false);
                    }
                }
            }
        }
    }
}

