package net.tonimatasdev.perworldcommands;

import net.tonimatasdev.perworldcommands.commands.Commands;
import net.tonimatasdev.perworldcommands.envents.CheckCommandEvent;
import net.tonimatasdev.perworldcommands.metrics.Metrics;
import net.tonimatasdev.perworldcommands.utils.TabulatorCompleter;
import net.tonimatasdev.perworldcommands.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class PerWorldCommands extends JavaPlugin implements Listener {
    private static PerWorldCommands instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new CheckCommandEvent(), this);

        Objects.requireNonNull(getCommand("perworldcommands")).setExecutor(new Commands());
        Objects.requireNonNull(getCommand("perworldcommands")).setTabCompleter(new TabulatorCompleter());


        if (getConfig().getBoolean("metrics")) {
            new Metrics(this, 12875);
        }

        if (getConfig().getBoolean("update-checker")) {
            UpdateChecker.check();
        }

        getLogger().info(ChatColor.DARK_GREEN + "<---------------------------------------->");
        getLogger().info(ChatColor.DARK_GREEN + " The plugin has been enabled (Version: " + getDescription().getVersion() + ")");
        getLogger().info(ChatColor.DARK_GREEN + "<---------------------------------------->");
    }

    @Override
    public void onDisable() {
        reloadConfig();
        saveConfig();

        getLogger().info(ChatColor.DARK_RED + "<---------------------------------------->");
        getLogger().info(ChatColor.DARK_RED + " The plugin has been disabled (Version: " + getDescription().getVersion() + ")");
        getLogger().info(ChatColor.DARK_RED + "<---------------------------------------->");
    }

    public static PerWorldCommands getInstance() {
        return instance;
    }
}
