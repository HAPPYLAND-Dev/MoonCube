package me.xiaozhangup.mooncube.menu;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.Unique;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
import me.xiaozhangup.mooncube.player.ProfileEditer;
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

            //, " ", "&f定价: &e24元/月" , "&e单击购买"
            menu.setItem(8 , Skull.getSkull(p , "&7" + p.getName() , " "   , "&e单击修改名片"));
            menu.setItem(19 , Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU4ZjE5ZGVmMzMwODVkYjU2NDkwOWZjOGMxYjJlYWJjMGViYzU5Y2Q4OTY2NWYzMWU1ODBmMWZjMjQ5N2I1OCJ9fX0=" , "&x&A&E&5&2&D&4Unqiue 权限组" , " " , "&f享有诸多特权,助理游戏进度", "&7特权列表:"  , "&c专属称号 &f[&9Unqiue&f]", "&e签到奖励翻倍" ,"&f/ec - 快速打开末影背包",  "&f/wb - 快速打开工作台" , "&f/anvial - 快速打开铁砧" ,"&f/top - 快速登顶" , "&f/jump - 快速传送" , "&f/heal - 快速恢复", "&f/sf charge - 快速充电" , "&7头颅库免费使用" , " ", "&f定价: &e24元/月" , "&e单击购买"));
            menu.setItem(12 , IBuilder.buildItem(Material.FEATHER , "&f无限飞行" , " " , "&7在服务器内无限飞行" , " " , "&f定价: &e3元/周" , "&e单击购买"));
            menu.setItem(13 , IBuilder.buildItem(Material.ENCHANTED_BOOK , "&x&D&0&5&C&E&3粘液科技物品全解锁" , " " , "&7解锁粘液科技所有物品", " ", "&f定价: &e27元" , "&e单击购买"));

            //TODO
            menu.setItem(36 , IBuilder.buildItem(Material.GRAY_BANNER , "&x&7&5&7&5&7&5当前个人信息" , " " , "&fUnique到期时间: " + PlaceholderAPI.setPlaceholders(p , "%luckperms_group_expiry_time_unique%")));
            menu.setItem(37 , IBuilder.buildItem(Material.DARK_OAK_SIGN , "&x&C&6&8&4&0&0出现问题请联系QQ: &73296517911"));

            menu.setItem(44 , IBuilder.buildItem(Material.BARRIER , "&c关闭菜单"));
            menu.setItem(43 , IBuilder.buildItem(Material.COMPASS , "&c返回主页"));

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }


    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof Unique) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {

                case 8 -> ProfileEditer.openProfile(p);

                //TODO

                case 43 -> MainMenu.open(p);
                case 44 -> p.closeInventory();
            }
        }
    }
}
