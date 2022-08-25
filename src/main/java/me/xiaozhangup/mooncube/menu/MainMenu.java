package me.xiaozhangup.mooncube.menu;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.HomeMenu;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
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

    public static void open(Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new HomeMenu(), 45, IString.addColor("主菜单 | 你好! " + p.getName() + " !"));
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
            menu.setItem(8, Skull.getSkull(p, "&7" + p.getName(), " ", "&e单击修改名片"));
            menu.setItem(36, IBuilder.buildItem(Material.BOOK, "&x&7&B&5&E&5&7编辑界面控件", " ", "&7开启或关闭计分板和BossBar"));
            menu.setItem(37, IBuilder.buildItem(Material.WATER_BUCKET, "&x&6&3&C&C&F&F切换岛屿生物群系", " ", "&7切换岛屿的群系"));
            menu.setItem(38, IBuilder.buildItem(Material.COD, "&x&4&F&B&3&B&F开启或关闭特殊钓鱼", " ", "&7如果你需要回到原版的钓鱼", "&7调整它即可"));
            menu.setItem(39, IBuilder.buildItem(Material.CHEST_MINECART, "&x&F&F&9&D&3&F打开岛屿商店", " ", "&7服务器交易物品的地方"));
            menu.setItem(40, IBuilder.buildItem(Material.DIAMOND_SWORD, "&x&6&A&B&7&F&F打开技能菜单", " ", "&7你的技能会在这里显示"));
            menu.setItem(44, IBuilder.buildItem(Material.BARRIER, "&c关闭菜单"));
            menu.setItem(43, IBuilder.buildItem(Material.ENDER_CHEST, "&x&4&8&A&9&9&9赞助我们", " ", "&7服务器的运行与开发需要大笔资金", "&7您的赞助至关重要", "&c所有赞助将全部用于维护服务器"));

            menu.setItem(11, IBuilder.buildItem(Material.COMMAND_BLOCK_MINECART, "&x&F&F&C&0&4&6岛屿操作", " ", "&7小众岛屿设置不太常用", "&7但也有用", " ","&e左键 &8- &7岛屿控制页", "&e右键 &8- &7小众岛屿设置"));
            menu.setItem(12, IBuilder.buildItem(Material.CAT_SPAWN_EGG, "&x&C&5&6&0&0&0宠物管理", " ", "&7管理你的宠物 (/pet)", " ", "&e如何捕捉宠物?", "&e使用栓绳击杀生物", "&e便有可能将对应生物", "&e变为你的宠物"));
            menu.setItem(13, IBuilder.buildItem(Material.HONEY_BOTTLE, "&x&B&C&5&1&0&0游览其他岛屿", " ", "&e左键 &8- &7查看岛屿转送点", "&e右键 &8- &7浏览全部玩家的岛屿"));
            menu.setItem(20, IBuilder.buildItem(Material.FISHING_ROD, "&x&F&9&6&8&3&A鱼的收购", " ", "&7收购你钓上来的特殊的鱼"));
            menu.setItem(21, IBuilder.buildItem(Material.OAK_SIGN, "&x&F&F&9&E&4&0服务器传送点", " ", "&7点击打开传送点菜单"));
            menu.setItem(22, IBuilder.buildItem(Material.BLUE_CANDLE, "&x&7&C&4&3&B&D扩展附魔百科", " ","&e善用附魔检验台!", " ", "&7点击打开扩展附魔菜单"));
            menu.setItem(15, IBuilder.buildItem(Material.WRITABLE_BOOK, "&x&F&F&D&9&5&A打开更新记录", " ", "&7服务器的版本记录"));
            menu.setItem(24, IBuilder.buildItem(Material.KNOWLEDGE_BOOK, "&x&6&0&A&D&5&E可能的更改", " ", "&7HAPPYLAND Dev的计划性更改"));

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
                case 40 -> Bukkit.dispatchCommand(p, "sk");

                case 11 -> {
                    if (e.getClick() == ClickType.LEFT) {
                        Bukkit.dispatchCommand(p, "is");
                    } else if (e.getClick() == ClickType.RIGHT) {
                        Bukkit.dispatchCommand(p, "is settings");
                    }
                }
                case 12 -> Bukkit.dispatchCommand(p, "pet");
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
                    p.closeInventory();
                    String book = ConfigManager.getConfig("book").getString("book");
                    ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                    bookMeta.setAuthor("HAPPYLAND Studio");
                    bookMeta.setTitle("Server Message");
                    bookMeta.addPage(IString.addColor(book));
                    itemStack.setItemMeta(bookMeta);
                    p.openBook(itemStack);
                }
                case 24 -> {
                    p.closeInventory();
                    String book = ConfigManager.getConfig("plan").getString("book");
                    ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                    bookMeta.setAuthor("HAPPYLAND Studio");
                    bookMeta.setTitle("Server Message");
                    bookMeta.addPage(IString.addColor(book));
                    itemStack.setItemMeta(bookMeta);
                    p.openBook(itemStack);
                }

                case 43 -> UniqueShop.open(p);
                case 44 -> p.closeInventory();
            }
        }
    }
}
