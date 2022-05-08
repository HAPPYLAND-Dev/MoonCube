package me.happylandmc.core.title;

import me.happylandmc.core.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class Title {
    public static void AllPlTitle(@Nullable String f, @Nullable String c) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(f.replace("&", Message.CP), c.replace("&", Message.CP));
        }
    }

    public static void AllPlActionBar(@Nullable String s) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendActionBar(s.replace("&", Message.CP));
        }
    }

    public static void SendInTime(String s, String a, int tick, Player p) {
        p.sendTitle(s, a, 0, tick, 0);
    }
}
