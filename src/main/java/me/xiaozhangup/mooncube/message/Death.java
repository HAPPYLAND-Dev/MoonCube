package me.xiaozhangup.mooncube.message;

import io.papermc.paper.event.entity.TameableDeathMessageEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.deathMessage() != null) {
            e.deathMessage(Component.text("§8[§c死亡§8] ").append(e.deathMessage()));
        }
    }

    @EventHandler
    public void onPetDeath(TameableDeathMessageEvent e) {
        e.deathMessage(Component.text("§8[§x§f§2§f§f§0§0宠物§8] ").append(e.deathMessage()));
    }

}
