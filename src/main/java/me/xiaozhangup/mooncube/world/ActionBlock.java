package me.xiaozhangup.mooncube.world;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import me.xiaozhangup.mooncube.player.Crate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

import static me.xiaozhangup.mooncube.guide.ABook.mm;

public class ActionBlock implements Listener {

    public static HashMap<String, String> mem = new HashMap<>();

    public static void add(Block block, String s) {
        ConfigManager.writeConfig("action", asString(block), s);
        mem.put(asString(block), s);
    }

    public static void remove(Block block) {
        if (hasAction(block)) {
            ConfigManager.writeConfig("action", asString(block), null);
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
        ConfigManager.getConfig("action").getKeys(false).forEach(key -> mem.put(key, ConfigManager.getConfig("action").getString(key)));
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
        if (date != null && !date.startsWith(":")) {
            e.setCancelled(true);
            Bukkit.dispatchCommand(p, date);
        } else if (date != null && date.startsWith(":")) {
            switch (date) {
                case ":crate" -> {
                    if (MoonCube.getEconomy().getBalance(p) <= 420) {
                        p.sendMessage(mm.deserialize("<dark_gray>[<color:#cff24e>抽金</color>]</dark_gray> <red>为了保护您的资产,需要账号至少拥有420才能抽奖</red>"));
                        return;
                    }
                    Crate.open(p);
                }
            }
        }
    }
}
