package me.happylandmc.core.message;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class Message {
    public static String CP = "§";

    public static void AdvMessage(Player p, String s, Boolean mode) {
        if (mode) {
            p.sendMessage(s
                    .replace("{name}", p.getName())
                    .replace("{world}", p.getWorld().getName())
                    .replace("{health}", String.valueOf(p.getHealth()))
                    .replace("{maxhealth}", String.valueOf(p.getMaxHealth()))
                    .replace("{hungry}", String.valueOf(p.getFoodLevel()))
                    .replace("&", "§"));
        } else {
            p.sendMessage(s.replace("&", "§"));
        }
    }

    public static String AdvText(String s, Player p) {
        String text = s
                .replace("{name}", p.getName())
                .replace("{world}", p.getWorld().getName())
                .replace("{health}", String.valueOf(p.getHealth()))
                .replace("{maxhealth}", String.valueOf(p.getMaxHealth()))
                .replace("{hungry}", String.valueOf(p.getFoodLevel()))
                .replace("&", "§");
        return text;
    }

    public static String Color(String s) {
        return s.replace("&", "§");
    }

    public static void PerMessage(Player p, String per, String mes) {
        p.sendMessage("§8[" + per.replace("&", "§") + "§8] " + mes.replace("&", "§"));
    }

    public static String PerText(String per, String mes) {
        String text = "§8[" + per.replace("&", "§") + "§8] " + mes.replace("&", "§");
        return text;
    }

    public static void AllPlMes(String s) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(s);
        }
    }

    public static void SendAirLine(Player p, int a) {
        int x = 0;
        while (x < a) {
            x++;
            p.sendMessage("");
        }
    }

    public static void SendAirLine(Player p) {
        p.sendMessage("");
    }

    public static void SendLine(@Nullable String s, Player p) {
        if (s == null) {
            p.sendMessage(Message.CP + s + "==================================");
        } else {
            p.sendMessage(Message.CP + s + "==================================");
        }
    }

    public static void SendList(Player p, String... s) {
        for (String mes : s) {
            p.sendMessage(mes.replace("&", Message.CP));
        }
    }
}
