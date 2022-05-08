package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.gui.HeyProfile;
import me.xiaozhangup.mooncube.gui.IsControl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Hey implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.PLAYER) return;
        Player ed = (Player) e.getRightClicked();
        Player p = e.getPlayer();
        if (!p.isSneaking()) {
            Inventory iscontrol = Bukkit.createInventory(new IsControl(), 27, "岛屿邀请 " + ed.getName());
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                for (int i = 0; i < 27; i++) {
                    iscontrol.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                }


                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(iscontrol);
                });
            });
        } else {
            Inventory profile = Bukkit.createInventory(new HeyProfile(), 54, "玩家个人资料 " + ed.getName());
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                for (int i = 0; i < 54; i++) {
                    profile.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
                }


                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(profile);
                });
            });
        }
    }

    //            for (int s = 0; s < 27; s++) {
//                iscop.setItem(s, new SItem(Material.BLACK_STAINED_GLASS_PANE));
//            }
//            iscop.setItem(10 , addName(Main.api.getItemHead("483") , "&7邀请加入你的岛屿 &f(invite)"));
//            iscop.setItem(11 , addName(Main.api.getItemHead("477") , "&7添加临时权限 &f(coop)"));
//            iscop.setItem(12 , addName(Main.api.getItemHead("494") , "&7添加为可信者 &f(trust)"));
//            iscop.setItem(13 , new ItemStack(Material.AIR));
//            iscop.setItem(14 , new ItemStack(Material.AIR));
//            iscop.setItem(15 , addName(Main.api.getItemHead("8830") , "&f从岛屿踢出本玩家 (expel)"));
//            iscop.setItem(16 , addName(Main.api.getItemHead("8839") , "&f从岛屿拉黑本玩家 (team kick)"));
//            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
//            p.openInventory(iscop);
//        } else {
//            for (int s = 0; s < 54; s++) {
//                ui.setItem(s, new SItem(Material.BLACK_STAINED_GLASS_PANE));
//            }
//            if ( ed.getInventory().getHelmet() != null ) {
//                ui.setItem(3, 2, ed.getInventory().getHelmet());
//            } else {
//                ui.setItem(3, 2, new SItem(Material.AIR));
//            }
//            if ( ed.getInventory().getBoots() != null ) {
//                ui.setItem(3, 5, ed.getInventory().getBoots());
//            } else {
//                ui.setItem(3, 5, new SItem(Material.AIR));
//            }
//            if ( ed.getInventory().getLeggings() != null ) {
//                ui.setItem(3, 4, ed.getInventory().getLeggings());
//            } else {
//                ui.setItem(3, 4, new SItem(Material.AIR));
//            }
//            if ( ed.getInventory().getChestplate() != null ) {
//                ui.setItem(3, 3, ed.getInventory().getChestplate());
//            } else {
//                ui.setItem(3, 3, new SItem(Material.AIR));
//            }
//            if ( ed.getInventory().getItemInOffHand() != null ) {
//                ui.setItem(2, 3, ed.getInventory().getItemInOffHand());
//            } else {
//                ui.setItem(2, 3, new SItem(Material.AIR));
//            }
//            if ( ed.getInventory().getItemInMainHand() != null ) {
//                ui.setItem(4, 3, ed.getInventory().getItemInMainHand());
//            } else {
//                ui.setItem(4, 3, new SItem(Material.AIR));
//            }
//
//            ItemStack exp = new ItemStack(Main.api.getItemHead("6310"));
//            ItemMeta expmeta = exp.getItemMeta();
//            expmeta.setDisplayName(Message.Color("&a经验: &7" + ed.getTotalExperience() + " &a点"));
//            exp.setItemMeta(expmeta);
//
//            ItemStack money = new ItemStack(Main.api.getItemHead("40502"));
//            ItemMeta monmeta = money.getItemMeta();
//            monmeta.setDisplayName(Message.Color("&6资产: &7" + Main.getEconomy().getBalance(ed) + " &6元"));
//            money.setItemMeta(monmeta);
//
//            ItemStack level = new ItemStack(Main.api.getItemHead("24064"));
//            ItemMeta levelnmeta = level.getItemMeta();
//            levelnmeta.setDisplayName(Message.Color("&b岛屿等级: &7" + PlaceholderAPI.setPlaceholders(ed, "%Level_bskyblock_island_level%") + " &b级"));
//            level.setItemMeta(levelnmeta);
//
//            ItemStack aach = new ItemStack(Main.api.getItemHead("25716"));
//            ItemMeta aachmeta = aach.getItemMeta();
//            aachmeta.setDisplayName(Message.Color("&c成就数: &7" + PlaceholderAPI.setPlaceholders(ed, "%aach_achievements%") + " &c个"));
//            aach.setItemMeta(aachmeta);
//
//            ItemStack reg = new ItemStack(Main.api.getItemHead("41994"));
//            ItemMeta regmeta = reg.getItemMeta();
//            regmeta.setDisplayName(Message.Color("&f岛屿大小: &7" + PlaceholderAPI.setPlaceholders(ed, "%bskyblock_island_protection_range%") + "*" + PlaceholderAPI.setPlaceholders(ed, "%bskyblock_island_protection_range%")));
//            reg.setItemMeta(regmeta);
//
//            ItemStack dt = new ItemStack(Main.api.getItemHead("14186"));
//            ItemMeta dtmeta = dt.getItemMeta();
//            dtmeta.setDisplayName(Message.Color("&9单挑KD值: &7" + PlaceholderAPI.setPlaceholders(ed, "%dantiao_kd%")));
//            dt.setItemMeta(dtmeta);
//
//            ui.setItem(7, 2, exp);
//            ui.setItem(7, 3, money);
//            ui.setItem(7, 4, level);
//            ui.setItem(7, 5, aach);
//            ui.setItem(8, 2, reg);
//            ui.setItem(8, 3, dt);
//
//            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
//            ui.setTitle(Message.Color("&0玩家 " + ed.getName() + " 的数据"));
//            ui.openInventory(p);
//        }
//    }
//
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof HeyProfile) {
            e.setCancelled(true);
        }
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof IsControl) {
            e.setCancelled(true);
        }
    }
//            String name = e.getView().getTitle().replace("岛屿邀请" , "");
//            Player p = (Player) e.getWhoClicked();
//            if (e.getRawSlot() == 10) {
//                p.performCommand("island team invite" + name);
//            } else if (e.getRawSlot() == 11) {
//                p.performCommand("island team coop" + name);
//            } else if (e.getRawSlot() == 12) {
//                p.performCommand("island team trust" + name);
//            } else if (e.getRawSlot() == 15) {
//                p.performCommand("island expel" + name);
//            } else if (e.getRawSlot() == 16) {
//                p.performCommand("island ban" + name);
//            }
//        }
//    }
//
//    public static ItemStack addName(ItemStack itemStack , String s) {
//        ItemMeta meta = itemStack.getItemMeta();
//        meta.setDisplayName(s.replace("&" , Message.CP));
//        itemStack.setItemMeta(meta);
//        return itemStack;
}
