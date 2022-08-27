package me.xiaozhangup.mooncube.menu;

import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaozhangup.mooncube.gui.TagMenu;
import me.xiaozhangup.mooncube.gui.WarpHolder;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
import me.xiaozhangup.mooncube.player.nametag.TagChangePack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NameTag implements Listener {

    private static final ItemStack closeItem = IBuilder.buildItem(Material.BARRIER, "&c关闭菜单");
    private static final ItemStack backItem = IBuilder.buildItem(Material.COMPASS, "&c返回主页");
    private static final ItemStack remove = IBuilder.buildItem(Material.BARRIER, "&c移除当前称号并恢复默认");
    private static final ItemStack sign = IBuilder.buildItem(Material.NAME_TAG, "&x&f&f&7&9&3&f设置你当前的称号", " ", "&7限制为6个字符之内");
    private List<Player> input = new ArrayList<>();

    public static void open(Player p) {
        if (!p.hasPermission("unique.tagset")) {
            p.sendMessage(IString.addColor("&8[&x&f&7&d&7&9&4前缀&8] &c你没有权限修改前缀! &e这需要Unique权限组!"));
            return;
        }
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory menu = Bukkit.createInventory(new TagMenu(), 36, IString.addColor("前缀称号菜单"));

        ItemStack board = IBuilder.getBorder(Material.BLACK_STAINED_GLASS_PANE);
        for (int i = 0; i < 9; i++) {
            menu.setItem(i, board);
        }
        ItemStack bg = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
        for (int i = 9; i < 36; i++) {
            menu.setItem(i, bg);
        }

        //20 22 24
        menu.setItem(20, Skull.getSkull(p, "&f你现在的称号", " ", PlaceholderAPI.setPlaceholders(p, "%luckperms_prefix%")));
        menu.setItem(22, sign);
        menu.setItem(24, remove);

        menu.setItem(35, closeItem);
        menu.setItem(34, backItem);

        p.openInventory(menu);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof WarpHolder) {
            e.setCancelled(true);
            switch (e.getRawSlot()) {

                case 24 -> TagChangePack.clearAll(p);
                case 22 -> {
                    p.closeInventory();
                    p.sendMessage(IString.addColor("&8[&x&f&7&d&7&9&4前缀&8] &e请在聊天框中输入你想要的称号,支持颜色代码! &7(字符不可超过6个)"));
                    input.add(p);
                }

                case 34 -> MainMenu.open(p);
                case 35 -> p.closeInventory();
            }
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        String inputtext = e.getMessage();
        if (input.contains(p)) {
            e.setCancelled(true);
            input.remove(p);
            if (inputtext.length() > 6) {
                p.sendMessage(IString.addColor("&8[&x&f&7&d&7&9&4前缀&8] &c你提供的文本过长了!"));
                return;
            }
            TagChangePack.setPerfix(p, inputtext);
            p.sendMessage(IString.addColor("&8[&x&f&7&d&7&9&4前缀&8] &f已经成功设置了你的称号!"));
            open(p);
        }
    }

}
