package net.tonimatasdev.perworldcommands.commands;

import net.tonimatasdev.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("perworldcommands.cmd")) {
            if (!(args.length >= 1)) {
                sender.sendMessage(ChatColor.DARK_RED + "[Error]: " + ChatColor.WHITE + "Syntax error. Check the command is completed.");
                return false;
            }

            if (args[0].equalsIgnoreCase("cmd")) {
                if (!(args.length >= 2)) {
                    sender.sendMessage(ChatColor.DARK_RED + "[Error]: " + ChatColor.WHITE + "Syntax error. Check the command is completed.");
                    return false;
                }

                if (args[1].equalsIgnoreCase("set")) {
                    if (!(args.length >= 4)) {
                        sender.sendMessage(ChatColor.DARK_RED + "[Error]: " + ChatColor.WHITE + "Syntax error. Check the command is completed.");
                        return false;
                    }

                    PerWorldCommands.getInstance().getConfig().set("commands." + args[2] + ".allowed-worlds", args[3].split(","));
                    PerWorldCommands.getInstance().saveConfig();
                    PerWorldCommands.getInstance().reloadConfig();
                    sender.sendMessage(ChatColor.DARK_GREEN + "[Successfully]: " + ChatColor.WHITE + "The command " + args[2] + " has been added.");
                }

                if (args[1].equalsIgnoreCase("remove")) {
                    if (!(args.length >= 3)) {
                        sender.sendMessage(ChatColor.DARK_RED + "[Error]: " + ChatColor.WHITE + "Syntax error. Check the command is completed.");
                        return false;
                    }

                    PerWorldCommands.getInstance().getConfig().set("commands." + args[2], null);
                    PerWorldCommands.getInstance().saveConfig();
                    PerWorldCommands.getInstance().reloadConfig();
                    sender.sendMessage(ChatColor.DARK_GREEN + "[Successfully]: " + ChatColor.WHITE + "The command " + args[2] + " has been removed.");
                }
            }

            if (args[0].equalsIgnoreCase("reload")) {
                PerWorldCommands.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.DARK_GREEN + "[Successfully]: " + ChatColor.WHITE + "The plugin has been reloaded");
            }

            if (args[0].equalsIgnoreCase("version")) {
                PerWorldCommands.getInstance().reloadConfig();
                sender.sendMessage(ChatColor.DARK_GREEN + "[Successfully]: " + ChatColor.WHITE + "You are using the version " + PerWorldCommands.getInstance().getDescription().getVersion());
            }
        }

        return true;
    }
}