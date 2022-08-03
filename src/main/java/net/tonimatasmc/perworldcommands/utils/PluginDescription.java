package net.tonimatasmc.perworldcommands.utils;

import net.tonimatasmc.perworldcommands.PerWorldCommands;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoadOrder;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class PluginDescription {
    private static final PluginDescriptionFile pdffile = PerWorldCommands.getPlugin().getDescription();
    private static Map<String, Map<String, Object>> commands;
    private static PermissionDefault permissionDefault;
    private static List<Permission> permissions;
    private static List<String> softDepend;
    private static List<String> loadBefore;
    private static List<String> authors;
    private static PluginLoadOrder load;
    private static List<String> depend;
    private static String description;
    private static String fullName;
    private static String website;
    private static String version;
    private static String prefix;
    private static String main;
    private static String name;


    public static void register() {
        permissionDefault = pdffile.getPermissionDefault();
        permissions = pdffile.getPermissions();
        description = pdffile.getDescription();
        softDepend = pdffile.getSoftDepend();
        loadBefore = pdffile.getLoadBefore();
        commands = pdffile.getCommands();
        fullName = pdffile.getFullName();
        authors = pdffile.getAuthors();
        website = pdffile.getWebsite();
        version = pdffile.getVersion();
        prefix = pdffile.getPrefix();
        depend = pdffile.getDepend();
        name = pdffile.getName();
        main = pdffile.getMain();
        load = pdffile.getLoad();
    }

    public static Map<String, Map<String, Object>> getCommands() {
        return commands;
    }

    public static PermissionDefault getPermissionDefault() {
        return permissionDefault;
    }

    public static List<Permission> getPermissions() {
        return permissions;
    }

    public static List<String> getSoftDepend() {
        return softDepend;
    }

    public static List<String> getLoadBefore() {
        return loadBefore;
    }

    public static List<String> getAuthors() {
        return authors;
    }

    public static PluginLoadOrder getLoad() {
        return load;
    }

    public static List<String> getDepend() {
        return depend;
    }

    public static String getDescription() {
        return description;
    }

    public static String getFullName() {
        return fullName;
    }

    public static String getWebsite() {
        return website;
    }

    public static String getVersion() {
        return version;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getMain() {
        return main;
    }

    public static String getName() {
        return name;
    }
}
