package me.xiaozhangup.mooncube.player;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import me.xiaozhangup.mooncube.gui.tools.Skull;

public class ArcaneEnchantBook implements Listener {

    public static final ItemStack ARCANE_ENCHANT_BOOK = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmVmZTY1MDViYzg0MGFiZmQ4NjY2MjYxMDE3ZWMyMGE2ODQ2ODU2MWJjM2NmMmZhZDYzOWE0ZWM5NDc4OWZhMCJ9fX0=",
            "&6奥术之书", "", "&f古老而被尘封的物品", "&f依稀看出它是一本附魔书", "", "&e右键 &f打开书本");

    static {
        ItemMeta im = ARCANE_ENCHANT_BOOK.getItemMeta();
        im.setCustomModelData(-114519);
        ARCANE_ENCHANT_BOOK.setItemMeta(im);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onInter(PlayerInteractEvent evt) {
        if (evt.isCancelled() || (!evt.getAction().equals(Action.RIGHT_CLICK_AIR)
                && !evt.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        if (evt.hasItem()) {
            ItemStack i = evt.getItem();
            if (i.getType().equals(Material.PLAYER_HEAD) && i.getItemMeta().hasCustomModelData()
                    && i.getItemMeta().getCustomModelData() == -114519) {
                evt.setCancelled(true);
                Enchantment e = Enchantment.values()[new Random().nextInt(Enchantment.values().length)];
                ItemStack eb = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta ebm = (EnchantmentStorageMeta) eb.getItemMeta();
                ebm.addStoredEnchant(e, new Random().nextInt(10) + 1, true);
                eb.setItemMeta(ebm);
                i.setAmount(i.getAmount() - 1);
                evt.getPlayer().playSound(evt.getPlayer(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);
                if (evt.getPlayer().getInventory().firstEmpty() == -1) {
                    evt.getPlayer().getWorld().dropItem(evt.getPlayer().getLocation(), eb);
                } else {
                    evt.getPlayer().getInventory().addItem(eb);
                }
            }
        }
    }
}
