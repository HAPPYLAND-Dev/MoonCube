package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.Unique;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UniqueShop implements Listener {

    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new Unique(),  45, IString.addColor("主菜单 | 你好! " + p.getName() + " !"));
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {

            ItemStack board = IBuilder.getBorder(Material.BLACK_STAINED_GLASS_PANE);
            for (int i = 0; i < 9; i++) {
                menu.setItem(i, board);
            }
            ItemStack bg = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
            for (int i = 9; i < 45; i++) {
                menu.setItem(i, bg);
            }

            //TODO
            menu.setItem(44 , IBuilder.buildItem(Material.COMPASS , "&c返回主页"));

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }


    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof Unique) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {

                case 44 -> MainMenu.open(p);
            }
        }
    }
}
