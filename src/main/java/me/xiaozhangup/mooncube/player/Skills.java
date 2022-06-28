package me.xiaozhangup.mooncube.player;

import com.archyx.aureliumskills.api.event.ManaAbilityActivateEvent;
import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Skills implements Listener {

    @EventHandler
    public void onPlayeruseAbility(ManaAbilityActivateEvent e) {
        Player p = e.getPlayer();

        if (Config.BLACK_SKILLS_WORLD.contains(p.getWorld().getName())) {
            e.setCancelled(true);
            p.sendMessage(IString.addColor("&8[b技能&8] &c这个世界无法使用技能!"));
        }
    }
}
