package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.Main;
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
            //TODO
            Main.plugin.getLogger().info("Yep");
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                FileConfiguration configuration = Config.getConfig("emodata.yml");
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
