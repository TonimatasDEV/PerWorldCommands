package dev.tonimatas.perworldcommands.commands;

import dev.tonimatas.perworldcommands.PerWorldCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.*;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
public class Commands implements CommandExecutor, TabCompleter {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> argList = new ArrayList<>();
        if (args.length == 1 && sender.hasPermission("perworldcommands.cmd")) {
            argList.add("cmd");
            argList.add("reload");
            argList.add("version");
            return argList.stream().filter(a -> a.startsWith(args[0])).collect(Collectors.toList());
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("cmd")) {
            argList.add("set");
            argList.add("remove");
            return argList;
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("set")) {
            SimpleCommandMap commandMap;

            try {
                Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                commandMap = (SimpleCommandMap) commandMapField.get(Bukkit.getPluginManager());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (commandMap == null) {
                argList.add("example");
                return argList;
            }

            argList.addAll(commandMap.getCommands().stream().map(Command::getName).collect(Collectors.toList()));
            return argList;
        }

        if (args.length == 3 && args[1].equalsIgnoreCase("remove")) {
            argList.addAll(PerWorldCommands.getInstance().getConfig().getStringList("commands"));
            return argList;
        }

        if (args.length == 4 && args[1].equalsIgnoreCase("set")) {
            for (World world : Bukkit.getWorlds()) {
                String[] split = args[3].replaceAll(",", ",x").split(",");

                if (split.length == 1) {
                    argList.add(world.getName());
                } else {
                    split[split.length - 1] = "";
                    argList.add(String.join(",", split).replaceAll("x", "") + world.getName());
                }
            }

            return argList;
        }

        return argList;
    }
}