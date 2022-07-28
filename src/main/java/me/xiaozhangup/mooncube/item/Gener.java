package me.xiaozhangup.mooncube.item;

import dev.lone.itemsadder.api.CustomBlock;
import me.xiaozhangup.mooncube.gui.tools.INumber;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import java.util.ArrayList;
import java.util.List;

public class Gener implements Listener {

    public static List<String> list = new ArrayList<>();

    public static void load() {
        list.add("iasurvival:aqua_aura_ore");
        list.add("iasurvival:blaze_powder_ore");
        list.add("iasurvival:cassiterite_ore");
        list.add("iasurvival:coal_dirt_ore");
        list.add("iasurvival:dark_amethyst_ore");
        list.add("iasurvival:end_ore");
        list.add("iasurvival:gold_dirt_ore");
        list.add("iasurvival:iron_dirt_ore");
        list.add("iasurvival:ruby_ore");
        list.add("iasurvival:spinel_ore");
        list.add("iasurvival:turquoise_ore");
        list.add("iaalchemy:nether_alchemy_ore");
        list.add("iaalchemy:mysterious_ore");
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBlockRegen(BlockFormEvent e) {
        if (e.getBlock().getWorld().getEnvironment() != World.Environment.NORMAL) return;
        if (e.getNewState().getType() != Material.STONE) return;
        if (INumber.getRandom(1, 12) == 6) {
            e.setCancelled(true);
            CustomBlock.place(list.get(INumber.getRandom(0, list.size() - 1)), e.getBlock().getLocation());
        }
    }

}
