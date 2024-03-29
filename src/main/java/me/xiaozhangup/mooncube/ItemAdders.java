package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.guide.TreePicker;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ItemAdders implements Listener {

    public static ItemStack iabook = IBuilder.buildItem(Material.ENCHANTED_BOOK, "&a额外物品 &7合成书", 1, " ", "&e右键 &8- &7打开合成页面", "&e左键 &8- &7打开Emoji页面");
    public static ItemStack novabook = IBuilder.buildItem(Material.KNOWLEDGE_BOOK, "&2科技线 &7指导书", 2, " ", "&e右键 &8- &7打开合成页面");

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack itemStack = e.getItem();
        Action action = e.getAction();
        if (e.getHand() == EquipmentSlot.HAND) {
            if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == 1) {
                e.setCancelled(true);
                if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                    Bukkit.dispatchCommand(p, "iaemoji");
                }
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    Bukkit.dispatchCommand(p, "ia");
                }
            } else if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == 2) {
                e.setCancelled(true);
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    Bukkit.dispatchCommand(p, "nova items");
                }
            } else if (itemStack != null && itemStack.hasItemMeta() && itemStack.getItemMeta().hasCustomModelData() && itemStack.getItemMeta().getCustomModelData() == 3) {
                e.setCancelled(true);
                TreePicker.open(p);
            }
        }
    }

}
