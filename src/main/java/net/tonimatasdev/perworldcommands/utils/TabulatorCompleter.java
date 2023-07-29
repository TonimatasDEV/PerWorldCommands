package net.tonimatasdev.perworldcommands.utils;

import net.tonimatasdev.perworldcommands.PerWorldCommands;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TabulatorCompleter implements org.bukkit.command.TabCompleter {

    private boolean hasPermission(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return true;
        } else {
            return sender.hasPermission("perworldcommands.cmd");
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> argList = new ArrayList<>();
        if (args.length == 1 && hasPermission(sender)) {
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
