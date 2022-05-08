package me.xiaozhangup.mooncube;

public class Config {

    public static String COIN_ACTION;
    public static String COIN_HOLOGRAM;

    public static void loadConfig() {
        Main.plugin.saveDefaultConfig();
        Main.plugin.reloadConfig();

        COIN_ACTION = Main.plugin.getConfig().getString("CoinActionBar");
        COIN_HOLOGRAM = Main.plugin.getConfig().getString("CoinHologram");
    }
}
