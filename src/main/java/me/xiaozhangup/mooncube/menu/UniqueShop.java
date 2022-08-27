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

    private static final ItemStack uniqueSkull = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU4ZjE5ZGVmMzMwODVkYjU2NDkwOWZjOGMxYjJlYWJjMGViYzU5Y2Q4OTY2NWYzMWU1ODBmMWZjMjQ5N2I1OCJ9fX0=",
            "&x&A&E&5&2&D&4Unqiue 权限组",
            "&7畅享 HAPPYLAND 提供的游戏内容",
            " ",
            "&f特权列表:",
            "&e专属称号 &8[&9Unqiue&8]",
            "&e更丰富的签到奖励 无限飞行",
            "&6放置更多的精灵,农场",
            "&6存储多达16只宠物",
            "&f/ec - 快速打开末影箱",
            "&f/wb - 快速打开工作台",
            "&f/anvil - 快速打开铁砧",
            "&f/top - 快速登顶",
            "&f/jump - 快速传送",
            "&f/heal - 快速恢复健康",
            "&7头颅库免费使用",
            " ",
            "&f定价: &e24元/月",
            "&e单击购买");
    private static final ItemStack flyItem = IBuilder.buildItem(Material.FEATHER, "&f无限飞行", " ", "&7在服务器内无限飞行", "&e在相同的一个月内,你可以购买Unique以更便宜的价格", "&e在获取飞行的同时享受其他更多权益!", " ", "&f定价: &e7元/周", "&e单击购买");
    private static final ItemStack iakey = IBuilder.buildItems(Material.TRIPWIRE_HOOK, "&5&l附魔书抽奖箱钥匙&r", 4, " ", "&f定价: &e32元/4个", "&e单击购买");
    private static final ItemStack enchkey = IBuilder.buildItems(Material.TRIPWIRE_HOOK, "&3&l饰品箱子钥匙&r", 4, " ", "&f定价: &e12元/4个", "&e单击购买");
    private static final ItemStack magickey = IBuilder.buildItems(Material.TRIPWIRE_HOOK, "&f&l神奇抽奖箱钥匙&r", 6, " ", "&f定价: &e12元/6个", "&e单击购买");
    private static final ItemStack contactItem = IBuilder.buildItem(Material.DARK_OAK_SIGN, "&x&C&6&8&4&0&0出现问题请联系QQ: &73296517911");
    private static final ItemStack closeItem = IBuilder.buildItem(Material.BARRIER, "&c关闭菜单");
    private static final ItemStack backItem = IBuilder.buildItem(Material.COMPASS, "&c返回主页");

    private static final ItemStack aoshubook = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVmZTY1MDViYzg0MGFiZmQ4NjY2MjYxMDE3ZWMyMGE2ODQ2ODU2MWJjM2NmMmZhZDYzOWE0ZWM5NDc4OWZhMCJ9fX0=",
            "&6奥术之书", "", "&f古老而被尘封的物品", "&f依稀看出它是一本附魔书", "", "&e右键 &f打开书本","", "&7必开出高级附魔" , "", "&f定价: &e8元/2个", "&e单击购买");

    private static final ItemStack baoshi = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5M2ViYWNiNjBiNzE3OTMzNTVmZGUwZDRiYmE0M2E5YzVlYzA5YzNmMzg4OTdjNDhjMWY4NTc1MjNhMGEyOSJ9fX0=",
            "&x&1&E&9&0&F&F完美的蓝宝石", "", "&f消耗一个蓝宝石和 20 级经验", "&f来进行一次手动附魔或祛魔", "", "&f成功机率: &e100%", " ",
            "&7将它拿在副手然后打开背包", "&7用附魔书按下 Shift 点击装备来附魔", "&7空的书本按下 Shift 点击已附魔的东西祛魔", "", "&f定价: &e6元/1个", "&e单击购买");

    private static final ItemStack purpleboard = IBuilder.getBorder(Material.PURPLE_STAINED_GLASS_PANE);

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
            menu.setItem(13, flyItem);
            menu.setItem(14, iakey);
            menu.setItem(15, enchkey);
            menu.setItem(16, magickey);
            menu.setItem(22, aoshubook);
            menu.setItem(23, baoshi);

            //TODO
            menu.setItem(36, IBuilder.buildItem(Material.GRAY_BANNER, "&x&7&5&7&5&7&5当前个人信息", " ", "&fUnique到期时间: " + PlaceholderAPI.setPlaceholders(p, "%luckperms_group_expiry_time_unique%")));
            menu.setItem(37, contactItem);

            menu.setItem(44, closeItem);
            menu.setItem(43, backItem);

            fillGUI(menu, 18, 20, 28, 29, 27, 9 ,10, 11);

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

                case 13 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy 7天飞行 wechat");
                }

                case 14 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy ia wechat");
                }

                case 15 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy ench wechat");
                }

                case 16 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy magic wechat");
                }

                case 22 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy aoshu wechat");
                }

                case 23 -> {
                    p.closeInventory();
                    Bukkit.dispatchCommand(p, "minepay buy baoshi wechat");
                }

                case 43 -> MainMenu.open(p);

                case 44 -> p.closeInventory();
            }
        }
    }

    public static void fillGUI(Inventory inventory , int... i) {
        for (int slot : i) {
            inventory.setItem(slot, purpleboard);
        }
    }
}
