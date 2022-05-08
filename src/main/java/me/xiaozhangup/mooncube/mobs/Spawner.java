package me.xiaozhangup.mooncube.mobs;

import me.happylandmc.core.Skull;
import me.happylandmc.core.math.Number;
import me.happylandmc.core.message.Message;
import me.xiaozhangup.mooncube.Config;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public class Spawner implements Listener {

    @EventHandler
    public void onMonDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        EntityType entityType = e.getEntityType();
        if (entityType != EntityType.PLAYER && entity.getKiller() != null) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            double point = entity.getMaxHealth() / 10 - 1;
            String coin = null;
            if (point < 0.00) {
                coin = decimalFormat.format(Number.getRandom(0, entity.getMaxHealth() / 10));
            } else {
                coin = decimalFormat.format(Number.getRandom(point, entity.getMaxHealth() / 10));
            }
            ItemStack ball = Skull.getSkull(
                    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MxNDE0NGY2MWM0ZTY2YjNjNDQzNjYwZGViYzczY2IyMTI1ZDAxNDBjNTFiNTUyMmM4YTY4Yjc4OTQxNCJ9fX0=",
                    "Coin|" + coin);
            Item item = (Item) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.DROPPED_ITEM);
            item.setItemStack(ball);
            item.setCanMobPickup(false);
            item.setCustomNameVisible(true);
            item.setPickupDelay(8);
            item.setThrower(entity.getUniqueId());
            item.setOwner(entity.getKiller().getUniqueId());
            item.setCustomName(Message.Color(Config.COIN_HOLOGRAM.replace("{coin}" , coin)));

        }
    }

    @EventHandler
    public void onPlayerPickup(PlayerAttemptPickupItemEvent e) {
        ItemStack itemStack = e.getItem().getItemStack();
        String itemName = itemStack.getItemMeta().getDisplayName();
        if (itemStack.getType() != Material.PLAYER_HEAD && !itemName.startsWith("Coin|")) return;
        e.setCancelled(true);
        //e.getPlayer().sendActionBar(itemStack.getItemMeta().getDisplayName().replace("Coin|" , ""));
        e.getPlayer().sendActionBar(
                Component.text(Config.COIN_ACTION.replace("{coin}" , itemStack.getItemMeta().getDisplayName().replace("Coin|", "")))
        );
        e.getItem().remove();
    }

    @EventHandler
    public void onHopperWork(InventoryPickupItemEvent e) {
        ItemStack itemStack = e.getItem().getItemStack();
        String itemName = itemStack.getItemMeta().getDisplayName();
        if (itemStack.getType() != Material.PLAYER_HEAD && !itemName.startsWith("Coin|")) return;
        e.setCancelled(true);
    }


}
