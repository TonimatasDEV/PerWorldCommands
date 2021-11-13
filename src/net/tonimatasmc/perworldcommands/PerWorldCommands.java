package net.tonimatasmc.perworldcommands;

import net.tonimatasmc.perworldcommands.manager.RegisterManager;
import net.tonimatasmc.perworldcommands.manager.UnregisterManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PerWorldCommands extends JavaPlugin implements Listener {
    private static PerWorldCommands plugin;

    @Override
    public void onEnable() {
        plugin = this;
        RegisterManager.register();
    }

    @Override
    public void onDisable() {
        UnregisterManager.unregister();
    }

    public static PerWorldCommands getPlugin() {
        return plugin;
    }
}
