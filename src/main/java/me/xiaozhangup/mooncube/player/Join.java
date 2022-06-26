package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import me.xiaozhangup.mooncube.mobs.Spawner;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.io.IOException;

public class Join implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPlayedBefore()) {
            Spawner.dailyCoin.putIfAbsent(e.getPlayer(), 0.0);
            Bukkit.getScheduler().runTaskLater(MoonCube.plugin , () -> {
                String book = ConfigManager.getConfig("book").getString("book");
                if (book != null) {
                    ItemStack itemStack = new ItemStack(Material.WRITTEN_BOOK);
                    BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                    bookMeta.setAuthor("HAPPYLAND Studio");
                    bookMeta.setTitle("Server Message");
                    bookMeta.addPage(IString.addColor(book));
                    itemStack.setItemMeta(bookMeta);
                    e.getPlayer().openBook(itemStack);
                }
            } , 15L);

        } else {
            Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {
                FileConfiguration configuration = ConfigManager.getConfig("emodata");
                configuration.set(e.getPlayer().getName() + ".emobase", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjViOTVkYTEyODE2NDJkYWE1ZDAyMmFkYmQzZTdjYjY5ZGMwOTQyYzgxY2Q2M2JlOWMzODU3ZDIyMmUxYzhkOSJ9fX0=");
                try {
                    configuration.save(new File(MoonCube.plugin.getDataFolder(), "emodata.yml"));
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                e.getPlayer().getInventory().clear();
                for (int i = 0 ; i < 37 ; i ++) {
                    if (ConfigManager.getConfig("kit").getItemStack("Slot." + i) == null) continue;
                    e.getPlayer().getInventory().setItem(i , ConfigManager.getConfig("kit").getItemStack("Slot." + i));
                }
            });
        }
    }
}
