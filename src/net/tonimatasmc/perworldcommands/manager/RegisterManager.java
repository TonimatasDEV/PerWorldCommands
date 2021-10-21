package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
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

        PerWorldCommands.getPlugin().saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new BlockedCommandEvent(), PerWorldCommands.getPlugin());

        PerWorldCommands.getPlugin().getCommand("perworldcommands").setExecutor(new Commands());
        PerWorldCommands.getPlugin().getCommand("pwc").setExecutor(new Commands());
        PerWorldCommands.getPlugin().getCommand("perworldcommands").setTabCompleter(new TabulatorCompleter());
        PerWorldCommands.getPlugin().getCommand("pwc").setTabCompleter(new TabulatorCompleter());

        int pluginId = 12875;
        Metrics metrics = new Metrics(PerWorldCommands.getPlugin(), pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("", () -> ""));

        //if (PerWorldCommands.plugin.getConfig().getString("UpdateChecker").equalsIgnoreCase("true")) {
        //    UpdateChecker.check();
        //}
    }
}
