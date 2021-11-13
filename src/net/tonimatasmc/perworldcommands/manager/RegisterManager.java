package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import net.tonimatasmc.perworldcommands.commands.Commands;
import net.tonimatasmc.perworldcommands.envents.BlockedCommandEvent;
import net.tonimatasmc.perworldcommands.metrics.Metrics;
import net.tonimatasmc.perworldcommands.utils.PluginDescription;
import net.tonimatasmc.perworldcommands.utils.TabulatorCompleter;
import net.tonimatasmc.perworldcommands.utils.UpdateChecker;
import org.bukkit.Bukkit;

public class RegisterManager {
    public static void register() {
        PluginDescription.description();

        ConfigManager.registerMessages();
        MessageManager.registerMessage();

        PerWorldCommands.getPlugin().saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockedCommandEvent(), PerWorldCommands.getPlugin());

        PerWorldCommands.getPlugin().getCommand("perworldcommands").setExecutor(new Commands());
        PerWorldCommands.getPlugin().getCommand("pwc").setExecutor(new Commands());
        PerWorldCommands.getPlugin().getCommand("perworldcommands").setTabCompleter(new TabulatorCompleter());
        PerWorldCommands.getPlugin().getCommand("pwc").setTabCompleter(new TabulatorCompleter());

        int pluginId = 12875;
        Metrics metrics = new Metrics(PerWorldCommands.getPlugin(), pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("", () -> ""));

        if (PerWorldCommands.getPlugin().getConfig().getString("UpdateChecker").equalsIgnoreCase("true")) {
            UpdateChecker.check();
        }
    }
}
