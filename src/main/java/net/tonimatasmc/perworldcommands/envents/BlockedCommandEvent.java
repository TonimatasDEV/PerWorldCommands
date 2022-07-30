package net.tonimatasmc.perworldcommands.envents;

import net.tonimatasmc.perworldcommands.manager.CommandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandEvent implements Listener {

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        if (CommandManager.constructor(event.getMessage().split(" ")[0].replace("/", ""), event.getPlayer())) {
            event.setCancelled(true);
        }

    }
}

