package me.xiaozhangup.mooncube.player.nametag;

import me.xiaozhangup.mooncube.MoonCube;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TagChangePack {

    public static void clearAll(Player p) {
        Bukkit.getScheduler().runTask(MoonCube.plugin, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " meta removeprefix 30");

        });
    }

    public static void setPerfix(Player p, String prefix) {
        Bukkit.getScheduler().runTask(MoonCube.plugin, () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " meta addprefix 30 " + prefix);

        });
    }

}
