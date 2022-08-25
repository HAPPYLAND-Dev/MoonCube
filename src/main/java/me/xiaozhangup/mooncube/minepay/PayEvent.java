package me.xiaozhangup.mooncube.minepay;

import me.xiaozhangup.mooncube.player.ArcaneAnvil;
import me.xiaozhangup.mooncube.player.ArcaneEnchantBook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.minepay.api.events.MinePaySuccessEvent;

public class PayEvent implements Listener {

    @EventHandler
    public void onMinePaySuccessEvent(MinePaySuccessEvent event) {
        String kitname = event.getTradeInfo().getKitItem().getName();
        Player p = Bukkit.getPlayer(event.getTradeInfo().getPlayerName());
        if ("aoshu".equals(kitname)) {
            p.getInventory().addItem(ArcaneEnchantBook.ARCANE_ENCHANT_BOOK, ArcaneEnchantBook.ARCANE_ENCHANT_BOOK);
        } else if ("baoshi".equals(kitname)) {
            p.getInventory().addItem(ArcaneAnvil.ARCANE_LAPIS_GEM_PERFECT);
        }
    }

}
