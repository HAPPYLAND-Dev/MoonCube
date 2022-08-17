package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.gui.tools.IString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PortalBreak implements Listener {

    private final String breakd = IString.addColor("&8[&x&9&9&D&0&6&6框架&8] &7框架成功拆除");
    private final String deny = IString.addColor("&8[&x&9&9&D&0&6&6框架&8] &c你没有权限拆除这个门框");
    private final String guide = IString.addColor("&8[&x&9&9&D&0&6&6框架&8] &a放置一个&e金块&a在门框上方,手持&e金镐&a挖掘门框即可掉落. 但&e金块&a会被消耗");
    private final String pickaxe = IString.addColor("&8[&x&9&9&D&0&6&6框架&8] &c你需要使用金镐来挖掘门框");
    private final ItemStack frame = new ItemStack(Material.END_PORTAL_FRAME);

    public static boolean check(Player p, Block b) {
        BlockBreakEvent blockBreakEvent = new BlockBreakEvent(b, p);
        Bukkit.getPluginManager().callEvent(blockBreakEvent);
        return !blockBreakEvent.isCancelled();
    }

    @EventHandler
    public void onPlayerBreak(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block block = e.getClickedBlock();
        ItemStack itemStack = e.getItem();
        Action action = e.getAction();
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (action.isLeftClick() && block != null && block.getType() == Material.END_PORTAL_FRAME) {
            if (itemStack != null) {
                if (itemStack.getType() == Material.GOLDEN_PICKAXE) {
                    Location location = block.getLocation();
                    Block gold = location.add(0, 1, 0).getBlock();
                    if (gold.getType() == Material.GOLD_BLOCK) {
                        if (check(p, block) && check(p, gold)) {
                            p.sendMessage(breakd);
                            block.setType(Material.AIR);
                            gold.setType(Material.AIR);
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
                            location.getWorld().dropItem(location, frame);
                        } else {
                            p.sendMessage(deny);
                        }
                    } else {
                        p.sendMessage(guide);
                    }
                } else {
                    p.sendMessage(pickaxe);
                }
            } else {
                p.sendMessage(guide);
            }
        }
    }

}
