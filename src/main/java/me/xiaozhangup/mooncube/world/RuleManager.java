package me.xiaozhangup.mooncube.world;

import me.xiaozhangup.mooncube.MoonCube;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class RuleManager implements Listener {

    public static void setAll() {
        for (World world : Bukkit.getWorlds()) {
            world.setGameRule(GameRule.KEEP_INVENTORY, true);
            MoonCube.plugin.getLogger().info("World: " + world.getName() + " Done!");
        }
    }

    @EventHandler
    public void onWorldload(WorldLoadEvent e) {
        e.getWorld().setGameRule(GameRule.KEEP_INVENTORY, true);
        MoonCube.plugin.getLogger().info("World: " + e.getWorld().getName() + " Done!");
    }
}
