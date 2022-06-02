package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.mobs.Spawner;
import me.xiaozhangup.mooncube.player.Hey;
import me.xiaozhangup.mooncube.player.Join;
import me.xiaozhangup.mooncube.player.ProfileEditer;
import me.xiaozhangup.mooncube.player.tab.TABConfig;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static Plugin plugin;
    private static Economy econ = null;

    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }
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
        Bukkit.getPluginManager().registerEvents(new Hey(), this);
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new ProfileEditer(), this);
        Bukkit.getPluginManager().registerEvents(new TABConfig(), this);
        //event load

        File emodata = new File(plugin.getDataFolder(), "emodata.yml");
        try {
            emodata.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //file

        Bukkit.getPluginCommand("profile").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            ProfileEditer.openProfile(p);
            return true;
        });
        Bukkit.getPluginCommand("tabc").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            TABConfig.openTAB(p);
            return true;
        });
        //command
    }

}
