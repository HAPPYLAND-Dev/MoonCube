package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.gui.Emo;
import me.xiaozhangup.mooncube.gui.HomeMenu;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
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
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {

            ItemStack board = IBuilder.getBorder(Material.BLACK_STAINED_GLASS_PANE);
            for (int i = 0; i < 9; i++) {
                menu.setItem(i, board);
            }
            ItemStack bg = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
            for (int i = 9; i < 45; i++) {
                menu.setItem(i, bg);
            }

            //TODO
            menu.setItem(0 , Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRkNDliYWU5NWM3OTBjM2IxZmY1YjJmMDEwNTJhNzE0ZDYxODU0ODFkNWIxYzg1OTMwYjNmOTlkMjMyMTY3NCJ9fX0=" , "&f显示界面设置" , "&7" , "&7调整游戏的页面控件"));
            menu.setItem(1 , Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFjMmY5MjhjNGFiZTMxZTM0MmM4MGM3MWZlZjcyM2U5OTA1NzE3ZjQ5OGRkNzQ2ZWJmOTQxNzk4ODlhNzVjMyJ9fX0=" , "&6鼓励我们" , "&7" , "&7为我们的宣传贴顶贴!" , "&7这样我们可以获得更多曝光"));
            menu.setItem(2, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTU4NjlmZDYzNTZkYjA3M2JhZGFlNzZkMTQzNTVkZjM1NGI5NzZjOWExMWIwNjMxZWY3NDc4ZTgyNmRhNTE5MCJ9fX0=" , "&a回到岛屿" , "&7" , "&7传送回自己的岛屿"));
            menu.setItem(3, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjUyNTU5ZjJiY2VhZDk4M2Y0YjY1NjFjMmI1ZjJiNTg4ZjBkNjExNmQ0NDY2NmNlZmYxMjAyMDc5ZDI3Y2E3NCJ9fX0=" , "&b服务器负载" , "&7" , "Dev...."));
            menu.setItem(4, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjMwMWRmOTkwMjU4NDlmZDk1OTFhNzY5NTJiZjRkMDEzOGU5ZGZiMTQ4ZWM5NDM0N2RlYWRkNTJmNGVjODY2NCJ9fX0=" , "&e宠物管理" , "&7" , "&7管理你的宠物"));

            menu.setItem(8 , Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTVkYjg0OWExNWVmMDFhMDMyMTNkMzZjN2M2MmUwN2I4OGMyNzdkYmE4NjkzMTdiN2UyNzU3MWZkZGIyOTNkNCJ9fX0=" , "&x&C&D&8&5&3&F主页面" , "&7" , "&7使用Shift + F打开本菜单" , "&7或者使用 /menu"));

            Bukkit.getScheduler().runTask(Main.plugin, () -> p.openInventory(menu));
        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof HomeMenu) {
            e.setCancelled(true);
        }
    }
}
