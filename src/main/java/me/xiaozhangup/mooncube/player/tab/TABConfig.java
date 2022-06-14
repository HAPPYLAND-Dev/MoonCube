package me.xiaozhangup.mooncube.player.tab;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.bossbar.BossBarManager;
import me.neznamy.tab.api.scoreboard.ScoreboardManager;
import me.xiaozhangup.mooncube.gui.TabC;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TABConfig implements Listener {

    public static List<Integer> bossbarSlot = new ArrayList<>();
    public static List<Integer> boardSlot = new ArrayList<>();

    public static void openTAB(Player p) {
        Inventory tab = Bukkit.createInventory(new TabC(), 45, IString.addColor("游戏页面控件置"));

        ScoreboardManager scoreboardManager = TabAPI.getInstance().getScoreboardManager();
        BossBarManager bossBarManager = TabAPI.getInstance().getBossBarManager();

        TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(p.getUniqueId());

        ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta boardMeta = board.getItemMeta();
        boardMeta.setDisplayName(" ");
        board.setItemMeta(boardMeta);

        for (int i = 0; i < 45; i++) {
            tab.setItem(i, board);
        }

        if (bossBarManager.hasBossBarVisible(tabPlayer)) {
            ItemStack bosson = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta bossonMeta = bosson.getItemMeta();
            bossonMeta.setDisplayName(IString.addColor("&fBossBar控件: &a开启"));
            List<String> onlore = new ArrayList<>();
            onlore.add(" ");
            onlore.add(IString.addColor("&7单击来关闭这个控件"));
            bossonMeta.setLore(onlore);
            bosson.setItemMeta(bossonMeta);
            for (int i = 1; i < 8; i++) {
                tab.setItem(i, bosson);
            }
        } else {
            ItemStack bossoff = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta bossoffMeta = bossoff.getItemMeta();
            bossoffMeta.setDisplayName(IString.addColor("&fBossBar控件: &c关闭"));
            List<String> offlore = new ArrayList<>();
            offlore.add(" ");
            offlore.add(IString.addColor("&7单击来打开这个控件"));
            bossoffMeta.setLore(offlore);
            bossoff.setItemMeta(bossoffMeta);
            for (int i = 1; i < 8; i++) {
                tab.setItem(i, bossoff);
            }
        }

        if (scoreboardManager.hasScoreboardVisible(tabPlayer)) {
            ItemStack boardon = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            ItemMeta boardonMeta = boardon.getItemMeta();
            boardonMeta.setDisplayName(IString.addColor("&f计分板控件: &a开启"));
            List<String> bloreon = new ArrayList<>();
            bloreon.add(" ");
            bloreon.add(IString.addColor("&7单击来关闭这个控件"));
            boardonMeta.setLore(bloreon);
            boardon.setItemMeta(boardonMeta);
            fastSet(boardon, tab, 25, 26, 34, 35, 43, 44);
        } else {
            ItemStack boardoff = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            ItemMeta boardoffMeta = boardoff.getItemMeta();
            boardoffMeta.setDisplayName(IString.addColor("&f计分板控件: &c关闭"));
            List<String> bloreoff = new ArrayList<>();
            bloreoff.add(" ");
            bloreoff.add(IString.addColor("&7单击来打开这个控件"));
            boardoffMeta.setLore(bloreoff);
            boardoff.setItemMeta(boardoffMeta);
            fastSet(boardoff, tab, 25, 26, 34, 35, 43, 44);
        }

        p.openInventory(tab);
    }

    public static void fastSet(ItemStack itemStack, Inventory inventory, Integer... list) {
        for (Integer slot : list) {
            inventory.setItem(slot, itemStack);
        }
    }

    public static void setUp() {
        int[] ibossbarSlot = {1, 2, 3, 4, 5, 6, 7};
        int[] iboardSlot = {25, 26, 34, 35, 43, 44};
        for (Integer bossbar : ibossbarSlot) {
            bossbarSlot.add(bossbar);
        }
        for (Integer board : iboardSlot) {
            boardSlot.add(board);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        ScoreboardManager scoreboardManager = TabAPI.getInstance().getScoreboardManager();
        BossBarManager bossBarManager = TabAPI.getInstance().getBossBarManager();

        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof TabC) {
            e.setCancelled(true);
            TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(p.getUniqueId());
            if (boardSlot.contains(e.getRawSlot())) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                if (scoreboardManager.hasScoreboardVisible(tabPlayer)) {
                    scoreboardManager.setScoreboardVisible(tabPlayer, false, false);
                    openTAB(p);
                } else {
                    scoreboardManager.setScoreboardVisible(tabPlayer, true, false);
                    openTAB(p);
                }
            }
            if (bossbarSlot.contains(e.getRawSlot())) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                if (bossBarManager.hasBossBarVisible(tabPlayer)) {
                    bossBarManager.setBossBarVisible(tabPlayer, false, false);
                    openTAB(p);
                } else {
                    bossBarManager.setBossBarVisible(tabPlayer, true, false);
                    openTAB(p);
                }
            }
        }
    }
}
