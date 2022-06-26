package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.HomeMenu;
import me.xiaozhangup.mooncube.gui.TabC;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
import me.xiaozhangup.mooncube.player.ProfileEditer;
import me.xiaozhangup.mooncube.player.tab.TABConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainMenu implements Listener {

    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new HomeMenu(),  45, IString.addColor("主菜单 | 你好! " + p.getName() + " !"));
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
            menu.setItem(8 , Skull.getSkull(p , "&7" + p.getName() , " "   , "&e单击修改名片"));
            menu.setItem(36 , IBuilder.buildItem(Material.BOOK , "&x&7&B&5&E&5&7编辑界面控件" , " " , "&7开启或关闭计分板和BossBar"));
            menu.setItem(37 , IBuilder.buildItem(Material.WATER_BUCKET , "&x&6&3&C&C&F&F切换岛屿生物群系", " " , "&7切换岛屿的群系"));
            menu.setItem(38 , IBuilder.buildItem(Material.COD , "&x&4&F&B&3&B&F开启或关闭特殊钓鱼" , " " , "&7如果你需要回到原版的钓鱼" , "&7调整它即可"));
            menu.setItem(44 , IBuilder.buildItem(Material.BARRIER , "&c关闭菜单"));

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof HomeMenu) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {
                case 8 -> ProfileEditer.openProfile(p);
                case 36 -> TABConfig.openTAB(p);
                case 37 -> Bukkit.dispatchCommand(p , "is biome");
                case 38 -> Bukkit.dispatchCommand(p , "emf toggle");

                case 44 -> p.closeInventory();
            }
        }
    }
}
