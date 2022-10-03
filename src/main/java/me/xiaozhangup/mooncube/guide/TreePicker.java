package me.xiaozhangup.mooncube.guide;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.ABookHolder;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import me.xiaozhangup.mooncube.player.ProfileEditor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TreePicker implements Listener {

    public static final ItemStack book = IBuilder.buildItem(Material.WRITTEN_BOOK, "&f引导之书", 3, "", "&7这其中记载了服务器的", "&7一些玩法以及游戏指南");

    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new ABookHolder(), 54, IString.addColor("你想要了解哪些部分的内容?"));
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {

            ItemStack board = IBuilder.getBorder(Material.BLACK_STAINED_GLASS_PANE);
            for (int i = 0; i < 9; i++) {
                menu.setItem(i, board);
            }
            ItemStack bg = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
            for (int i = 9; i < 54; i++) {
                menu.setItem(i, bg);
            }

            for (int i = 18; i <= ABook.guide_node.size() + 17; i++) {
                if (ConfigManager.getConfig("playerdate").getIntegerList(p.getUniqueId() + ".Guide").contains(i - 17)) {
                    menu.setItem(i, IBuilder.buildItem(Material.WRITTEN_BOOK, "&f" + ABook.guide_node.get(i - 18), "", "&e> 单击开始阅读"));
                } else {
                    menu.setItem(i, IBuilder.buildItem(Material.BOOK, "&f" + ABook.guide_node.get(i - 18), "", "&e> 单击开始阅读"));
                }
            }

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));

        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof ABookHolder) {
            e.setCancelled(true);
            int slot = e.getRawSlot();
            if (slot >= 18 && slot <= ABook.guide_node.size() + 17) {
                ABook.openBook(slot - 17, p);
            }
        }
    }

}
