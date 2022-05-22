package me.xiaozhangup.mooncube.player;

import me.happylandmc.core.Skull;
import me.happylandmc.core.message.Message;
import me.xiaozhangup.mooncube.Config;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Hey implements Listener {

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.PLAYER) return;
        Player ed = (Player) e.getRightClicked();
        Player p = e.getPlayer();
        Material material = p.getInventory().getItemInMainHand().getType();
        if (Config.BLACK_ITEMS.contains(material.toString()) || material.toString().endsWith("POTION")) return;
        if (p.isSneaking()) {
            Inventory iscontrol = Bukkit.createInventory(new IsControl(), 27, Message.Color("对于玩家 " + ed.getName() + " 的岛屿选项"));
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta boardMeta = board.getItemMeta();
                //PlaceholderAPI.setPlaceholders(ed , "%%");
                boardMeta.setDisplayName(" ");
                board.setItemMeta(boardMeta);
                for (int i = 0; i < 27; i++) {
                    iscontrol.setItem(i, board);
                }

                iscontrol.setItem(8, Skull.getSkull(ed, "&7" + ed.getName()));

                iscontrol.setItem(10, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NjNGNjMmVhYTVmNzRlMjZjYjA0ZWM0YTQxZWRlYWU0ZDA0YWY5ZTJmM2U5OWJhNjRkNmM5YzBjNWUzYTdiZiJ9fX0="));
                iscontrol.setItem(11, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NjNGNjMmVhYTVmNzRlMjZjYjA0ZWM0YTQxZWRlYWU0ZDA0YWY5ZTJmM2U5OWJhNjRkNmM5YzBjNWUzYTdiZiJ9fX0="));
                iscontrol.setItem(12, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NjNGNjMmVhYTVmNzRlMjZjYjA0ZWM0YTQxZWRlYWU0ZDA0YWY5ZTJmM2U5OWJhNjRkNmM5YzBjNWUzYTdiZiJ9fX0="));
                iscontrol.setItem(13, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NjNGNjMmVhYTVmNzRlMjZjYjA0ZWM0YTQxZWRlYWU0ZDA0YWY5ZTJmM2U5OWJhNjRkNmM5YzBjNWUzYTdiZiJ9fX0="));
                iscontrol.setItem(14, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NjNGNjMmVhYTVmNzRlMjZjYjA0ZWM0YTQxZWRlYWU0ZDA0YWY5ZTJmM2U5OWJhNjRkNmM5YzBjNWUzYTdiZiJ9fX0="));
                iscontrol.setItem(15, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2NjNGNjMmVhYTVmNzRlMjZjYjA0ZWM0YTQxZWRlYWU0ZDA0YWY5ZTJmM2U5OWJhNjRkNmM5YzBjNWUzYTdiZiJ9fX0="));


                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(iscontrol);
                });
            });
        } else {
            Inventory profile = Bukkit.createInventory(new HeyProfile(), 54, Message.Color("玩家 " + ed.getName() + " 的个人资料"));
            Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                ItemMeta boardMeta = board.getItemMeta();
                boardMeta.setDisplayName(" ");
                board.setItemMeta(boardMeta);
                for (int i = 0; i < 54; i++) {
                    profile.setItem(i, board);
                }

                profile.setItem(8, Skull.getSkull(ed, "&7" + ed.getName()));

                ItemStack gray = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta grayItemMeta = gray.getItemMeta();
                grayItemMeta.setDisplayName(" ");
                board.setItemMeta(grayItemMeta);
                profile.setItem(10, gray);
                profile.setItem(12, gray);
                profile.setItem(28, gray);
                profile.setItem(30, gray);
                profile.setItem(37, gray);
                profile.setItem(39, gray);

                profile.setItem(11, ed.getInventory().getHelmet());
                profile.setItem(20, ed.getInventory().getChestplate());
                profile.setItem(29, ed.getInventory().getLeggings());
                profile.setItem(38, ed.getInventory().getBoots());

                profile.setItem(19, ed.getInventory().getItemInOffHand());
                profile.setItem(21, ed.getInventory().getItemInMainHand());

                profile.setItem(14, new ItemStack(Material.OAK_SIGN));
                profile.setItem(15, new ItemStack(Material.CROSSBOW));
                profile.setItem(23, new ItemStack(Material.COD));
                profile.setItem(24, new ItemStack(Material.DIAMOND_HOE));
                profile.setItem(33, new ItemStack(Material.WATER_BUCKET));
                profile.setItem(42, new ItemStack(Material.IRON_INGOT));

                ItemStack dailyemo = Skull.getSkull(Config.getConfig("emodata.yml").getString(ed.getName() + ".emobase"));
                ItemMeta emoMeta = dailyemo.getItemMeta();
                emoMeta.setDisplayName(Message.Color("&f个性签名"));
                if (Config.getConfig("emodata.yml").getString(ed.getName() + ".text") == null) {
                    List<String> lore = new ArrayList<>();
                    lore.add(Message.Color("&7&o他没有设置签名"));
                    emoMeta.setLore(lore);
                    dailyemo.setItemMeta(emoMeta);
                } else {
                    List<String> lore = new ArrayList<>();
                    lore.add(Message.Color("&7" + Config.getConfig("emodata.yml").getString(ed.getName() + ".text")));
                    emoMeta.setLore(lore);
                    dailyemo.setItemMeta(emoMeta);
                }
                profile.setItem(40, dailyemo);

                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(profile);
                });
            });
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof HeyProfile) {
            e.setCancelled(true);
        }
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof IsControl) {
            e.setCancelled(true);
        }
    }
}
