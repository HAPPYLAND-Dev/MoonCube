package me.xiaozhangup.mooncube.mobs;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.tools.BookTip;
import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ArmorClear {

    public static HashMap<Player, Boolean> check = new HashMap<>();

    public static void clear(Player player) {
        check.putIfAbsent(player, false);
        if (!check.get(player)) {
            check.put(player, true);
            String book = "&c&l您真的要这么做吗?\n&7这会清除你附近3x3x3的全息文字,并且不可撤销!虽然你可以通过重新放置对应机器来重新显示正确的全息文字.\n&0\n&0十秒内再次输入本命令确认清理!";
            BookTip.openBook(player, book);
            Bukkit.getScheduler().runTaskLater(MoonCube.plugin, () -> {
                check.put(player, false);
            }, 200L);
            return;
        }
        player.sendActionBar(IString.addColor("&c你的附近没有浮空文字"));
        int i = 0;
        check.put(player, false);
        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
            if (!(entity instanceof ArmorStand armorStand)) continue;
            if (!armorStand.isVisible()) {
                entity.remove();
                i++;
                player.sendActionBar(IString.addColor("&c清理掉了 &f(" + i + " 个文字)"));
            }
        }
    }

}
