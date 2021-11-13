package net.tonimatasmc.perworldcommands.utils;

import net.tonimatasmc.perworldcommands.manager.ArgManager;
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

            if (args.length == 2 && args[0].equalsIgnoreCase("remove")) {
                argList.add("cmd");
                return argList;
            }

            if (args.length == 2 && (args[0].equalsIgnoreCase("set"))) {
                //argList.add("msg");
                argList.add("cmd");
                return argList;
            }

            if (args.length == 3 && args[1].equalsIgnoreCase("cmd")) {
                argList.add("example");
                return argList;
            }

            if (args.length == 4 && args[0].equalsIgnoreCase("set") && args[1].equalsIgnoreCase("cmd") && !args[2].isEmpty()) {
                for (World world : Bukkit.getWorlds()) {
                    argList.add(world.getName());
                }return argList;
            }

            ArgManager.constructor(args,5, argList);
            ArgManager.constructor(args,6, argList);
            ArgManager.constructor(args,7, argList);
            ArgManager.constructor(args,8, argList);
            ArgManager.constructor(args,9, argList);
            ArgManager.constructor(args,10, argList);
            ArgManager.constructor(args,11, argList);
            ArgManager.constructor(args,12, argList);
            ArgManager.constructor(args,13, argList);
            ArgManager.constructor(args,14, argList);
            ArgManager.constructor(args,15, argList);
            ArgManager.constructor(args,16, argList);
            ArgManager.constructor(args,17, argList);
            ArgManager.constructor(args,18, argList);
            ArgManager.constructor(args,19, argList);
            ArgManager.constructor(args,20, argList);
            return argList;


        }return null;
    }
}
