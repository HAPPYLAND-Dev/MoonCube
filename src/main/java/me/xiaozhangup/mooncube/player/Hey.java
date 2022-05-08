package me.xiaozhangup.mooncube.player;

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


public class Hey implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.PLAYER) return;
        Player ed = (Player) e.getRightClicked();
        Player p = e.getPlayer();
        if (!p.isSneaking()) {
            Inventory iscontrol = Bukkit.createInventory(new IsControl(), 27, Message.Color("&l岛屿邀请 " + ed.getName()));
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                for (int i = 0; i < 27; i++) {
                    iscontrol.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                }


                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(iscontrol);
                });
            });
        } else {
            Inventory profile = Bukkit.createInventory(new HeyProfile(), 54, Message.Color("&l玩家个人资料 " + ed.getName()));
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                for (int i = 0; i < 54; i++) {
                    profile.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                }


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
