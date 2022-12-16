package net.tonimatasdev.perworldcommands.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {

    public static void check() {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=97003").openConnection();

            httpURLConnection.setConnectTimeout(1250);
            httpURLConnection.setReadTimeout(1250);

            String latestVersion = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())).readLine();

            if (latestVersion.length() <= 7 && !PluginDescription.getVersion().equals(latestVersion)) {
                Bukkit.getConsoleSender().sendMessage(PluginDescription.getPrefix() + ChatColor.DARK_RED + " There is a new version available. " + ChatColor.YELLOW + "(" + ChatColor.GRAY + latestVersion + ChatColor.YELLOW + ")");
                Bukkit.getConsoleSender().sendMessage(PluginDescription.getPrefix() + ChatColor.DARK_RED + " You can download it at: " + ChatColor.WHITE + "https://www.spigotmc.org/resources/perworldcommands.97003/");
            }

            httpURLConnection.disconnect();
        } catch (Exception var3) {
            Bukkit.getConsoleSender().sendMessage(PluginDescription.getPrefix() + ChatColor.DARK_RED + " Error while checking update.");
        }
    }
}
