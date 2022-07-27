package me.xiaozhangup.mooncube.menu;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.Unique;
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

public class UniqueShop implements Listener {

    private static final ItemStack uniqueSkull = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU4ZjE5ZGVmMzMwODVkYjU2NDkwOWZjOGMxYjJlYWJjMGViYzU5Y2Q4OTY2NWYzMWU1ODBmMWZjMjQ5N2I1OCJ9fX0=", "&x&A&E&5&2&D&4Unqiue 权限组", " ", "&f享有诸多特权,助理游戏进度", "&7特权列表:", "&e专属称号 &8[&9Unqiue&8]", "&e签到奖励翻倍", "&e无限飞行", "&f/ec - 快速打开末影背包", "&f/wb - 快速打开工作台", "&f/anvil - 快速打开铁砧", "&f/top - 快速登顶", "&f/jump - 快速传送", "&f/heal - 快速恢复", "&f/sf charge - 快速充电", "&7头颅库免费使用", " ", "&f定价: &e24元/月", "&e单击购买");
    private static final ItemStack flyItem = IBuilder.buildItem(Material.FEATHER, "&f无限飞行", " ", "&7在服务器内无限飞行", "&e推荐您购买Unique获取更多权益!", " ", "&f定价: &e7元/周", "&e单击购买");
    //    private static final ItemStack slimefunUnlockAllItem = IBuilder.buildItem(Material.ENCHANTED_BOOK, "&x&D&0&5&C&E&3粘液科技物品全解锁", " ", "&7解锁粘液科技所有物品", " ", "&f定价: &e27元", "&e单击购买");
//    private static final ItemStack gener = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2Y5ZjM1NmY1ZmU3ZDFiYzkyY2RkZmFlYmEzZWU3NzNhYzlkZjFjYzRkMWMyZjhmZTVmNDcwMTMwMzJjNTUxZCJ9fX0=", "&6无尽发电机&r", " ", "&r当接收到红石信号时工作", "", "&5创造发电机", "&8⇨ &e⚡ &7发电效率由接收到的红石信号强度决定", "&7*一个就够带动极多机器", "&7*并且没有任何消耗! (3800J/s)", " ", "&f定价: &e32元/个", "&e单击购买");
//    private static final ItemStack infgener = IBuilder.buildItem(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, "&b无尽发电机", " ", "&7利用宇宙能量发电", " ", "&8⇨ &e⚡ &76000000 J 可储存", "&8⇨ &e⚡ &76000 J/s", " ", "&f定价: &e72元/个", "&e单击购买");
    private static final ItemStack contactItem = IBuilder.buildItem(Material.DARK_OAK_SIGN, "&x&C&6&8&4&0&0出现问题请联系QQ: &73296517911");
    private static final ItemStack closeItem = IBuilder.buildItem(Material.BARRIER, "&c关闭菜单");
    private static final ItemStack backItem = IBuilder.buildItem(Material.COMPASS, "&c返回主页");


    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new Unique(), 45, IString.addColor("主菜单 | 你好! " + p.getName() + " !"));
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
            menu.setItem(8, Skull.getSkull(p, "&7" + p.getName(), " ", "&e单击修改名片"));
            menu.setItem(19, uniqueSkull);
            menu.setItem(12, flyItem);
//            menu.setItem(13, slimefunUnlockAllItem);
//            menu.setItem(14, gener);
//            menu.setItem(15, infgener);

            //TODO
            menu.setItem(36, IBuilder.buildItem(Material.GRAY_BANNER, "&x&7&5&7&5&7&5当前个人信息", " ", "&fUnique到期时间: " + PlaceholderAPI.setPlaceholders(p, "%luckperms_group_expiry_time_unique%")));
            menu.setItem(37, contactItem);

            menu.setItem(44, closeItem);
            menu.setItem(43, backItem);

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }


    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof Unique) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {
                case 8 -> ProfileEditor.openProfile(p);

                case 19 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy Unqiue权限组 wechat");
                }

                case 12 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy 7天飞行 wechat");
                }

//                case 13 -> {
//                    p.closeInventory();
//                    Bukkit.dispatchCommand(p, "minepay buy 粘液科技全解 wechat");
//                }
//
//                case 14 -> {
//                    p.closeInventory();
//                    Bukkit.dispatchCommand(p, "minepay buy ENDLESS_GENERATOR wechat");
//                }
//
//                case 15 -> {
//                    p.closeInventory();
//                    Bukkit.dispatchCommand(p, "minepay buy INFINITE_PANEL wechat");
//                }

                case 43 -> MainMenu.open(p);

                case 44 -> p.closeInventory();
            }
        }
    }
}
