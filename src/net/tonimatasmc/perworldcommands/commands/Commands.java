package net.tonimatasmc.perworldcommands.commands;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import net.tonimatasmc.perworldcommands.manager.ConfigManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"ConstantConditions"})
public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender.hasPermission("perworldcommands.cmd") || sender.hasPermission("pwc.cmd")) {
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("cmd")) {
                    List<String> worldList = new ArrayList<>();
                    worldList.add(args[3]);

                    if (args.length <= 3) {
                        worldList.add(args[4]);
                        if (args.length <= 4) {
                            worldList.add(args[5]);
                            if (args.length <= 5) {
                                worldList.add(args[6]);
                                if (args.length <= 6) {
                                    worldList.add(args[7]);
                                    if (args.length <= 7) {
                                        worldList.add(args[8]);
                                        if (args.length <= 8) {
                                            worldList.add(args[9]);
                                            if (args.length <= 9) {
                                                worldList.add(args[10]);
                                                if (args.length <= 10) {
                                                    worldList.add(args[11]);
                                                    if (args.length <= 11) {
                                                        worldList.add(args[12]);
                                                        if (args.length <= 12) {
                                                            worldList.add(args[13]);
                                                            if (args.length <= 13) {
                                                                worldList.add(args[14]);
                                                                if (args.length <= 14) {
                                                                    worldList.add(args[15]);
                                                                    if (args.length <= 15) {
                                                                        worldList.add(args[16]);
                                                                        if (args.length <= 16) {
                                                                            worldList.add(args[17]);
                                                                            if (args.length <= 17) {
                                                                                worldList.add(args[18]);
                                                                                if (args.length <= 18) {
                                                                                    worldList.add(args[19]);
                                                                                    if (args.length <= 19) {
                                                                                        worldList.add(args[20]);
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2] + ".allowed-worlds", worldList);
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("AddCommand").replace('&', '§').replace("%command%", args[2]));
                }

                if (args[1].equalsIgnoreCase("msg")) {
                    PerWorldCommands.getPlugin().getConfig().set("globalblockmessage", "\"" + (args.length <= 2) + "\"");
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("MessageApplicated").replace('&', '§').replace("%message%","\"" + (args.length <= 2) + "\""));
                }
            }

            if (args[0].equalsIgnoreCase("remove")) {
                if (args[1].equalsIgnoreCase("cmd")) {
                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2], null);
                    PerWorldCommands.getPlugin().saveConfig();
                    PerWorldCommands.getPlugin().reloadConfig();
                    sender.sendMessage(ConfigManager.getMessages().getString("RemoveCommand").replace('&', '§').replace("%command%", args[2]));
                }
            }

            if (args[0].equalsIgnoreCase("reload")) {
                PerWorldCommands.getPlugin().reloadConfig();
                ConfigManager.reloadMessages();
                sender.sendMessage(ConfigManager.getMessages().getString("ReloadPlugin").replace('&', '§'));
            }
        }return true;
    }
}