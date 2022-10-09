package me.xiaozhangup.mooncube.player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.xiaozhangup.mooncube.MoonCube;
import net.ess3.api.events.UserBalanceUpdateEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class EcoWatch extends PlaceholderExpansion implements Listener {

    private static final HashMap<Player, Double> pot = new HashMap<>();

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
            Double eco = pot.getOrDefault(p, 0.0);
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
    public void onQuit(PlayerQuitEvent e) {
        pot.remove(e.getPlayer());
    }

    @EventHandler
    public void balanceUpdate(UserBalanceUpdateEvent e) {
        Player p = e.getPlayer();
        pot.put(
                p, pot.getOrDefault(p, 0.0) +
                e.getNewBalance().doubleValue() - e.getOldBalance().doubleValue());
    }

//老旧的监测方法
//    public static void run() {
//        Bukkit.getOnlinePlayers().forEach((p) -> {
//            pot.put(p, 0.0);
//            mem.put(p, MoonCube.getEconomy().getBalance(p));
//        });
//        Bukkit.getScheduler().runTaskTimerAsynchronously(MoonCube.plugin, () -> {
//            Bukkit.getOnlinePlayers().forEach((p) -> {
//
//                Double last = mem.get(p);
//                double curry = ECONOMY.getBalance(p);
//                Double ee = pot.get(p);
//
//                if (last == null) last = curry;
//                if (ee == null) ee = 0.0;
//
//                pot.put(p, ee + curry - last);
//                mem.put(p, curry);
//
//            });
//        }, 0L, 20L);
//
//    }
}
