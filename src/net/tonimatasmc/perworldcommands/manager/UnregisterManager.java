package net.tonimatasmc.perworldcommands.manager;

import net.tonimatasmc.perworldcommands.PerWorldCommands;

public class UnregisterManager {
    public static void unregister() {
        PerWorldCommands.getPlugin().saveConfig();
        ConfigManager.saveMessages();
        MessageManager.unregisterMessage();
    }
}
