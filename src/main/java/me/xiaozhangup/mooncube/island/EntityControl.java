package me.xiaozhangup.mooncube.island;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.managers.IslandManager;
import me.xiaozhangup.mooncube.gui.tools.IString;
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

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class EntityControl implements Listener {

    public static ConcurrentHashMap<Integer, Integer> villagerCountMap = new ConcurrentHashMap<>();

    private static IslandManager islandManager;
    private static final Integer amount = 20;


    public EntityControl() {
        try {
            islandManager = IridiumSkyblock.getInstance().getIslandManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void scanEntity() {
        villagerCountMap.clear();
        for (World world : Bukkit.getWorlds()) {
            if (world.getName().startsWith("IridiumSkyblock")) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getType() != EntityType.VILLAGER) continue;
                    Location location = entity.getLocation();
                    Optional<Island> island = islandManager.getIslandViaLocation(location);
                    if (island.isEmpty()) continue;
                    int islandId = island.get().getId();

                    int cnt = villagerCountMap.getOrDefault(islandId, 0);
                    villagerCountMap.put(islandId, cnt + 1);
                }
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntityType() != EntityType.VILLAGER) return;
        Location location = e.getLocation();
        if (location.getWorld().getName().startsWith("IridiumSkyblock")) {
            Optional<Island> island = islandManager.getIslandViaLocation(location);
            if (island.isEmpty()) return;
            int islandId = island.get().getId();

            int cnt = villagerCountMap.getOrDefault(islandId, 0);
            if (cnt >= amount) {
                e.setCancelled(true);
                for (Player p : location.getNearbyPlayers(4)) {
                    p.sendActionBar(IString.addColor("&c???????????????????????????????????? " + amount + " &f(??????????????? " + cnt + " )"));
                }
            } else {
                villagerCountMap.put(islandId, cnt + 1);
            }
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        if (e.getEntityType() != EntityType.VILLAGER) return;
        Location location = e.getEntity().getLocation();
        if (location.getWorld().getName().startsWith("IridiumSkyblock")) {
            Optional<Island> island = IridiumSkyblock.getInstance().getIslandManager().getIslandViaLocation(location);
            if (island.isEmpty()) return;
            int islandId = island.get().getId();

            int cnt = villagerCountMap.getOrDefault(islandId, 0);
            if (cnt > 0) villagerCountMap.put(islandId, cnt - 1);
        }
    }
}
