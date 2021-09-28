package net.tonimatasmc.perworldcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class PerWorldCommands extends JavaPlugin implements Listener {
    private FileConfiguration messages = null;
    private File messagesFile = null;
    public String version;
    public String prefix;

    public void onEnable() {
        this.version = this.getDescription().getVersion();
        this.prefix = this.getDescription().getPrefix();

        this.saveDefaultConfig();
        this.registerMessages();

        Bukkit.getPluginManager().registerEvents(new BlockedCommandEvent(this), this);

        this.getCommand("perworldcommands").setExecutor(new Commands(this));
        this.getCommand("pwc").setExecutor(new Commands(this));
        this.getCommand("perworldcommands").setTabCompleter(new TabulatorCompleter());
        this.getCommand("pwc").setTabCompleter(new TabulatorCompleter());

        int pluginId = 12875;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("", () -> ""));

        //if (this.getConfig().getString("UpdateChecker").equalsIgnoreCase("true")) {
        //    this.updateChecker();
        //}

        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
        Bukkit.getConsoleSender().sendMessage(this.prefix + ChatColor.DARK_GREEN + " The plugin was activated (Version: " + this.version + ")");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_AQUA + "Visit: https://tonimatasmc.com");
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "<---------------------------------------->");
    }

    public boolean Command(String message, Player player) {
        if (this.getConfig().getConfigurationSection("commands." + message.toLowerCase()) != null) {
            String msg = this.getConfig().getString("globalblockmessage");

            if (this.getConfig().getString("commands." + message + ".blockmessage") != null) {
                msg = this.getConfig().getString("commands." + message + ".blockmessage");
            }

            if (this.getConfig().getStringList("commands." + message + ".allowed-worlds").contains(player.getWorld().getName().toLowerCase())) {
                return false;
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("Prefix")+ " " + msg));
                return true;
            }
        } else {
            return false;
        }
    }

    public FileConfiguration getMessages() {
        if (this.messages == null) {
            this.reloadMessages();
        }

        return this.messages;
    }

    public void reloadMessages() {
        if (this.messages == null) {
            this.messagesFile = new File(this.getDataFolder(), "messages.yml");
        }

        this.messages = YamlConfiguration.loadConfiguration(this.messagesFile);
        Reader defConfigStream = new InputStreamReader(this.getResource("messages.yml"), StandardCharsets.UTF_8);
        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
        this.messages.setDefaults(defConfig);
    }

    public void saveMessages() {
        try {
            this.messages.save(this.messagesFile);
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void registerMessages() {
        this.messagesFile = new File(this.getDataFolder(), "messages.yml");
        if (!this.messagesFile.exists()) {
            this.getMessages().options().copyDefaults(true);
            this.saveMessages();
        }

    }
/*
    public void updateChecker() {
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL("https://api.spigotmc.org/legacy/update.php?resource=96185")).openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            this.latestversion = (new BufferedReader(new InputStreamReader(con.getInputStream()))).readLine();
            if (this.latestversion.length() <= 7 && !this.version.equals(this.latestversion)) {
                Bukkit.getConsoleSender().sendMessage(this.prefix + ChatColor.RED + " There is a new version available. " + ChatColor.YELLOW + "(" + ChatColor.GRAY + this.latestversion + ChatColor.YELLOW + ")");
                Bukkit.getConsoleSender().sendMessage(this.prefix + ChatColor.RED + " You can download it at: " + ChatColor.WHITE + "https://www.spigotmc.org/resources/instakillmobsgamemode.96185");
            }
        } catch (Exception var3) {
            Bukkit.getConsoleSender().sendMessage(this.prefix + ChatColor.RED + " Error while checking update.");
        }
    }

 */
}
