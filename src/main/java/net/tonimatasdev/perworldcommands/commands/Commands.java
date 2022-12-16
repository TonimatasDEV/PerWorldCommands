package net.tonimatasdev.perworldcommands.commands;

import net.tonimatasdev.perworldcommands.PerWorldCommands;
import net.tonimatasdev.perworldcommands.storage.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pwc") || cmd.getName().equalsIgnoreCase("perworldcommands")) {
            if (sender.hasPermission("perworldcommands.cmd") || sender.hasPermission("pwc.cmd")) {
                if (!(args.length >= 1)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("SyntaxError")));
                } else {
                    if (args[0].equalsIgnoreCase("set")) {
                        if (args.length >= 2) {
                            if (args[1].equalsIgnoreCase("cmd")) {
                                List<String> worldList = new ArrayList<>();
                                int number = 3;

                                while (number <= 20) {
                                    if (args.length >= number + 1) {
                                        worldList.add(args[number]);
                                    }

                                    number++;
                                }

                                if (args.length >= 3) {
                                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2] + ".allowed-worlds", worldList);
                                    PerWorldCommands.getPlugin().saveConfig();
                                    PerWorldCommands.getPlugin().reloadConfig();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("AddCommand").replace("%command%", args[2])));
                                } else {
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("SyntaxError")));
                                }
                            }

                            if (args[1].equalsIgnoreCase("msg")) {
                                if (args.length == 3) {
                                    PerWorldCommands.getPlugin().getConfig().set("globalblockmessage", args[2]);
                                    PerWorldCommands.getPlugin().saveConfig();
                                    PerWorldCommands.getPlugin().reloadConfig();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("MessageApplicated").replace("%message%", args[2])));
                                } else {
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("SyntaxError")));
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("SyntaxError")));
                        }
                    }

                    if (args[0].equalsIgnoreCase("remove")) {
                        if (args.length >= 2) {
                            if (args[1].equalsIgnoreCase("cmd")) {
                                if (args.length == 3) {
                                    PerWorldCommands.getPlugin().getConfig().set("commands." + args[2], null);
                                    PerWorldCommands.getPlugin().saveConfig();
                                    PerWorldCommands.getPlugin().reloadConfig();
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("RemoveCommand").replace("%command%", args[2])));
                                } else {
                                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("SyntaxError")));
                                }
                            }
                        } else {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("SyntaxError")));
                        }
                    }

                    if (args[0].equalsIgnoreCase("reload")) {
                        PerWorldCommands.getPlugin().reloadConfig();
                        Messages.reloadMessages();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Messages.getMessages().getString("ReloadPlugin")));
                    }
                }

            }
        }

        return true;
    }
}