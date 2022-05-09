package me.xiaozhangup.mooncube.player;

import me.happylandmc.core.Skull;
import me.happylandmc.core.message.Message;
import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.gui.HeyProfile;
import me.xiaozhangup.mooncube.gui.IsControl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class Hey implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.PLAYER) return;
        Player ed = (Player) e.getRightClicked();
        Player p = e.getPlayer();
        Material material = p.getInventory().getItemInMainHand().getType();
        if (material.toString().endsWith("_POTION") || material == Material.BOW || material == Material.CROSSBOW || material == Material.TRIDENT
                || material == Material.SNOWBALL || material == Material.ENDER_PEARL || material == Material.FISHING_ROD)
            return;
        if (p.isSneaking()) {
            Inventory iscontrol = Bukkit.createInventory(new IsControl(), 27, Message.Color("对于玩家 " + ed.getName() + " 的岛屿选项"));
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta boardMeta = board.getItemMeta();
                boardMeta.setDisplayName("");
                board.setItemMeta(boardMeta);
                for (int i = 0; i < 27; i++) {
                    iscontrol.setItem(i, board);
                }

                iscontrol.setItem(8, Skull.getSkull(ed, "&7" + ed.getName()));

                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(iscontrol);
                });
            });
        } else {
            Inventory profile = Bukkit.createInventory(new HeyProfile(), 54, Message.Color("玩家 " + ed.getName() + " 的个人资料"));
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta boardMeta = board.getItemMeta();
                boardMeta.setDisplayName("");
                board.setItemMeta(boardMeta);
                for (int i = 0; i < 54; i++) {
                    profile.setItem(i, board);
                }

                profile.setItem(16, Skull.getSkull(ed, ed.getName() + "'s Profile", Arrays.asList("Line 1", "Line 2")));

                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(profile);
                });
            });
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof HeyProfile) {
            e.setCancelled(true);
        }
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof IsControl) {
            e.setCancelled(true);
        }
    }
}
