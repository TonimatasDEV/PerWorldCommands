package net.tonimatasdev.perworldcommands.envents;

import net.tonimatasdev.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CheckCommandEvent implements Listener {

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().split(" ")[0].replace("/", "");
        Player player = event.getPlayer();

        if (!event.getPlayer().hasPermission("pwc.bypass") || !event.getPlayer().hasPermission("perworldcommands.bypass")) {
            if (PerWorldCommands.getPlugin().getConfig().getConfigurationSection("commands." + message.toLowerCase()) != null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', PerWorldCommands.getPlugin().getConfig().getString("Prefix") + " " + PerWorldCommands.getPlugin().getConfig().getString("globalblockmessage")));

                if (PerWorldCommands.getPlugin().getConfig().getBoolean("isworldblacklist")) {
                    event.setCancelled(PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(player.getWorld().getName().toLowerCase()));
                } else {
                    event.setCancelled(!PerWorldCommands.getPlugin().getConfig().getStringList("commands." + message + ".allowed-worlds").contains(player.getWorld().getName().toLowerCase()));
                }
            }
        }
    }
}

