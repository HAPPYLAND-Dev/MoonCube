package me.xiaozhangup.mooncube.mobs;

import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ArmorClear {

    public static void clear(Player player) {
        int i = 0;
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
