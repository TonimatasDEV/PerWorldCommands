package net.tonimatasdev.perworldcommands.envents;

import net.tonimatasdev.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.Objects;

public class CheckCommandEvent implements Listener {

    @EventHandler
    public void PerWorldCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().split(" ")[0].replace("/", "");
        FileConfiguration config = PerWorldCommands.getInstance().getConfig();
        List<String> worlds = config.getStringList("commands." + message + ".allowed-worlds");

        if (event.getPlayer().hasPermission("pwc.bypass")) return;
        if (worlds.isEmpty()) return;

        boolean isDisabled = !worlds.contains(event.getPlayer().getWorld().getName().toLowerCase());

        if (config.getBoolean("isworldblacklist")) {
            isDisabled = !isDisabled;
        }

        if (isDisabled) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("block-message"))));
            event.setCancelled(true);
        }
    }
}

