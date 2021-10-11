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
                    List<String> worldList = new ArrayList<>();
                    worldList.add(args[3]);

                    if (!args[4].isEmpty()) {
                        worldList.add(args[4]);
                    }

                    if (!args[5].isEmpty()) {
                        worldList.add(args[5]);
                    }

                    if (!args[6].isEmpty()) {
                        worldList.add(args[6]);
                    }

                    if (!args[7].isEmpty()) {
                        worldList.add(args[7]);
                    }

                    if (!args[8].isEmpty()) {
                        worldList.add(args[8]);
                    }

                    if (!args[9].isEmpty()) {
                        worldList.add(args[9]);
                    }

                    if (!args[10].isEmpty()) {
                        worldList.add(args[10]);
                    }

                    if (!args[11].isEmpty()) {
                        worldList.add(args[11]);
                    }

                    if (!args[12].isEmpty()) {
                        worldList.add(args[12]);
                    }

                    if (!args[13].isEmpty()) {
                        worldList.add(args[13]);
                    }

                    if (!args[14].isEmpty()) {
                        worldList.add(args[14]);
                    }

                    if (!args[15].isEmpty()) {
                        worldList.add(args[15]);
                    }

                    if (!args[16].isEmpty()) {
                        worldList.add(args[16]);
                    }

                    if (!args[17].isEmpty()) {
                        worldList.add(args[17]);
                    }

                    if (!args[18].isEmpty()) {
                        worldList.add(args[18]);
                    }

                    if (!args[19].isEmpty()) {
                        worldList.add(args[19]);
                    }

                    if (!args[20].isEmpty()) {
                        worldList.add(args[20]);
                    }

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