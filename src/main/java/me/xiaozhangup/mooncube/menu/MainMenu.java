package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.ItemAdders;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.HomeMenu;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
import me.xiaozhangup.mooncube.guide.ABook;
import me.xiaozhangup.mooncube.guide.TreePicker;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import me.xiaozhangup.mooncube.player.ProfileEditor;
import me.xiaozhangup.mooncube.player.tab.TABConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class MainMenu implements Listener {

    public static final ItemStack BG = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
    public static final ItemStack BOARD = IBuilder.getBorder(Material.BLACK_STAINED_GLASS_PANE);
    public static final ItemStack ITEM = IBuilder.buildItem(Material.BOOK, "&x&7&B&5&E&5&7游戏设置", " ", "&7调整你的界面或者是其他功能");
    public static final ItemStack ITEM1 = IBuilder.buildItem(Material.WATER_BUCKET, "&x&6&3&C&C&F&F切换岛屿生物群系", " ", "&7切换岛屿的群系");
    public static final ItemStack ITEM2 = IBuilder.buildItem(Material.COD, "&x&4&F&B&3&B&F开启或关闭特殊钓鱼", " ", "&7如果你需要回到原版的钓鱼", "&7调整它即可");
    public static final ItemStack ITEM3 = IBuilder.buildItem(Material.CHEST_MINECART, "&x&F&F&9&D&3&F打开岛屿商店", " ", "&7服务器交易物品的地方");
    public static final ItemStack ITEM4 = IBuilder.buildItem(Material.DIAMOND_SWORD, "&x&6&A&B&7&F&F技能属性点", " ", "&7在你的岛屿放置一个&e书架&7并右键", "&7即可打开你的属性菜单", "&7按下&eL&7键打开概况");
    public static final ItemStack ITEM5 = IBuilder.buildItem(Material.BARRIER, "&c关闭菜单");
    public static final ItemStack ITEM6 = IBuilder.buildItem(Material.ENDER_CHEST, "&x&4&8&A&9&9&9赞助我们", " ", "&7服务器的运行与开发需要大笔资金", "&7您的赞助至关重要", "&c所有赞助将全部用于维护服务器");
    public static final ItemStack ITEM7 = IBuilder.buildItem(Material.COMMAND_BLOCK_MINECART, "&x&F&F&C&0&4&6岛屿操作", " ", "&7小众岛屿设置不太常用", "&7但也有用", " ", "&e左键 &8- &7岛屿控制页", "&e右键 &8- &7小众岛屿设置");
    public static final ItemStack ITEM8 = IBuilder.buildItem(Material.ACACIA_SAPLING, "&x&F&D&D&8&3&5查看所有的机制修改", " ", "&7我们根据需要做了大量的", "&7游戏内容/机制修改", "&7单击此处即可查看全部的更改内容");
    public static final ItemStack ITEM9 = IBuilder.buildItem(Material.HONEY_BOTTLE, "&x&B&C&5&1&0&0游览其他岛屿", " ", "&e左键 &8- &7查看岛屿转送点", "&e右键 &8- &7浏览全部玩家的岛屿", "", "&c使用命令/pw help查看帮助");
    public static final ItemStack ITEM10 = IBuilder.buildItem(Material.FISHING_ROD, "&x&F&9&6&8&3&A鱼的收购", " ", "&7收购你钓上来的特殊的鱼");
    public static final ItemStack ITEM11 = IBuilder.buildItem(Material.OAK_SIGN, "&x&F&F&9&E&4&0服务器传送点", " ", "&7点击打开传送点菜单");
    public static final ItemStack ITEM12 = IBuilder.buildItem(Material.PURPLE_CANDLE, "&x&7&C&4&3&B&D扩展附魔百科", " ", "&e善用附魔检验台!", " ", "&7点击打开扩展附魔菜单");
    public static final ItemStack ITEM13 = IBuilder.buildItem(
            Material.WRITABLE_BOOK,
            "&x&F&F&D&9&5&A服务器公告",
            " ",
            "&e左键 &8- &7服务器的版本记录",
            "&e右键 &8- &7HAPPYLAND Dev的计划性更改",
            "",
            "&e数字键操作:",
            "&f1 &7- 打开签到",
            "&f2 &7- 打开&a额外物品 &7合成书",
            "&f3 &7- 打开&2科技线 &7指导书",
            "&f4 &7- 打开&f引导之书",
            "&f5 &7- 前往&x&c&c&9&e&3&3杂鱼市场",
            "&f6 &7- 前往&x&6&b&9&b&f&d反馈任何问题或建议"
    );
    public static final ItemStack ITEM14 = IBuilder.buildItem(Material.KNOWLEDGE_BOOK, "&x&6&0&A&D&5&E获取指导书", " ", "&7获得如下三本指导书:", "&a额外物品 &7合成书", "&2科技线 &7指导书", "&f引导之书");

    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new HomeMenu(), 45, IString.addColor("主菜单 | 你好! " + p.getName() + " !"));
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {

            for (int i = 0; i < 9; i++) {
                menu.setItem(i, BOARD);
            }
            for (int i = 9; i < 45; i++) {
                menu.setItem(i, BG);
            }

            //TODO
            menu.setItem(8, Skull.getSkull(p, "&7" + p.getName(), " ", "&e单击修改名片"));
            menu.setItem(36, ITEM);
            menu.setItem(37, ITEM1);
            menu.setItem(38, ITEM2);
            menu.setItem(39, ITEM3);
            menu.setItem(40, ITEM4);
            menu.setItem(44, ITEM5);
            menu.setItem(43, ITEM6);

            menu.setItem(11, ITEM7);
            menu.setItem(12, ITEM8);
            menu.setItem(13, ITEM9);
            menu.setItem(20, ITEM10);
            menu.setItem(21, ITEM11);
            menu.setItem(22, ITEM12);
            //
            menu.setItem(15, ITEM13);
            //
            menu.setItem(24, ITEM14);

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> p.openInventory(menu));
        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof HomeMenu) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {
                case 8 -> ProfileEditor.openProfile(p);
                case 36 -> TABConfig.openTAB(p);
                case 37 -> Bukkit.dispatchCommand(p, "is biome");
                case 38 -> Bukkit.dispatchCommand(p, "emf toggle");
                case 39 -> Bukkit.dispatchCommand(p, "is shop");

                case 11 -> {
                    if (e.getClick() == ClickType.LEFT) {
                        Bukkit.dispatchCommand(p, "is");
                    } else if (e.getClick() == ClickType.RIGHT) {
                        Bukkit.dispatchCommand(p, "is settings");
                    }
                }
                case 12 -> Tweaker.open(p);
                case 13 -> {
                    if (e.getClick() == ClickType.LEFT) {
                        Bukkit.dispatchCommand(p, "pw");
                    } else if (e.getClick() == ClickType.RIGHT) {
                        Bukkit.dispatchCommand(p, "is visit");
                    }
                }
                case 20 -> Bukkit.dispatchCommand(p, "emf shop");
                case 21 -> Warps.open(p);
                case 22 -> Bukkit.dispatchCommand(p, "nereusopus menu");
                case 15 -> {
                    if (e.getClick() == ClickType.LEFT) {
                        p.closeInventory();
                        String book = ConfigManager.getConfig("book").getString("book");
                        ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                        bookMeta.setAuthor("HAPPYLAND Studio");
                        bookMeta.setTitle("Server Message");
                        bookMeta.addPage(IString.addColor(book));
                        itemStack.setItemMeta(bookMeta);
                        p.openBook(itemStack);
                    } else if (e.getClick() == ClickType.RIGHT) {
                        p.closeInventory();
                        String book = ConfigManager.getConfig("plan").getString("book");
                        ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                        BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                        bookMeta.setAuthor("HAPPYLAND Studio");
                        bookMeta.setTitle("Server Message");
                        bookMeta.addPage(IString.addColor(book));
                        itemStack.setItemMeta(bookMeta);
                        p.openBook(itemStack);
                    } else if (e.getClick() == ClickType.NUMBER_KEY) {
                        switch (e.getHotbarButton()) {
                            case 0 -> Bukkit.dispatchCommand(p, "rewards");
                            case 1 -> Bukkit.dispatchCommand(p, "ia");
                            case 2 -> Bukkit.dispatchCommand(p, "nova items");
                            case 3 -> TreePicker.open(p);
                            case 4 -> Bukkit.dispatchCommand(p, "warp fishshop");
                            case 5 -> {
                                p.closeInventory();
                                p.sendMessage(
                                        "§f",
                                        "§7",
                                        "§x§6§b§9§b§f§d    §lTencent 兔小巢",
                                        "§7    https://support.qq.com/products/452661/",
                                        "§7",
                                        "§7    提出任何你发现的BUG,或者是任何的诉求",
                                        "§7    我们会定期查看并对问题经行解决/改进",
                                        "§f",
                                        "§7",
                                        "§7"
                                );
                                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                            }
                        }
                    }
                }
                case 24 -> {
                    p.getInventory().addItem(ItemAdders.iabook, ItemAdders.novabook, TreePicker.book);
                }

                case 43 -> UniqueShop.open(p);
                case 44 -> p.closeInventory();
            }
        }
    }
}
