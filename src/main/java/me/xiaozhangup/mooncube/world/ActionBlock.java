package me.xiaozhangup.mooncube.world;

import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

public class ActionBlock implements Listener {

    public static HashMap<String, String> mem = new HashMap<>();
    public static FileConfiguration action = ConfigManager.getConfig("action");

    public static void add(Block block, String s) {
        action.set(asString(block), s);
        mem.put(asString(block), s);
    }

    public static void remove(Block block) {
        if (hasAction(block)) {
            action.set(asString(block), null);
            mem.remove(asString(block));
        }
    }

    public static boolean hasAction(Block block) {
        return mem.containsKey(asString(block));
    }

    public static String asString(Block block) {
        Location location = block.getLocation();
        String var1;
        var1 = location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ() + ";" + location.getWorld().getName();
        return var1;
    }

    public static void load() {
        mem.clear();
        action.getKeys(false).forEach(key -> mem.put(key, action.getString(key)));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerClickBlock(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Player p = e.getPlayer();
        Block block = p.getTargetBlock(6);
        if (block == null) {
            return;
        }
        String date = mem.get(asString(block));
        if (date != null) {
            e.setCancelled(true);
            Bukkit.dispatchCommand(p, date);
        }
    }
}
