package me.xiaozhangup.mooncube.player.fastkey;

import me.xiaozhangup.mooncube.config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.HashMap;
import java.util.Map;

public class Ketboard implements Listener {

    private final Map<Player, Long> timeSnap = new HashMap<>();
    public static Map<Integer, String> shortSnap = new HashMap<>();

    public static String fCommand = null;

    //TODO
    //高级快捷键优待实现，例如Shift+F

    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent e) {
            timeSnap.put(e.getPlayer() , System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerKeyinput(PlayerItemHeldEvent e) {
        Player p = e.getPlayer();
        if (p.isSneaking() && shortSnap.get(e.getNewSlot()) != null && timeSnap.get(p) != null && System.currentTimeMillis() - timeSnap.get(p) <= 220) {
            e.setCancelled(true);
            Bukkit.dispatchCommand(p , shortSnap.get(e.getNewSlot()));
        }
    }

    @EventHandler
    public void onPlayerSwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        if (fCommand != null && p.isSneaking() && timeSnap.get(p) != null && System.currentTimeMillis() - timeSnap.get(p) <= 220) {
            e.setCancelled(true);
            Bukkit.dispatchCommand(p , fCommand);
        }
    }

    public static void loadKey() {
        for (int i = 0 ; i < 9 ; i ++) {
            if (ConfigManager.getConfig("keymap").getString(String.valueOf(i)) != null) {
                shortSnap.put(i , ConfigManager.getConfig("keymap").getString(String.valueOf(i)));
            }
        }
        if (ConfigManager.getConfig("keymap").getString("F") != null) {
            fCommand = ConfigManager.getConfig("keymap").getString("F");
        }
    }
}
