package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import javax.annotation.Nullable;

public class Respawn implements Listener {

    public static String path = "playerdate";

    @Nullable
    public static Location getSet(Player p) {
        return ConfigManager.getConfig(path).getLocation(p.getUniqueId() + ".Respawn");
    }

    public static void setLocation(Player p) {
        ConfigManager.writeConfig(path, p.getUniqueId() + ".Respawn", p.getLocation());
    }

    public static void removeLocation(Player p) {
        ConfigManager.writeConfig(path, p.getUniqueId() + ".Respawn", null);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        Location set = getSet(p);
        if (set != null) {
            e.setRespawnLocation(set);
        }
    }

}
