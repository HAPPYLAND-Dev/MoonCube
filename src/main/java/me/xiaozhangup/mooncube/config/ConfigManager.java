package me.xiaozhangup.mooncube.config;

import me.xiaozhangup.mooncube.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static me.xiaozhangup.mooncube.Main.plugin;

public class ConfigManager {

    public static void createFile(String s) {
        File file = new File(plugin.getDataFolder(), s + ".yml");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileConfiguration getConfig(String s) {
        File file = new File(plugin.getDataFolder(), s + ".yml");
        if (!file.exists()) {
            plugin.saveResource(s, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void writeConfig(String s , String node , String value) {
        FileConfiguration fileConfiguration = getConfig(s);
        fileConfiguration.set(node , value);
        try {
            fileConfiguration.save(new File(Main.plugin.getDataFolder(), s + ".yml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}