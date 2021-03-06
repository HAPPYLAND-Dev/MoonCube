package me.xiaozhangup.mooncube;

import java.util.List;

import static me.xiaozhangup.mooncube.MoonCube.plugin;

public class Config {

    public static String COIN_ACTION;
    public static String COIN_HOLOGRAM;
    public static String COIN_FULL;
    public static Double DAILY_MAX;

    public static List<String> BLACK_ITEMS;

    public static List<String> BLACK_SKILLS_WORLD;

    public static void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();

        COIN_ACTION = plugin.getConfig().getString("CoinActionBar");
        COIN_HOLOGRAM = plugin.getConfig().getString("CoinHologram");
        COIN_FULL = plugin.getConfig().getString("CoinFull");
        BLACK_ITEMS = plugin.getConfig().getStringList("BlackItems");
        BLACK_SKILLS_WORLD = plugin.getConfig().getStringList("BlackSkillsWorld");
        DAILY_MAX = plugin.getConfig().getDouble("DailyMax");
    }

}
