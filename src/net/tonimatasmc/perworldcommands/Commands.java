package net.tonimatasmc.perworldcommands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {
    private final PerWorldCommands plugin;

    public Commands(PerWorldCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender.hasPermission("perworldcommands.cmd") || sender.hasPermission("pwc.cmd")) {
            if (args[0].equalsIgnoreCase("set")) {
                if (args[1].equalsIgnoreCase("command") || args[1].equalsIgnoreCase("cmd")) {
                    List<Boolean> worldList = new ArrayList<>();
                    worldList.add(args.length <= 3);
                    this.plugin.getConfig().set("commands." + args[2] + ".allowed-worlds", worldList);
                    this.plugin.getConfig().set("commands." + args[2] + ".blockmessage", "This command is disabled");
                    this.plugin.saveConfig();
                    this.plugin.reloadConfig();
                }

                if (args[1].equalsIgnoreCase("message") || args[1].equalsIgnoreCase("msg")) {
                    this.plugin.getConfig().set("commands." + args[2] + ".blockmessage", "" + (args.length <= 3) + "");
                    this.plugin.saveConfig();
                    this.plugin.reloadConfig();
                    sender.sendMessage(this.plugin.getMessages().getString("").replace('&', '§'));
                }
            }

            if (args[0].equalsIgnoreCase("remove") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("del")) {
                if (args[1].equalsIgnoreCase("command") || args[1].equalsIgnoreCase("cmd")) {
                    this.plugin.getConfig().set("commands." + args[3], "");
                    this.plugin.saveConfig();
                    this.plugin.reloadConfig();
                    sender.sendMessage(this.plugin.getMessages().getString("&2[Success]: &fThe command" + args[3] + " has been removed.").replace('&', '§'));
                }

                if (args[1].equalsIgnoreCase("message") || args[1].equalsIgnoreCase("msg")) {
                    this.plugin.getConfig().set("commands." + args[2] + ".blockmessage", false);
                    this.plugin.saveConfig();
                    this.plugin.reloadConfig();
                    sender.sendMessage(this.plugin.getMessages().getString("&2[Success]: &fThe blocked message " + args[2] + " has been removed.").replace('&', '§'));
                }
            }

            if (args[0].equalsIgnoreCase("reload")) {
                this.plugin.reloadConfig();
                this.plugin.reloadMessages();
            }

        }return true;
    }
}