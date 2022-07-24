package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.WarpHolder;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
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

public class Warps implements Listener {

    private static final ItemStack closeItem = IBuilder.buildItem(Material.BARRIER, "&c关闭菜单");
    private static final ItemStack backItem = IBuilder.buildItem(Material.COMPASS, "&c返回主页");
    private static final ItemStack treemaker = IBuilder.buildItem(Material.WOODEN_AXE, "&x&7&B&5&E&5&7树场", " ", "&7/warp treemaker");
    private static final ItemStack spawn = IBuilder.buildItem(Material.CAMPFIRE, "&x&F&F&5&F&5&2出生点", " ", "&7/warp spawn");
    private static final ItemStack pvp = IBuilder.buildItem(Material.NETHERITE_SWORD, "&x&6&A&1&B&9&A集体PvP场", " ", "&7/warp pvp");

    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new WarpHolder(), 45, IString.addColor("主菜单 | 你好! " + p.getName() + " !"));
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {

            ItemStack board = IBuilder.getBorder(Material.BLACK_STAINED_GLASS_PANE);
            for (int i = 0; i < 9; i++) {
                menu.setItem(i, board);
            }
            ItemStack bg = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
            for (int i = 9; i < 45; i++) {
                menu.setItem(i, bg);
            }

            menu.setItem(11, treemaker);
            menu.setItem(12, spawn);
            menu.setItem(13, pvp);

            menu.setItem(8, Skull.getSkull(p, "&7" + p.getName(), " ", "&e单击修改名片"));

            menu.setItem(44, closeItem);
            menu.setItem(43, backItem);

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof WarpHolder) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {
                case 8 -> ProfileEditor.openProfile(p);

                case 11 -> Bukkit.dispatchCommand(p, "warp treemaker");
                case 12 -> Bukkit.dispatchCommand(p, "warp spawn");
                case 13 -> Bukkit.dispatchCommand(p, "warp pvp");

                case 43 -> MainMenu.open(p);
                case 44 -> p.closeInventory();
            }
        }
    }
}
