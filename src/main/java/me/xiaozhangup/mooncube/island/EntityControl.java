package me.xiaozhangup.mooncube.island;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class EntityControl implements Listener {

    public static HashMap<Integer , Integer> count = new HashMap<>();

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntityType() != EntityType.VILLAGER) return;
        Location location = e.getLocation();
        if (location.getWorld().getName().startsWith("IridiumSkyblock")) {
            Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(location);
            int landId = island.get().getId();
            count.putIfAbsent(landId , 0);
            if (count.get(landId) >= 10) {
                e.setCancelled(true);
                for (Player p : location.getNearbyPlayers(4)) {
                    p.sendActionBar(IString.addColor("&c所在岛屿村民数量达到上限 10 &f(当前数量为 " + count.get(landId) + ")"));
                }
            } else {
                count.put(landId , count.get(landId) + 1);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntityType() != EntityType.VILLAGER) return;
        Location location = e.getEntity().getLocation();
        if (location.getWorld().getName().startsWith("IridiumSkyblock")) {
            Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(location);
            int landId = island.get().getId();
            count.putIfAbsent(landId, 0);

            count.put(landId, count.get(landId) - 1);
        }
    }

    public static void loadFromFile() {
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin , () -> {
            for (Integer s : ConfigManager.getConfig("landcount").getIntegerList("Lands")) {
                count.put(s , ConfigManager.getConfig("landcount").getInt(s.toString()));
            }
        });
    }

    public static void scanEntity() {
        count.clear();
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().startsWith("IridiumSkyblock")) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getType() != EntityType.VILLAGER) continue;
                    Location location = entity.getLocation();
                    Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(location);
                    int landId = island.get().getId();

                    count.putIfAbsent(landId , 0);

                    count.put(landId , count.get(landId) + 1);
                }
            }
        }
    }

    public static void saveToFile() {
        List<Integer> lands = null;
        count.forEach((landid, number) -> {
            lands.add(landid);
            ConfigManager.writeConfig("landcount", landid.toString(), number);
        });
        ConfigManager.writeConfig("landcount", "Lands", lands);
    }
}
