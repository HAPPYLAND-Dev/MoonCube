package me.xiaozhangup.mooncube.world;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.api.IridiumSkyblockAPI;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.WorldLoadEvent;

import static me.xiaozhangup.mooncube.guide.ABook.mm;

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

    @EventHandler
    public void on(BlockDispenseEvent e) {
        if (e.getBlock().getChunk().getEntities().length > 16) e.setCancelled(true);
    }

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock() != null && e.getItem() != null) {
            if (e.getClickedBlock().getType() == Material.STRUCTURE_VOID && e.getItem().getType() != Material.SHULKER_SHELL) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void on(BlockFormEvent e) {
        if (e.getNewState().getType() == Material.OBSIDIAN) {
            e.getNewState().getLocation().getNearbyPlayers(3).forEach(player -> {
                var land = IridiumSkyblockAPI.getInstance().getIslandViaLocation(player.getLocation());
                if (land.isPresent() && land.get().getLevel() <= 1) {
                    player.sendMessage(IString.addColor("&8[&x&0&9&C&6&F&9空岛&8] &c使用铁桶右键黑曜石将其变回岩浆!"));
                }
            });
        }
    }
}
