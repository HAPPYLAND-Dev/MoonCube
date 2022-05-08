package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.mobs.Spawner;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;
    private static Economy econ = null;

    public static Economy getEconomy() {
        return econ;
    }
    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null)
            econ = (Economy)economyProvider.getProvider();
        return (econ != null);
    }

    @Override
    public void onEnable() {
        getLogger().info("MoonCube Version " + Bukkit.getPluginManager().getPlugin("MoonCube").getDescription().getVersion());
        getLogger().info("");
        plugin = this;
        //log print

        Config.loadConfig();
        setupEconomy();
        //config

        Bukkit.getPluginManager().registerEvents(new Spawner(), this);
        //event load
    }

}
