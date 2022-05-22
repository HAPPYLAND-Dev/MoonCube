package me.xiaozhangup.mooncube.player;

import me.happylandmc.core.message.Message;
import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.Main;
import me.xiaozhangup.mooncube.gui.Emo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
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
            profile.setItem(16, sign);

            Bukkit.getScheduler().runTask(Main.plugin, () -> {
                p.openInventory(profile);
            });
        });
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player && e.getInventory().getHolder() instanceof Emo) {
            e.setCancelled(true);
            if (e.getInventory().getType() == InventoryType.CHEST) {
                if (e.getSlot() == 16) {
                    e.getWhoClicked().closeInventory();
                    Message.PerMessage((Player) e.getWhoClicked(), "&x&8&F&B&C&8&F签名", "&f在聊天栏输入你的新签名,或者输入 c 取消设置");
                    input.put((Player) e.getWhoClicked(), true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (input.get(e.getPlayer())) {
            e.setCancelled(true);
            input.put(e.getPlayer(), false);
            if (e.getMessage().equals("c")) {
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
