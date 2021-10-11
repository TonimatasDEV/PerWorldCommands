package net.tonimatasmc.perworldcommands.commands;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import net.tonimatasmc.perworldcommands.manager.CommandManager;
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
                    List<String> worldList = new ArrayList<>();
                    worldList.add(args[3]);

                    //if (args[4].equalsIgnoreCase(String.valueOf(Bukkit.getWorlds()))) {
                    //    worldList.add(args[4]);
                    //}

                    CommandManager.argsConstructor(args,4, worldList);
                    CommandManager.argsConstructor(args,5, worldList);
                    CommandManager.argsConstructor(args,6, worldList);
                    CommandManager.argsConstructor(args,7, worldList);
                    CommandManager.argsConstructor(args,8, worldList);
                    CommandManager.argsConstructor(args,9, worldList);
                    CommandManager.argsConstructor(args,10, worldList);
                    CommandManager.argsConstructor(args,11, worldList);
                    CommandManager.argsConstructor(args,12, worldList);
                    CommandManager.argsConstructor(args,13, worldList);
                    CommandManager.argsConstructor(args,14, worldList);
                    CommandManager.argsConstructor(args,15, worldList);
                    CommandManager.argsConstructor(args,16, worldList);
                    CommandManager.argsConstructor(args,17, worldList);
                    CommandManager.argsConstructor(args,18, worldList);
                    CommandManager.argsConstructor(args,19, worldList);
                    CommandManager.argsConstructor(args,20, worldList);

                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2] + ".allowed-worlds", worldList);
                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2] + ".blockmessage", "This command is disabled");
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                }

                if (args[1].equalsIgnoreCase("message") || args[1].equalsIgnoreCase("msg")) {
                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2] + ".blockmessage", "\"" + (args.length <= 3) + "\"");
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("").replace('&', '§'));
                }
            }
/*
            if (args[0].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) {
                if (args[1].equalsIgnoreCase("command") || args[1].equalsIgnoreCase("cmd")) {
                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[3], "");
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("&2[Success]: &fThe command" + args[3] + " has been removed.").replace('&', '§'));
                }

                if (args[1].equalsIgnoreCase("message") || args[1].equalsIgnoreCase("msg")) {
                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2] + ".blockmessage", false);
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("&2[Success]: &fThe blocked message " + args[2] + " has been removed.").replace('&', '§'));
                }
            }*/

            if (args[0].equalsIgnoreCase("reload")) {
                PerWorldCommands.getPlugin().reloadConfig();
                ConfigManager.reloadMessages();
            }

        }return true;
    }
}