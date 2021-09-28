package net.tonimatasmc.perworldcommands;

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

            if (args.length == 2 && args[0].equals("remove") || args[0].equals("delete") || args[0].equals("del")) {
                argList.add("message");
                argList.add("msg");
                argList.add("command");
                argList.add("cmd");
                return argList;
            }

            if (args.length == 2 && (args[0].equals("set"))) {
                argList.add("message");
                argList.add("msg");
                argList.add("command");
                argList.add("cmd");
                return argList;
            }

            if (args.length == 3) {
                argList.add("enable");
                argList.add("disable");
                return argList;
            }return argList;
        }return null;
    }
}
