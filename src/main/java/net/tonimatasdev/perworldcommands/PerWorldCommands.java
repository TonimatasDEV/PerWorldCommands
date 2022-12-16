package net.tonimatasdev.perworldcommands;

import net.tonimatasdev.perworldcommands.envents.BlockedCommandEvent;
import net.tonimatasdev.perworldcommands.storage.Messages;
import net.tonimatasdev.perworldcommands.utils.UpdateChecker;
import net.tonimatasdev.perworldcommands.commands.Commands;
import net.tonimatasdev.perworldcommands.metrics.Metrics;
import net.tonimatasdev.perworldcommands.utils.PluginDescription;
import net.tonimatasdev.perworldcommands.utils.TabulatorCompleter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class PerWorldCommands extends JavaPlugin implements Listener {
    private static PerWorldCommands plugin;

    public static PerWorldCommands getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;

        PluginDescription.register();
        Messages.registerMessages();
        PerWorldCommands.getPlugin().saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockedCommandEvent(), PerWorldCommands.getPlugin());

        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("perworldcommands")).setExecutor(new Commands());
        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("pwc")).setExecutor(new Commands());
        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("perworldcommands")).setTabCompleter(new TabulatorCompleter());
        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("pwc")).setTabCompleter(new TabulatorCompleter());

        Metrics metrics = new Metrics(PerWorldCommands.getPlugin(), 12875);
        metrics.addCustomChart(new Metrics.SimplePie("", () -> ""));

        if (Objects.requireNonNull(PerWorldCommands.getPlugin().getConfig().getString("UpdateChecker")).equalsIgnoreCase("true")) {
            UpdateChecker.check();
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PluginDescription.getPrefix() + ChatColor.DARK_GREEN + " The plugin has been enabled (Version: " + PluginDescription.getVersion() + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
    }

    @Override
    public void onDisable() {
        PerWorldCommands.getPlugin().reloadConfig();
        PerWorldCommands.getPlugin().saveConfig();

        Messages.reloadMessages();
        Messages.saveMessages();

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(PluginDescription.getPrefix() + ChatColor.DARK_RED + " The plugin has been disabled (Version: " + PluginDescription.getVersion() + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "<---------------------------------------->");
    }
}