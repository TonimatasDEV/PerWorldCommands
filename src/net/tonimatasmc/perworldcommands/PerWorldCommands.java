package net.tonimatasmc.perworldcommands;

import net.tonimatasmc.perworldcommands.manager.RegisterManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class PerWorldCommands extends JavaPlugin implements Listener {
    private static PerWorldCommands plugin;
    public String version;
    public String prefix;

    public void onEnable() {
        this.version = this.getDescription().getVersion();
        this.prefix = this.getDescription().getPrefix();
        plugin = this;
        RegisterManager.register();
    }

    public static PerWorldCommands getPlugin() {
        return plugin;
    }
}
