package me.xiaozhangup.mooncube.island;

import com.iridium.iridiumskyblock.api.IslandCreateEvent;
import com.iridium.iridiumskyblock.api.IslandDeleteEvent;
import com.iridium.iridiumskyblock.api.IslandRegenEvent;
import com.iridium.iridiumskyblock.database.User;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static me.xiaozhangup.mooncube.guide.ABook.mm;

public class LandEvent implements Listener {

    @EventHandler
    public void on(IslandRegenEvent e) {
        Player p = e.getUser().getPlayer();
        if (e.getIsland().getMembers().size() > 1) {
            e.setCancelled(true);
            if (p != null) {
                p.sendTitlePart(TitlePart.TITLE, mm.deserialize("<red>无法重置岛屿"));
                p.sendTitlePart(TitlePart.SUBTITLE, mm.deserialize("因为你的岛屿团队有其他成员!"));
            }
        } else {
            fush(p);
        }
    }

    @EventHandler
    public void on(IslandDeleteEvent e) {
        User user = e.getUser();
        if (user == null) return;
        Player p = user.getPlayer();
        if (e.getIsland().getMembers().size() > 1) {
            e.setCancelled(true);
            if (p != null) {
                p.sendTitlePart(TitlePart.TITLE, mm.deserialize("<red>无法删除岛屿"));
                p.sendTitlePart(TitlePart.SUBTITLE, mm.deserialize("因为你的岛屿团队有其他成员!"));
            }
        } else {
            fush(p);
        }
    }

    public static void fush(Player p) {
        p.getInventory().clear();
        p.getEnderChest().clear();
        p.setTotalExperience(0);
        p.setExp(0);
        p.setLevel(0);
        p.setFoodLevel(20);
        p.setHealth(20);

        for (int i = 0; i < 37; i++) {
            if (ConfigManager.getConfig("kit").getItemStack("Slot." + i) == null) continue;
            p.getInventory().setItem(i, ConfigManager.getConfig("kit").getItemStack("Slot." + i));
        }
    }
}
