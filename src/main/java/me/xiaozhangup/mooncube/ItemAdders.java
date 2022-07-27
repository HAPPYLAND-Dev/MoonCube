package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.gui.tools.IBuilder;
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

    public static ItemStack iabook = IBuilder.buildItem(Material.ENCHANTED_BOOK, "&a额外物品 &7合成书", " ", "&e右键 &8- &7打开合成页面", "&e左键 &8- &7打开Emoji页面");

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack itemStack = e.getItem();
        Action action = e.getAction();
        if (e.getHand() == EquipmentSlot.HAND) {
            if (itemStack != null && itemStack == iabook) {
                if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                    Bukkit.dispatchCommand(p, "iaemoji");
                }
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    Bukkit.dispatchCommand(p, "ia");
                }
            }
        }
    }

}
