package me.xiaozhangup.mooncube.message;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.tools.BookTip;
import me.xiaozhangup.mooncube.gui.tools.INumber;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {


    static List<String> messages = new ArrayList<>();

    public static void run() {
        Bukkit.getScheduler().runTaskTimer(MoonCube.plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(messages.get(INumber.getRandom(0, messages.size() - 1)));
            }
        }, 0L, 9600L);
    }

    public static void load() {
        messages.clear();
        for (String s : MoonCube.plugin.getConfig().getStringList("Board.messages")) {
            messages.add(IString.addColor(MoonCube.plugin.getConfig().getString("Board.prefix") + s));
        }
    }

    public static void print() {
        for (String s : messages) {
            MoonCube.plugin.getLogger().info(s);
        }
    }

    public static void push() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.closeInventory();
            String book = ConfigManager.getConfig("plan").getString("book");
            BookTip.openBook(p, book);
        }
    }
}
