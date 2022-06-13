package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.config.ConfigManager;
import me.xiaozhangup.mooncube.mobs.Spawner;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class Join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPlayedBefore()) {
            Spawner.dailyCoin.putIfAbsent(e.getPlayer(), 0.0);
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                FileConfiguration configuration = ConfigManager.getConfig("emodata");
                configuration.set(e.getPlayer().getName() + ".emobase", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjViOTVkYTEyODE2NDJkYWE1ZDAyMmFkYmQzZTdjYjY5ZGMwOTQyYzgxY2Q2M2JlOWMzODU3ZDIyMmUxYzhkOSJ9fX0=");
                try {
                    configuration.save(new File(Main.plugin.getDataFolder(), "emodata.yml"));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }

            });
        }
    }
}
