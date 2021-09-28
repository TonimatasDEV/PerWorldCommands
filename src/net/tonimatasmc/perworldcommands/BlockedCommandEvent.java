package net.tonimatasmc.perworldcommands;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandEvent implements Listener {
    private final PerWorldCommands plugin;

    public BlockedCommandEvent(PerWorldCommands plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        if (this.plugin.Command(event.getMessage().substring(1), event.getPlayer())) {
            event.setCancelled(true);
        }

    }
}

