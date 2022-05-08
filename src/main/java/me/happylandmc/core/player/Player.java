package me.happylandmc.core.player;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class Player {
    public static void executeCommand(Player p, String s) {
        Bukkit.dispatchCommand((CommandSender) p, s);
    }
}
