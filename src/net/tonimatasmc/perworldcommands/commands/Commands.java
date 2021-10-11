package net.tonimatasmc.perworldcommands.commands;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import net.tonimatasmc.perworldcommands.manager.ConfigManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("CommentedOutCode")
public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender.hasPermission("perworldcommands.cmd") || sender.hasPermission("pwc.cmd")) {
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("command") || args[1].equalsIgnoreCase("cmd")) {
                    List<Boolean> worldList = new ArrayList<>();
                    worldList.add(args.length <= 3);
                    PerWorldCommands.plugin.getConfig().set("commands." + args[2] + ".allowed-worlds", "\n" + worldList);
                    PerWorldCommands.plugin.getConfig().set("commands." + args[2] + ".blockmessage", "This command is disabled");
                    PerWorldCommands.plugin.saveConfig();
                    PerWorldCommands.plugin.reloadConfig();
                }

                if (args[1].equalsIgnoreCase("message") || args[1].equalsIgnoreCase("msg")) {
                    PerWorldCommands.plugin.getConfig().set("commands." + args[2] + ".blockmessage", "\"" + (args.length <= 3) + "\"");
                    PerWorldCommands.plugin.saveConfig();
                    PerWorldCommands.plugin.reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("").replace('&', '§'));
                }
            }
/*
            if (args[0].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) {
                if (args[1].equalsIgnoreCase("command") || args[1].equalsIgnoreCase("cmd")) {
                    PerWorldCommands.plugin.getConfig().set("commands." + args[3], "");
                    PerWorldCommands.plugin.saveConfig();
                    PerWorldCommands.plugin.reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("&2[Success]: &fThe command" + args[3] + " has been removed.").replace('&', '§'));
                }

                if (args[1].equalsIgnoreCase("message") || args[1].equalsIgnoreCase("msg")) {
                    PerWorldCommands.plugin.getConfig().set("commands." + args[2] + ".blockmessage", false);
                    PerWorldCommands.plugin.saveConfig();
                    PerWorldCommands.plugin.reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("&2[Success]: &fThe blocked message " + args[2] + " has been removed.").replace('&', '§'));
                }
            }*/

            if (args[0].equalsIgnoreCase("reload")) {
                PerWorldCommands.plugin.reloadConfig();
                ConfigManager.reloadMessages();
            }

        }return true;
    }
}