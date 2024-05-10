package dev.tonimatas.perworldcommands;

import dev.tonimatas.perworldcommands.commands.Commands;
import dev.tonimatas.perworldcommands.envents.CheckCommandEvent;
import dev.tonimatas.perworldcommands.metrics.Metrics;
import dev.tonimatas.perworldcommands.utils.UpdateChecker;
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
        Objects.requireNonNull(getCommand("perworldcommands")).setTabCompleter(new Commands());


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
