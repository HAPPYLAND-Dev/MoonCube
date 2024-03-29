package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.HomeMenu;
import me.xiaozhangup.mooncube.gui.Tweak;
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

import static me.xiaozhangup.mooncube.menu.MainMenu.BOARD;

public class Tweaker implements Listener {

    public static ItemStack ITEM1 = IBuilder.buildItem(Material.OAK_LEAVES, "&f树叶快速腐烂", "&7树叶将很快腐烂");
    public static ItemStack ITEM2 = IBuilder.buildItem(Material.IRON_HOE, "&f范围收割", "&7使用锄收割作物将会", "&7一次性收集3x3左右的范围");
    public static ItemStack ITEM3 = IBuilder.buildItem(Material.BONE_MEAL, "&f范围催熟", "&7将会催熟范围内的作物", "&7甚至可以催熟仙人掌,甘蔗等");
    public static ItemStack ITEM4 = IBuilder.buildItem(Material.TOTEM_OF_UNDYING, "&f死亡重生", "&7你不会掉落任何的物品,经验", "&7可以在\"主菜单->游戏设置\"自定义重生点");
    public static ItemStack ITEM5 = IBuilder.buildItem(Material.LADDER, "&f快速梯子", "&7手持梯子右键梯子将会在右键的梯子下方放置梯子", "&7Shift + 右键整段梯子的一端可快速到达另一端");
    public static ItemStack ITEM6 = IBuilder.buildItem(Material.GRASS, "&f透草攻击", "&7你的攻击将会透过草等等");
    public static ItemStack ITEM7 = IBuilder.buildItem(Material.NAME_TAG, "&f生物静音", "&7将生物命名为silence可让其不再发出声音", "&7再次命名为unsilence可让其再次发出声音");
    public static ItemStack ITEM8 = IBuilder.buildItem(Material.NOTE_BLOCK, "&f生物音响", "&7在音符盒侧边放置生物头颅", "&7可让音符盒发出对应生物的声音");
    public static ItemStack ITEM9 = IBuilder.buildItem(Material.ITEM_FRAME, "&f透明展示框", "&7使用剪刀右键展示框可使其不可见");
    public static ItemStack ITEM10 = IBuilder.buildItem(Material.SNOWBALL, "&f强劲的雪球", "&7雪球能砸死任何生物,每次造成0.5点伤害", "&7并且会击退生物");
    public static ItemStack ITEM11 = IBuilder.buildItem(Material.WATER_BUCKET, "&f无限水桶", "&7将水桶使用铁砧附魔为无限", "&7使其可无限放置水源");
    public static ItemStack ITEM12 = IBuilder.buildItem(Material.OBSIDIAN, "&f异形地狱门", "&7地狱门可以为任何形状,并点燃", "&7可使用黑曜石和哭泣的黑曜石");
    public static ItemStack ITEM13 = IBuilder.buildItem(Material.VILLAGER_SPAWN_EGG, "&f贪财的村民", "&7如果你手持绿宝石块", "&7那么村民会被你吸引过来");
    public static ItemStack ITEM14 = IBuilder.buildItem(Material.WARDEN_SPAWN_EGG, "&f更快的流浪商人生成", "&7流浪商人会每7分钟生成一次");
    public static ItemStack ITEM15 = IBuilder.buildItem(Material.POLAR_BEAR_SPAWN_EGG, "&f无AI", "&7将生物困在一格内时,生物将失去AI");
    public static ItemStack ITEM16 = IBuilder.buildItem(Material.ENCHANTED_BOOK, "&f附魔大改", "&7所有的附魔都存在了稀有度", "&7并且村民不会出售特殊附魔", "&e主菜单可以查看所有附魔的介绍");
    public static ItemStack ITEM17 = IBuilder.buildItem(Material.PLAYER_HEAD, "&f玩家相互交互", "&7你可以右键,Shift + 右键一个玩家", "&7来和他进行一些交互");
    public static ItemStack ITEM18 = IBuilder.buildItem(Material.TNT, "&fTNT复制", "&7TNT复制被禁用");
    public static ItemStack ITEM19 = IBuilder.buildItem(Material.WHITE_CARPET, "&f地毯复制", "&7地毯复制被禁用");
    public static ItemStack ITEM20 = IBuilder.buildItem(Material.POWERED_RAIL, "&f铁轨复制", "&7铁轨复制被禁用");
    public static ItemStack ITEM21 = IBuilder.buildItem(Material.SHULKER_SHELL, "&f潜影贝", "&7在末影人在末地生成时", "&7有20%的概率同时生成潜影贝");
    public static ItemStack ITEM22 = IBuilder.buildItem(Material.DISPENSER, "&f发射器/投掷器", "&7当所在区块实体数量大于24时", "&7将不会照常工作");
    public static ItemStack ITEM23 = IBuilder.buildItem(Material.NOTE_BLOCK, "&f音符盒", "&7不再能够传导红石信号");
    private static final ItemStack backItem = IBuilder.buildItem(Material.COMPASS, "&c返回主页");


    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new Tweak(), 36, IString.addColor("我们的所有游戏内容修改"));
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {

            for (int i = 0; i < 9; i++) {
                menu.setItem(i, BOARD);
            }
            menu.setItem(4, backItem);

            menu.setItem(9, ITEM1);
            menu.setItem(10, ITEM2);
            menu.setItem(11, ITEM3);
            menu.setItem(12, ITEM4);
            menu.setItem(13, ITEM5);
            menu.setItem(14, ITEM6);
            menu.setItem(15, ITEM7);
            menu.setItem(16, ITEM8);
            menu.setItem(17, ITEM9);
            menu.setItem(18, ITEM10);
            menu.setItem(19, ITEM11);
            menu.setItem(20, ITEM12);
            menu.setItem(21, ITEM13);
            menu.setItem(22, ITEM14);
            menu.setItem(23, ITEM15);
            menu.setItem(24, ITEM16);
            menu.setItem(25, ITEM17);
            menu.setItem(26, ITEM18);
            menu.setItem(27, ITEM19);
            menu.setItem(28, ITEM20);
            menu.setItem(29, ITEM21);
            menu.setItem(30, ITEM22);
            menu.setItem(31, ITEM23);

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof Tweak) {
            e.setCancelled(true);
            if (e.getRawSlot() == 4) {
                MainMenu.open(p);
            }
        }
    }

}
