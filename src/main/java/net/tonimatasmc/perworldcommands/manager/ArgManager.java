package net.tonimatasmc.perworldcommands.manager;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;

public class ArgManager {
    public static void constructor(String[] args, int number, List<String> argList) {
        if (args.length == number && args[0].equals("set") && args[1].equals("cmd")) {
            for (World world : Bukkit.getWorlds()) {
                argList.add(world.getName());
            }
        }
    }
}
