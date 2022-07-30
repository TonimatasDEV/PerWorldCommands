package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;

public class UnregisterManager {
    public static void unregister() {
        PerWorldCommands.getPlugin().reloadConfig();
        PerWorldCommands.getPlugin().saveConfig();
        ConfigManager.reloadMessages();
        ConfigManager.saveMessages();
        MessageManager.unregisterMessage();
    }
}
