package me.xiaozhangup.mooncube;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

import static me.xiaozhangup.mooncube.Main.plugin;

public class Config {

    public static String COIN_ACTION;
    public static String COIN_HOLOGRAM;

    public static List<String> BLACK_ITEMS;

    public static void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        COIN_ACTION = plugin.getConfig().getString("CoinActionBar");
        COIN_HOLOGRAM = plugin.getConfig().getString("CoinHologram");
        BLACK_ITEMS = plugin.getConfig().getStringList("BlackItems");
    }

    public static FileConfiguration getConfig(String yamlFileName) {
        File file = new File(plugin.getDataFolder(), yamlFileName);
        if (!file.exists()) {
            plugin.saveResource(yamlFileName, false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }


}
