package me.xiaozhangup.mooncube.chunk;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.List;

public class AutoRemove implements Listener {

    private final List<EntityType> remove = List.of(EntityType.ARROW, EntityType.SNOWBALL, EntityType.DROPPED_ITEM, EntityType.WITHER_SKULL, EntityType.EGG);

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        for (Entity entity : e.getChunk().getEntities()) {
            if (remove.contains(entity.getType())) {
                entity.remove();
            }
        }
    }

}
