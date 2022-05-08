package me.happylandmc.core.console;

import me.xiaozhangup.mooncube.Main;
import org.bukkit.Bukkit;

public class Console {
    public static void ConsolePrint(String s, Boolean a) {
        if (a) {
            Main.plugin.getLogger().warning(s);
        } else {
            Main.plugin.getLogger().info(s);
        }
    }

    public static void ConsoleCom(String s) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
    }

    public static void AirLine() {
        System.out.println();
    }

    public static void FullLine() {
        System.out.println("===========================");
    }
}
