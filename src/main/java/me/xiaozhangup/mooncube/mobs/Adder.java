package me.xiaozhangup.mooncube.mobs;

import me.xiaozhangup.mooncube.gui.tools.INumber;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class Adder implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onEndermanSpawn(CreatureSpawnEvent e) {
        if (e.getEntity() instanceof Enderman && e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
            Location location = e.getLocation();
            if (location.getWorld().getEnvironment() == World.Environment.THE_END && INumber.getRandom(1, 5) == 2) {
                location.setY(location.getY() + 1);
                location.getWorld().spawnEntity(location, EntityType.SHULKER, CreatureSpawnEvent.SpawnReason.NATURAL);
            }
        }
    }
}
