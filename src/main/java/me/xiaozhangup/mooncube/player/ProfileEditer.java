package me.xiaozhangup.mooncube.player;

import me.happylandmc.core.Skull;
import me.happylandmc.core.message.Message;
import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.gui.Emo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileEditer implements Listener {

    HashMap<Player, Boolean> input = new HashMap<>();

    public static void openProfile(Player p) {
        Inventory profile = Bukkit.createInventory(new Emo(), 45, Message.Color("编辑你的个人资料"));
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
            ItemStack board = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta boardMeta = board.getItemMeta();
            boardMeta.setDisplayName(" ");
            board.setItemMeta(boardMeta);
            for (int i = 0; i < 45; i++) {
                profile.setItem(i, board);
            }

            ItemStack sign = new ItemStack(Material.NAME_TAG);
            ItemMeta signMeta = sign.getItemMeta();
            signMeta.setDisplayName(Message.Color("&f单击设置你的个性签名"));
            if (Config.getConfig("emodata.yml").getString(p.getName() + ".text") == null) {
                List<String> lore = new ArrayList<>();
                lore.add(Message.Color("&7&o你没有设置签名"));
                signMeta.setLore(lore);
                sign.setItemMeta(signMeta);
            } else {
                List<String> lore = new ArrayList<>();
                lore.add(Message.Color("&7" + Config.getConfig("emodata.yml").getString(p.getName() + ".text")));
                signMeta.setLore(lore);
                sign.setItemMeta(signMeta);
            }
            sign.setItemMeta(signMeta);

            profile.setItem(10, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVhNDZmODMzNGU0OWQyNzMzODRlYjcyYjJhYzE1ZTI0YTY0MGQ3NjQ4ZTRiMjhjMzQ4ZWZjZTkzZGM5N2FiIn19fQ==", "&f看淡"));
            profile.setItem(11, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM0OWJkNDEzODYwM2E5MDRmYWFmZTEzNjZmNmJmYjczZWJkMTY3NDA1OGE4OTg1NjYyOGViMmM5NWMyMCJ9fX0=", "&f嘻皮"));
            profile.setItem(12, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMyNjcyYWFhZTU4ZjNkMTg1MmQxOWI4NDIyY2FmNzBiMzI1ODJmOGRlM2ZjYjVjN2MyNGRhY2I3ZWJjMyJ9fX0=", "&f伤心"));
            profile.setItem(13, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMyZmUxMjFhNjNlYWFiZDk5Y2VkNmQxYWNjOTE3OTg2NTJkMWVlODA4NGQyZjkxMjdkOGEzMTVjYWQ1Y2U0In19fQ==", "&f哭泣"));
            profile.setItem(14, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGIwNDQ3NDY0OWY5MDdhY2Y2YmEwNGY4Y2VhNjViMjZhOTEwY2UyNGJhYjJjNmEzZmE2NzU5ZWFjZmZhZiJ9fX0=", "&f疯狂"));
            profile.setItem(19, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU4Njg1MjlmYmY0YmU2MjkzNzEyNzViMTEzOGRhYjkyOTU3NjAyMTcxNmVlNzM3ZGIxMjYzNGFhMTI1YWYzIn19fQ==", "&f惊喜"));
            profile.setItem(20, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE2ZjNkY2U5NzdkOGI3OTdlMWU0NzZmNWFiOTM2MTllZDJmMmIyMWE0OWFjOTM3NDMxNDBjZjY3YTA4OCJ9fX0=", "&f回想"));
            profile.setItem(21, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZmNDlhODY0NWRkNjhiNjhmMGJhZmEyMjY1YmFkOTMyZjc1MWM1ODRlNGY3MzFmZDcxNzNhNWU1YjI2ZDMifX19", "&f斜嘴笑"));
            profile.setItem(22, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VmNTc1NjI5YTI2ODlkNjNhM2EzZTkxYmQzNDJlYzNmNzhiNGYzOTc2ODdjMDgzM2JmNmQ2NGJmMjZkMTJlOCJ9fX0=", "&f酷"));
            profile.setItem(23, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4ZjVmYjM4N2NhNjZmYzJmNjViOTFmY2IyMzE2MDQ1NDhlODU2NTg5NWJiOTZjNjc2OTg0MjA1ZTZmMTkifX19", "&f兴奋"));
            profile.setItem(28, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM5YzNkZjdhNjI4YWY4ZDc1MWVjY2ExOTc2NDJjZGMxYTA3YzMwZTMyODliMmQzMjYxZjdhNjVjZjM5NWIifX19", "&f卒"));
            profile.setItem(29, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkNGJlYTM2NmFjYTU4ZGQ1YjIyZTk0MGJjZGQ0YmE0NWJmODg0MjFmNmQ4MzExNThiODc5ZjJjOGFiY2UxOCJ9fX0=", "&f怒火"));
            profile.setItem(30, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNjZDU0NzJhNDZmNDZlNGNkZmRhOWFkZWEyMzIwY2NjZmJhZTExMTk4YjZhYWUxNjNkMTdjNGI1YjQ2NjZkIn19fQ==", "&f紧张"));
            profile.setItem(31, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlYTk5YWQ5NWI1NzAxNzVmZGEyNWMzYTY5Nzg4ZDZhOWI4NTRhYTEzZjhhNWZmNjNmNmVmZWRmNTgxZGZiNiJ9fX0=", "&f思索"));
            profile.setItem(32, Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFiZmFiZjBhZGIyYTYxNWU5MDFmZTAwNDBlMzIyYzUxZmI3ZDExYzgyY2ZhZmEyMWU1MjIxYTVmNmEyZTAzMCJ9fX0=", "&f吐了"));

            ItemStack dailyemo = Skull.getSkull(Config.getConfig("emodata.yml").getString(p.getName() + ".emobase"));
            ItemMeta emoMeta = dailyemo.getItemMeta();
            emoMeta.setDisplayName(Message.Color("&f当前个性表情"));
            List<String> lore = new ArrayList<>();
            lore.add(Message.Color("&7从左侧表情点选来更换"));
            emoMeta.setLore(lore);
            dailyemo.setItemMeta(emoMeta);
            profile.setItem(34, dailyemo);

            profile.setItem(16, sign);

            Bukkit.getScheduler().runTask(Main.plugin, () -> p.openInventory(profile));
        });
    }

    public static void setEmo(Player p, String s) {
        FileConfiguration configuration = Config.getConfig("emodata.yml");
        configuration.set(p.getName() + ".emobase", s);
        try {
            configuration.save(new File(Main.plugin.getDataFolder(), "emodata.yml"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
        openProfile(p);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p && e.getInventory().getHolder() instanceof Emo) {
            e.setCancelled(true);
            if (e.getRawSlot() == 16) {
                e.getWhoClicked().closeInventory();
                Message.PerMessage((Player) e.getWhoClicked(), "&x&8&F&B&C&8&F签名", "&f在聊天栏输入你的新签名,或者输入 c 取消设置");
                input.put((Player) e.getWhoClicked(), true);
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
            } else {
                Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
                    if (e.getRawSlot() == 10) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVhNDZmODMzNGU0OWQyNzMzODRlYjcyYjJhYzE1ZTI0YTY0MGQ3NjQ4ZTRiMjhjMzQ4ZWZjZTkzZGM5N2FiIn19fQ==");
                    }
                    if (e.getRawSlot() == 11) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM0OWJkNDEzODYwM2E5MDRmYWFmZTEzNjZmNmJmYjczZWJkMTY3NDA1OGE4OTg1NjYyOGViMmM5NWMyMCJ9fX0=");
                    }
                    if (e.getRawSlot() == 12) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMyNjcyYWFhZTU4ZjNkMTg1MmQxOWI4NDIyY2FmNzBiMzI1ODJmOGRlM2ZjYjVjN2MyNGRhY2I3ZWJjMyJ9fX0=");
                    }
                    if (e.getRawSlot() == 13) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMyZmUxMjFhNjNlYWFiZDk5Y2VkNmQxYWNjOTE3OTg2NTJkMWVlODA4NGQyZjkxMjdkOGEzMTVjYWQ1Y2U0In19fQ==");
                    }
                    if (e.getRawSlot() == 14) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGIwNDQ3NDY0OWY5MDdhY2Y2YmEwNGY4Y2VhNjViMjZhOTEwY2UyNGJhYjJjNmEzZmE2NzU5ZWFjZmZhZiJ9fX0=");
                    }
                    if (e.getRawSlot() == 19) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU4Njg1MjlmYmY0YmU2MjkzNzEyNzViMTEzOGRhYjkyOTU3NjAyMTcxNmVlNzM3ZGIxMjYzNGFhMTI1YWYzIn19fQ==");
                    }
                    if (e.getRawSlot() == 20) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE2ZjNkY2U5NzdkOGI3OTdlMWU0NzZmNWFiOTM2MTllZDJmMmIyMWE0OWFjOTM3NDMxNDBjZjY3YTA4OCJ9fX0=");
                    }
                    if (e.getRawSlot() == 21) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZmNDlhODY0NWRkNjhiNjhmMGJhZmEyMjY1YmFkOTMyZjc1MWM1ODRlNGY3MzFmZDcxNzNhNWU1YjI2ZDMifX19");
                    }
                    if (e.getRawSlot() == 22) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VmNTc1NjI5YTI2ODlkNjNhM2EzZTkxYmQzNDJlYzNmNzhiNGYzOTc2ODdjMDgzM2JmNmQ2NGJmMjZkMTJlOCJ9fX0=");
                    }
                    if (e.getRawSlot() == 23) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ4ZjVmYjM4N2NhNjZmYzJmNjViOTFmY2IyMzE2MDQ1NDhlODU2NTg5NWJiOTZjNjc2OTg0MjA1ZTZmMTkifX19");
                    }
                    if (e.getRawSlot() == 28) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM5YzNkZjdhNjI4YWY4ZDc1MWVjY2ExOTc2NDJjZGMxYTA3YzMwZTMyODliMmQzMjYxZjdhNjVjZjM5NWIifX19");
                    }
                    if (e.getRawSlot() == 29) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkNGJlYTM2NmFjYTU4ZGQ1YjIyZTk0MGJjZGQ0YmE0NWJmODg0MjFmNmQ4MzExNThiODc5ZjJjOGFiY2UxOCJ9fX0=");
                    }
                    if (e.getRawSlot() == 30) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNjZDU0NzJhNDZmNDZlNGNkZmRhOWFkZWEyMzIwY2NjZmJhZTExMTk4YjZhYWUxNjNkMTdjNGI1YjQ2NjZkIn19fQ==");
                    }
                    if (e.getRawSlot() == 31) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlYTk5YWQ5NWI1NzAxNzVmZGEyNWMzYTY5Nzg4ZDZhOWI4NTRhYTEzZjhhNWZmNjNmNmVmZWRmNTgxZGZiNiJ9fX0=");
                    }
                    if (e.getRawSlot() == 32) {
                        setEmo(p, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFiZmFiZjBhZGIyYTYxNWU5MDFmZTAwNDBlMzIyYzUxZmI3ZDExYzgyY2ZhZmEyMWU1MjIxYTVmNmEyZTAzMCJ9fX0=");
                    }
                });
            }

        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (input.get(e.getPlayer()) == null) return;
        if (input.get(e.getPlayer())) {
            e.setCancelled(true);
            input.put(e.getPlayer(), false);
            if ("c".equals(e.getMessage())) {
                Message.PerMessage(e.getPlayer(), "&x&8&F&B&C&8&F签名", "&c签名设置已取消");
                openProfile(e.getPlayer());
            } else {
                Message.PerMessage(e.getPlayer(), "&x&8&F&B&C&8&F签名", "&f签名设置成功!");
                FileConfiguration configuration = Config.getConfig("emodata.yml");
                configuration.set(e.getPlayer().getName() + ".text", e.getMessage());
                try {
                    configuration.save(new File(Main.plugin.getDataFolder(), "emodata.yml"));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                openProfile(e.getPlayer());
            }
        }
    }
}
