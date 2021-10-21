package net.tonimatasmc.perworldcommands.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabulatorCompleter implements org.bukkit.command.TabCompleter {

    private boolean hasPermission(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        } else {
            return sender.hasPermission("perworldcommands.cmd") || sender.hasPermission("pwc.cmd");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (cmd.getName().equalsIgnoreCase("perworldcommands") || cmd.getName().equalsIgnoreCase("pwc")) {
            List<String> argList = new ArrayList<>();
            if (args.length == 1 && hasPermission(sender)) {
                argList.add("remove");
                argList.add("set");
                return argList.stream().filter(a -> a.startsWith(args[0])).collect(Collectors.toList());
            }

            if (args.length == 2 && args[0].equals("remove")) {
                argList.add("msg");
                argList.add("cmd");
                return argList;
            }

            if (args.length == 2 && (args[0].equals("set"))) {
                argList.add("msg");
                argList.add("cmd");
                return argList;
            }

            if ((args.length == 3) && (args[0].equals("set")) && (args[1].equals("cmd"))) {
                argList.add(String.valueOf(Bukkit.getCommandAliases()));
            }

            if ((args.length <= 4) && (args[0].equals("set")) && (args[1].equals("cmd"))) {
                for (World world : Bukkit.getWorlds()) {
                    argList.add(world.getName());
                }return argList;
            }

        }return null;
    }
}
