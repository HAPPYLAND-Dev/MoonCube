package me.xiaozhangup.mooncube.player;

import me.clip.placeholderapi.PlaceholderAPI;
import me.happylandmc.core.Skull;
import me.happylandmc.core.message.Message;
import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.gui.HeyProfile;
import me.xiaozhangup.mooncube.gui.IsControl;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Hey implements Listener {

    private final HashMap<Player, Player> target = new HashMap<>();

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() != EntityType.PLAYER) {
            return;
        }
        Player ed = (Player) e.getRightClicked();
        Player p = e.getPlayer();
        Material material = p.getInventory().getItemInMainHand().getType();
        if (Config.BLACK_ITEMS.contains(material.toString()) || material.toString().endsWith("POTION")) {
            return;
        }
        target.put(p, ed);
        if (p.isSneaking()) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
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


                iscontrol.setItem(10, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjJiYjM3ZjAzMjlmNmJiNWMzY2Y1NjFmZWNhMGJlMTAzNGM5OWE0OGY1ZTFjNzExZGM5YjgzNWRjMzk5NTdhZSJ9fX0=" , "&x&F&5&F&5&F&5参观他的岛屿"));
                iscontrol.setItem(11, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMyYzFmMjUyNjNhZWJkNTdiODBiM2I3YjJiZTkxOGUxNGEyNzc3NmYxZTk5NTE3NDk1MjczZTU4M2NjNmY2ZCJ9fX0=" , "&x&F&5&F&5&F&5取消邀请他成为岛员"));
                iscontrol.setItem(12, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmZmRkOWYwN2M3ZWFjMTdhMGIwNWJjNzk2YmE0ZmNlMTk0MWM3MDVmMWE3ZmM4YjQ2YzI4ODI3MTIzZGU1MiJ9fX0=" , "&x&F&5&F&5&F&5邀请他成为岛员"));
                iscontrol.setItem(13, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWNiNTQ4MDFmNmJmOWYwYWZmOWI5ZWRhOGJjOTE2NDJmODhhZWVhYzNjM2RlNWJiODA3NWRjYTI5NGU1MGU2MiJ9fX0=" , "&x&3&C&B&3&7&1将自己的岛屿转让给他"));

                ItemStack ban = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjViMzZjNmYwMTMzYmVhMzgwY2NmYWE0MGJkMDlkM2IwNGFiN2Q3NWRhZmViNzFiMzNmM2ZhMThkNmU0OWJiMCJ9fX0=" , "&c将他从自己岛屿拉黑");
                ItemMeta banMeta = ban.getItemMeta();
                List<String> blore = new ArrayList<>();
                blore.add(Message.Color("&7左键 - 拉黑玩家"));
                blore.add(Message.Color("&7右键 - 取消拉黑玩家"));
                banMeta.setLore(blore);
                ban.setItemMeta(banMeta);
                iscontrol.setItem(14, ban);

                ItemStack trust = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI3ODY2ZDI2NjkzYWE5N2M4NDRiNjJmYzlkMDJkNmY5ZjJjMjE3MGJkZWRkZmJlOGI3NjUzMDU0YTdhNGE0YiJ9fX0=" , "&e将他标记为信任者");
                ItemMeta trustMeta = trust.getItemMeta();
                List<String> tlore = new ArrayList<>();
                tlore.add(Message.Color("&7左键 - 信任玩家"));
                tlore.add(Message.Color("&7右键 - 取消信任玩家"));
                trustMeta.setLore(tlore);
                trust.setItemMeta(trustMeta);
                iscontrol.setItem(15, trust);

                Bukkit.getScheduler().runTask(Main.plugin, () -> {
                    p.openInventory(iscontrol);
                });
            });
        } else {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
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

                ItemStack sign = new ItemStack(Material.HEART_OF_THE_SEA);
                ItemMeta signMeta = sign.getItemMeta();
                signMeta.setDisplayName(Message.Color("&x&7&F&F&F&D&4岛屿数据统计"));
                List<String> olore = new ArrayList<>();
                olore.add(" ");
                olore.add(Message.Color("&x&3&C&B&3&7&1岛屿水晶 &7: " + PlaceholderAPI.setPlaceholders(p, "%iridiumskyblock_island_bank_crystals%")));
                olore.add(Message.Color("&x&6&B&8&E&2&3岛屿大小 &7: " + PlaceholderAPI.setPlaceholders(p, "%iridiumskyblock_island_upgrade_size_dimensions%")));
                olore.add(Message.Color("&x&8&0&8&0&0&0岛屿价值 &7: " + PlaceholderAPI.setPlaceholders(p, "%iridiumskyblock_island_value%")));
                olore.add(Message.Color("&x&0&0&8&0&8&0岛屿等级 &7: " + PlaceholderAPI.setPlaceholders(p, "%iridiumskyblock_island_level%")));
                signMeta.setLore(olore);
                sign.setItemMeta(signMeta);
                profile.setItem(14, sign);

                ItemStack skills = new ItemStack(Material.CROSSBOW);
                ItemMeta skillsMeta = skills.getItemMeta();
                skillsMeta.setDisplayName(Message.Color("&x&C&D&8&5&3&F个人技能数据"));
                List<String> slore = new ArrayList<>();
                slore.add(" ");
                slore.add(Message.Color("&4力量 ➽ &7: " + PlaceholderAPI.setPlaceholders(p, "%aureliumskills_strength%")));
                slore.add(Message.Color("&c生命值 ❤ &7: " + PlaceholderAPI.setPlaceholders(p, "%aureliumskills_health%")));
                slore.add(Message.Color("&6再生 ❥ &7: " + PlaceholderAPI.setPlaceholders(p, "%aureliumskills_regeneration%")));
                slore.add(Message.Color("&2幸运 ☘ &7: " + PlaceholderAPI.setPlaceholders(p, "%aureliumskills_luck%")));
                slore.add(Message.Color("&9智慧 ✿ &7: " + PlaceholderAPI.setPlaceholders(p, "%aureliumskills_wisdom%")));
                slore.add(Message.Color("&5韧性 ✦ &7: " + PlaceholderAPI.setPlaceholders(p, "%aureliumskills_toughness%")));
                skillsMeta.setLore(slore);
                skills.setItemMeta(skillsMeta);
                profile.setItem(15, skills);

                ItemStack maper = new ItemStack(Material.COD);
                ItemMeta maperMeta = maper.getItemMeta();
                maperMeta.setDisplayName(Message.Color("&x&F&F&E&B&C&D其他个人数据"));
                List<String> mlore = new ArrayList<>();
                mlore.add(" ");
                mlore.add(Message.Color("&x&F&F&D&E&A&D金钱 &7: ") + PlaceholderAPI.setPlaceholders(p, "%vault_eco_balance_fixed%"));
                mlore.add(Message.Color("&x&F&5&D&E&B&3经验 &7: ") + PlaceholderAPI.setPlaceholders(p, "%player_exp%"));
                mlore.add(Message.Color("&x&D&E&B&8&8&7生命 &7: ") + PlaceholderAPI.setPlaceholders(p, "%player_health%"));
                mlore.add(Message.Color("&x&D&2&B&4&8&C当前正在 &7: ") + PlaceholderAPI.setPlaceholders(p, "%multiverse_world_alias%"));
                maperMeta.setLore(mlore);
                maper.setItemMeta(maperMeta);
                profile.setItem(24, maper);

                ItemStack visit = new ItemStack(Material.ENDER_EYE);
                ItemMeta visitMeta = visit.getItemMeta();
                visitMeta.setDisplayName(Message.Color("&x&9&A&C&D&3&2参观它的岛屿"));
                List<String> alore = new ArrayList<>();
                alore.add(" ");
                alore.add(Message.Color("&7不是所有人的岛屿都能参观!"));
                alore.add(Message.Color("&7例如岛主禁止访客参观后,"));
                alore.add(Message.Color("&7你就无法参观它的岛屿."));
                visitMeta.setLore(alore);
                visit.setItemMeta(visitMeta);
                profile.setItem(42, visit);

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
            if (e.getRawSlot() == 42) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is visit " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
        }
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof IsControl) {
            e.setCancelled(true);
            if (e.getRawSlot() == 10) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is visit " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 11) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is uninvite " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 12) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is invite " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 13) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is transfer " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 14 && e.getClick() == ClickType.RIGHT) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is unban " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 14 && e.getClick() == ClickType.LEFT) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is ban " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 15 && e.getClick() == ClickType.RIGHT) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is untrust " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }
            if (e.getRawSlot() == 15 && e.getClick() == ClickType.LEFT) {
                Bukkit.dispatchCommand(e.getWhoClicked(), "is trust " + target.get((Player) e.getWhoClicked()).getName());
                e.getWhoClicked().closeInventory();
                ((Player) e.getWhoClicked()).playSound(e.getWhoClicked() , Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            }

        }
    }
}
