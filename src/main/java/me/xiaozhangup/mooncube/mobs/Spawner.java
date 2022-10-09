package me.xiaozhangup.mooncube.mobs;

import me.xiaozhangup.mooncube.Config;
import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.tools.INumber;
import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Spawner implements Listener {

    public static Map<UUID, Double> dailyCoin = new HashMap<>();


    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");


    @EventHandler
    public void onMonDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        EntityType entityType = e.getEntityType();
        if (entityType != EntityType.PLAYER && entity.getKiller() != null) {
            double point = entity.getMaxHealth() / 10 - 1;
            double coinValue;
            if (point < 0.00) coinValue = INumber.getRandom(0, entity.getMaxHealth() / 10);
            else coinValue = INumber.getRandom(point, entity.getMaxHealth() / 10);

            String coin = decimalFormat.format(coinValue);


            Player player = entity.getKiller();
            UUID uuid = player.getUniqueId();

            double playerDailyCoin = dailyCoin.getOrDefault(uuid, 0.0);
            if (playerDailyCoin <= Config.DAILY_MAX) {
                //player.sendActionBar(IString.addColor(Config.COIN_ACTION.replace("{coin}", coin)));
                MoonCube.getEconomy().depositPlayer(player, Double.parseDouble(coin));
                dailyCoin.put(uuid, playerDailyCoin + Double.parseDouble(coin));
            } else {
                player.sendActionBar(IString.addColor(Config.COIN_FULL));
            }

        }
    }

}
