package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.*;
import net.tonimatasmc.perworldcommands.commands.Commands;
import net.tonimatasmc.perworldcommands.envents.BlockedCommandEvent;
import net.tonimatasmc.perworldcommands.metrics.Metrics;
import net.tonimatasmc.perworldcommands.utils.TabulatorCompleter;
import org.bukkit.Bukkit;

@SuppressWarnings("CommentedOutCode")
public class RegisterManager {
    public static void register() {
        ConfigManager.registerMessages();
        MessageManager.registerMessage();

        PerWorldCommands.plugin.saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockedCommandEvent(), PerWorldCommands.plugin);

        PerWorldCommands.plugin.getCommand("perworldcommands").setExecutor(new Commands());
        PerWorldCommands.plugin.getCommand("pwc").setExecutor(new Commands());
        PerWorldCommands.plugin.getCommand("perworldcommands").setTabCompleter(new TabulatorCompleter());
        PerWorldCommands.plugin.getCommand("pwc").setTabCompleter(new TabulatorCompleter());

        int pluginId = 12875;
        Metrics metrics = new Metrics(PerWorldCommands.plugin, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("", () -> ""));

        //if (PerWorldCommands.plugin.getConfig().getString("UpdateChecker").equalsIgnoreCase("true")) {
        //    UpdateChecker.check();
        //}
    }
}
