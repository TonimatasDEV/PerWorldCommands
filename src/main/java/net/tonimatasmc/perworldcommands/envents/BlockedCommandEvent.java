package net.tonimatasmc.perworldcommands.envents;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import net.tonimatasmc.perworldcommands.manager.CommandManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class BlockedCommandEvent implements Listener {

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        if (!event.getPlayer().hasPermission("pwc.bypass") || !event.getPlayer().hasPermission("perworldcommands.bypass")) {
            if (PerWorldCommands.getPlugin().getConfig().getBoolean("isworldblacklist")) {
                if (CommandManager.constructorIsBlacklist(event.getMessage().split(" ")[0].replace("/", ""), event.getPlayer())) {
                    event.setCancelled(true);
                }
            } else {
                if (CommandManager.constructorIsWhitelist(event.getMessage().split(" ")[0].replace("/", ""), event.getPlayer())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}

