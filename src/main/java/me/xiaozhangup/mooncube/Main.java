package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.mobs.Spawner;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        getLogger().info("MoonCube Version " + Bukkit.getPluginManager().getPlugin("MoonCube").getDescription().getVersion());
        getLogger().info("");
        plugin = this;
        //log print

        Config.loadConfig();
        //config

        Bukkit.getPluginManager().registerEvents(new Spawner(), this);
        //event load
    }

}
