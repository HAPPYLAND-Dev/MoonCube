package me.xiaozhangup.mooncube.player;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.database.Island;
import com.iridium.iridiumskyblock.database.User;
import com.iridium.iridiumskyblock.managers.IslandManager;
import me.clip.placeholderapi.PlaceholderAPI;
import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.HeyProfile;
import me.xiaozhangup.mooncube.gui.IsControl;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.gui.tools.Skull;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class Hey implements Listener {

    private final HashMap<Player, Player> target = new HashMap<>();
    IslandManager islandManager = new IslandManager();

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e) {
        if (e.getHand() != EquipmentSlot.HAND || e.getRightClicked().getType() != EntityType.PLAYER) return;
        
        Player ed = (Player) e.getRightClicked();
        Player p = e.getPlayer();
        Material material = p.getInventory().getItemInMainHand().getType();
        if (Config.BLACK_ITEMS.contains(material.toString()) || material.toString().endsWith("POTION")) {
            return;
        }
        target.put(p, ed);
        if (p.isSneaking()) {
            openIsControl(p, ed);
        } else {
            openProfile(p, ed);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player player) {
            if (e.getInventory().getHolder() instanceof HeyProfile) {
                e.setCancelled(true);
                if (e.getRawSlot() == 42) {
                    executeCommandAndClose(player, "is visit");
                }
                else if (e.getRawSlot() == 43) {
                    executeCommandAndClose(player, "trade");
                }
            }
            if (e.getInventory().getHolder() instanceof IsControl) {
                e.setCancelled(true);
                if (e.getRawSlot() == 10) {
                    executeCommandAndClose(player, "is visit");
                }
                else if (e.getRawSlot() == 11) {
                    executeCommandAndClose(player, "is uninvite");
                }
                else if (e.getRawSlot() == 12) {
                    executeCommandAndClose(player, "is invite");
                }
                else if (e.getRawSlot() == 13) {
                    executeCommandAndClose(player, "is transfer");
                }
                else if (e.getRawSlot() == 14 && e.getClick() == ClickType.RIGHT) {
                    executeCommandAndOpen(player, "is unban");
                }
                else if (e.getRawSlot() == 14 && e.getClick() == ClickType.LEFT) {
                    executeCommandAndOpen(player, "is ban");
                }
                else if (e.getRawSlot() == 15 && e.getClick() == ClickType.RIGHT) {
                    executeCommandAndOpen(player, "is untrust");
                }
                else if (e.getRawSlot() == 15 && e.getClick() == ClickType.LEFT) {
                    executeCommandAndOpen(player, "is trust");
                }
            }
        }
    }

    private void executeCommandAndOpen(Player player, String command) {
        Bukkit.dispatchCommand(player, command + " " + target.get(player).getName());
        openIsControl(player, target.get(player));
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
    }

    private void executeCommandAndClose(Player player, String command) {
        Bukkit.dispatchCommand(player, command + " " + target.get(player).getName());
        player.closeInventory();
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
    }

    public static void openIsControl(Player p, Player ed) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory iscontrol = Bukkit.createInventory(new IsControl(), 27, IString.addColor("对于玩家 " + ed.getName() + " 的岛屿选项"));
        User user = IridiumSkyblock.getInstance().getUserManager().getUser(p);
        Optional<Island> island = user.getIsland();
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {
            ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta boardMeta = board.getItemMeta();
            boardMeta.setDisplayName(" ");
            board.setItemMeta(boardMeta);
            for (int i = 0; i < 27; i++) {
                iscontrol.setItem(i, board);
            }

            iscontrol.setItem(8, Skull.getSkull(ed, "&7" + ed.getName()));


            iscontrol.setItem(10, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjJiYjM3ZjAzMjlmNmJiNWMzY2Y1NjFmZWNhMGJlMTAzNGM5OWE0OGY1ZTFjNzExZGM5YjgzNWRjMzk5NTdhZSJ9fX0=", "&x&F&5&F&5&F&5参观他的岛屿"));
            iscontrol.setItem(11, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMyYzFmMjUyNjNhZWJkNTdiODBiM2I3YjJiZTkxOGUxNGEyNzc3NmYxZTk5NTE3NDk1MjczZTU4M2NjNmY2ZCJ9fX0=", "&x&F&5&F&5&F&5取消邀请他成为岛员"));
            iscontrol.setItem(12, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlmZmRkOWYwN2M3ZWFjMTdhMGIwNWJjNzk2YmE0ZmNlMTk0MWM3MDVmMWE3ZmM4YjQ2YzI4ODI3MTIzZGU1MiJ9fX0=", "&x&F&5&F&5&F&5邀请他成为岛员"));
            iscontrol.setItem(13, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWNiNTQ4MDFmNmJmOWYwYWZmOWI5ZWRhOGJjOTE2NDJmODhhZWVhYzNjM2RlNWJiODA3NWRjYTI5NGU1MGU2MiJ9fX0=", "&x&3&C&B&3&7&1将自己的岛屿转让给他"));

            ItemStack ban = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjViMzZjNmYwMTMzYmVhMzgwY2NmYWE0MGJkMDlkM2IwNGFiN2Q3NWRhZmViNzFiMzNmM2ZhMThkNmU0OWJiMCJ9fX0=", "&c将他从自己岛屿拉黑");
            ItemMeta banMeta = ban.getItemMeta();
            List<String> blore = new ArrayList<>();
            blore.add(IString.addColor("&7左键 - 拉黑玩家"));
            blore.add(IString.addColor("&7右键 - 取消拉黑玩家"));
            User targetUser = IridiumSkyblock.getInstance().getUserManager().getUser(ed);
            if (island.get().equals(targetUser.getIsland().orElse(null)) || IridiumSkyblock.getInstance().getIslandManager().getIslandTrusted(island.get(), targetUser).isPresent()) {
                blore.add(" ");
                blore.add(IString.addColor("&c✘ 他目前为你的岛屿信任者"));
                blore.add(IString.addColor("&c因此你无法拉黑他"));
            }
            blore.add(" ");
            if (IridiumSkyblock.getInstance().getIslandManager().isBannedOnIsland(island.get(), targetUser)) {
                blore.add(IString.addColor("&a✔ 本玩家已被你拉黑"));
            } else {
                blore.add(IString.addColor("&c✘ 本玩家没有被拉黑"));
            }
            banMeta.setLore(blore);
            ban.setItemMeta(banMeta);
            iscontrol.setItem(14, ban);

            ItemStack trust = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjI3ODY2ZDI2NjkzYWE5N2M4NDRiNjJmYzlkMDJkNmY5ZjJjMjE3MGJkZWRkZmJlOGI3NjUzMDU0YTdhNGE0YiJ9fX0=", "&e将他标记为信任者");
            ItemMeta trustMeta = trust.getItemMeta();
            List<String> tlore = new ArrayList<>();
            tlore.add(IString.addColor("&7左键 - 信任玩家"));
            tlore.add(IString.addColor("&7右键 - 取消信任玩家"));
            tlore.add(" ");
            if (island.get().equals(targetUser.getIsland().orElse(null)) || IridiumSkyblock.getInstance().getIslandManager().getIslandTrusted(island.get(), targetUser).isPresent()) {
                tlore.add(IString.addColor("&a✔ 本玩家已是信任玩家"));
            } else {
                tlore.add(IString.addColor("&c✘ 本玩家还不是信任玩家"));
            }
            trustMeta.setLore(tlore);
            trust.setItemMeta(trustMeta);
            iscontrol.setItem(15, trust);

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> {
                p.openInventory(iscontrol);
            });
        });
    }

    public static void openProfile(Player p, Player ed) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        Inventory profile = Bukkit.createInventory(new HeyProfile(), 54, IString.addColor("玩家 " + ed.getName() + " 的个人资料"));
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {
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

            PlayerInventory inventory = ed.getInventory();
            profile.setItem(11, inventory.getHelmet());
            profile.setItem(20, inventory.getChestplate());
            profile.setItem(29, inventory.getLeggings());
            profile.setItem(38, inventory.getBoots());

            profile.setItem(19, inventory.getItemInOffHand());
            profile.setItem(21, inventory.getItemInMainHand());

            ItemStack sign = new ItemStack(Material.HEART_OF_THE_SEA);
            ItemMeta signMeta = sign.getItemMeta();
            signMeta.setDisplayName(IString.addColor("&x&7&F&F&F&D&4岛屿数据统计"));
            List<String> olore = new ArrayList<>();
            olore.add(" ");
            olore.add(IString.addColor("&x&3&C&B&3&7&1岛屿水晶 &7: " + PlaceholderAPI.setPlaceholders(ed, "%iridiumskyblock_island_bank_crystals%")));
            olore.add(IString.addColor("&x&6&B&8&E&2&3岛屿大小 &7: " + PlaceholderAPI.setPlaceholders(ed, "%iridiumskyblock_island_upgrade_size_dimensions%")));
            olore.add(IString.addColor("&x&8&0&8&0&0&0岛屿价值 &7: " + PlaceholderAPI.setPlaceholders(ed, "%iridiumskyblock_island_value%")));
            olore.add(IString.addColor("&x&0&0&8&0&8&0岛屿等级 &7: " + PlaceholderAPI.setPlaceholders(ed, "%iridiumskyblock_island_level%")));
            signMeta.setLore(olore);
            sign.setItemMeta(signMeta);
            profile.setItem(14, sign);

            ItemStack skills = new ItemStack(Material.CROSSBOW);
            ItemMeta skillsMeta = skills.getItemMeta();
            skillsMeta.setDisplayName(IString.addColor("&x&C&D&8&5&3&F个人技能数据"));
            List<String> slore = new ArrayList<>();
            slore.add(" ");
            slore.add(IString.addColor("&4力量 ➽ &7: " + PlaceholderAPI.setPlaceholders(ed, "%aureliumskills_strength%")));
            slore.add(IString.addColor("&c生命值 ❤ &7: " + PlaceholderAPI.setPlaceholders(ed, "%aureliumskills_health%")));
            slore.add(IString.addColor("&6再生 ❥ &7: " + PlaceholderAPI.setPlaceholders(ed, "%aureliumskills_regeneration%")));
            slore.add(IString.addColor("&2幸运 ☘ &7: " + PlaceholderAPI.setPlaceholders(ed, "%aureliumskills_luck%")));
            slore.add(IString.addColor("&9智慧 ✿ &7: " + PlaceholderAPI.setPlaceholders(ed, "%aureliumskills_wisdom%")));
            slore.add(IString.addColor("&5韧性 ✦ &7: " + PlaceholderAPI.setPlaceholders(ed, "%aureliumskills_toughness%")));
            skillsMeta.setLore(slore);
            skills.setItemMeta(skillsMeta);
            profile.setItem(15, skills);

            ItemStack maper = new ItemStack(Material.COD);
            ItemMeta maperMeta = maper.getItemMeta();
            maperMeta.setDisplayName(IString.addColor("&x&F&F&E&B&C&D其他个人数据"));
            List<String> mlore = new ArrayList<>();
            mlore.add(" ");
            mlore.add(IString.addColor("&x&F&F&D&E&A&D金钱 &7: ") + PlaceholderAPI.setPlaceholders(ed, "%vault_eco_balance_fixed%"));
            mlore.add(IString.addColor("&x&F&5&D&E&B&3经验 &7: ") + PlaceholderAPI.setPlaceholders(ed, "%player_exp%"));
            mlore.add(IString.addColor("&x&D&E&B&8&8&7生命 &7: ") + PlaceholderAPI.setPlaceholders(ed, "%player_health%"));
            mlore.add(IString.addColor("&x&D&2&B&4&8&C当前正在 &7: ") + PlaceholderAPI.setPlaceholders(ed, "%multiverse_world_alias%"));
            maperMeta.setLore(mlore);
            maper.setItemMeta(maperMeta);
            profile.setItem(24, maper);

            ItemStack visit = new ItemStack(Material.ENDER_EYE);
            ItemMeta visitMeta = visit.getItemMeta();
            visitMeta.setDisplayName(IString.addColor("&x&9&A&C&D&3&2参观它的岛屿"));
            List<String> alore = new ArrayList<>();
            alore.add(" ");
            alore.add(IString.addColor("&7不是所有人的岛屿都能参观!"));
            alore.add(IString.addColor("&7例如岛主禁止访客参观后,"));
            alore.add(IString.addColor("&7你就无法参观它的岛屿."));
            visitMeta.setLore(alore);
            visit.setItemMeta(visitMeta);
            profile.setItem(42, visit);

            ItemStack trade = new ItemStack(Material.MINECART);
            ItemMeta tradeMeta = trade.getItemMeta();
            tradeMeta.setDisplayName(IString.addColor("&x&D&C&D&C&D&C向他发起交易"));
            List<String> tlore = new ArrayList<>();
            tlore.add(" ");
            tlore.add(IString.addColor("&7耐心等待对方同意即可."));
            tlore.add(IString.addColor("&7随便放点什么,万一别人看中了呢?"));
            tradeMeta.setLore(tlore);
            trade.setItemMeta(tradeMeta);
            profile.setItem(43, trade);

            ItemStack dailyemo = Skull.getSkull(ConfigManager.getConfig("emodata").getString(ed.getName() + ".emobase"));
            ItemMeta emoMeta = dailyemo.getItemMeta();
            emoMeta.setDisplayName(IString.addColor("&f个性签名"));
            if (ConfigManager.getConfig("emodata").getString(ed.getName() + ".text") == null) {
                List<String> lore = new ArrayList<>();
                lore.add(IString.addColor("&7&o他没有设置签名"));
                emoMeta.setLore(lore);
                dailyemo.setItemMeta(emoMeta);
            } else {
                List<String> lore = new ArrayList<>();
                lore.add(IString.addColor("&7" + ConfigManager.getConfig("emodata").getString(ed.getName() + ".text")));
                emoMeta.setLore(lore);
                dailyemo.setItemMeta(emoMeta);
            }
            profile.setItem(40, dailyemo);

            Bukkit.getScheduler().runTask(MoonCube.plugin, () -> {
                p.openInventory(profile);
            });
        });
    }
}
