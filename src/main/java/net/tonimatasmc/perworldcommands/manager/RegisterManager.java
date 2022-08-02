package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import net.tonimatasmc.perworldcommands.commands.Commands;
import net.tonimatasmc.perworldcommands.envents.BlockedCommandEvent;
import net.tonimatasmc.perworldcommands.metrics.Metrics;
import net.tonimatasmc.perworldcommands.utils.PluginDescription;
import net.tonimatasmc.perworldcommands.utils.TabulatorCompleter;
import net.tonimatasmc.perworldcommands.utils.UpdateChecker;
import org.bukkit.Bukkit;

import java.util.Objects;

public class RegisterManager {
    public static void register() {
        PluginDescription.register();

        ConfigManager.registerMessages();
        MessageManager.registerMessage();

        PerWorldCommands.getPlugin().saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockedCommandEvent(), PerWorldCommands.getPlugin());

        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("perworldcommands")).setExecutor(new Commands());
        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("pwc")).setExecutor(new Commands());
        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("perworldcommands")).setTabCompleter(new TabulatorCompleter());
        Objects.requireNonNull(PerWorldCommands.getPlugin().getCommand("pwc")).setTabCompleter(new TabulatorCompleter());

        metrics();
        updateChecker();
    }

    public static void metrics() {
        int pluginId = 12875;

        Metrics metrics = new Metrics(PerWorldCommands.getPlugin(), pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("", () -> ""));
    }

    public static void updateChecker() {
        if (Objects.requireNonNull(PerWorldCommands.getPlugin().getConfig().getString("UpdateChecker")).equalsIgnoreCase("true")) {
            UpdateChecker.check();
        }
    }
}
