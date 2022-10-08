package me.xiaozhangup.mooncube.player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.xiaozhangup.mooncube.MoonCube;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class EcoWatch extends PlaceholderExpansion implements Listener {

    public static final Economy ECONOMY = MoonCube.getEconomy();
    private static HashMap<Player, Double> pot = new HashMap<>();
    private static HashMap<Player, Double> mem = new HashMap<>();

    @Override
    public @NotNull String getIdentifier() {
        return "mooncube";
    }

    @Override
    public @NotNull String getAuthor() {
        return "xiaozhangup";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player p, @NotNull String identifier) {
        if ("pot".equals(identifier)) {
            if (p == null) {
                return "";
            }
            double eco = pot.get(p);
            BigDecimal bd = new BigDecimal(eco);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            if (eco > 0) {
                pot.put(p, 0.0);
                return "&a+" + bd;
            }
            if (eco < 0) {
                pot.put(p, 0.0);
                return "&c" + bd;
            }
            return "";
        }
        return "";
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        pot.put(player, 0.0);
        mem.put(player, MoonCube.getEconomy().getBalance(player));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        pot.remove(player);
        mem.remove(player);
    }

    public static void run() {
        Bukkit.getOnlinePlayers().forEach((p) -> {
            pot.put(p, 0.0);
            mem.put(p, MoonCube.getEconomy().getBalance(p));
        });
        Bukkit.getScheduler().runTaskTimerAsynchronously(MoonCube.plugin, () -> {
            Bukkit.getOnlinePlayers().forEach((p) -> {
                double last = mem.get(p);
                double curry = ECONOMY.getBalance(p);
                double ee = pot.get(p);
                pot.put(p, ee + curry - last);
                mem.put(p, curry);
            });
        }, 1L, 20L);

    }
}
