package me.xiaozhangup.mooncube.player;

import org.apache.commons.lang3.tuple.Pair;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import me.xiaozhangup.mooncube.gui.tools.INumber;
import me.xiaozhangup.mooncube.gui.tools.Skull;

public class ArcaneAnvil implements Listener {

    public static final ItemStack ARCANE_LAPIS_GEM_ROUGH = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2ZjZWJlNTRkYmMzNDVlYTdlMjIyMDZmNzAzZTZiMzNiZWZiZTk1YjZhOTE4YmQxNzU0Yjc2MTg4YmM2NWJiNSJ9fX0=",
            "&x&1&E&9&0&F&F粗糙的蓝宝石", "", "&f消耗一个蓝宝石和 20 级经验", "&f来进行一次手动附魔或祛魔", "", "&f成功机率: &e50%", " ",
            "&7将它拿在副手然后打开背包", "&7用附魔书按下 Shift 点击装备来附魔", "&7空的书本按下 Shift 点击已附魔的东西祛魔");
    public static final ItemStack ARCANE_LAPIS_GEM_FINE = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzYxNjFkYWEzNTg5ZWM5YzgxODc0NTlhYzM2ZmQ0ZGQyNjQ2YzA0MDY3OGQzYmZhY2I3MmEyMjEwYzZjODAxYyJ9fX0=",
            "&x&1&E&9&0&F&F精致的蓝宝石", "", "&f消耗一个蓝宝石和 20 级经验", "&f来进行一次手动附魔或祛魔", "", "&f成功机率: &e95%", " ",
            "&7将它拿在副手然后打开背包", "&7用附魔书按下 Shift 点击装备来附魔", "&7空的书本按下 Shift 点击已附魔的东西祛魔");
    public static final ItemStack ARCANE_LAPIS_GEM_PERFECT = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGU5M2ViYWNiNjBiNzE3OTMzNTVmZGUwZDRiYmE0M2E5YzVlYzA5YzNmMzg4OTdjNDhjMWY4NTc1MjNhMGEyOSJ9fX0=",
            "&x&1&E&9&0&F&F完美的蓝宝石", "", "&f消耗一个蓝宝石和 20 级经验", "&f来进行一次手动附魔或祛魔", "", "&f成功机率: &e100%", " ",
            "&7将它拿在副手然后打开背包", "&7用附魔书按下 Shift 点击装备来附魔", "&7空的书本按下 Shift 点击已附魔的东西祛魔");
    public static final ItemStack ARCANE_LAPIS_GEM_FLAWED = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGEwYWY5OWU4ZDg3MDMxOTRhODQ3YTU1MjY4Y2Y1ZWY0YWM0ZWIzYjI0YzBlZDg2NTUxMzM5ZDEwYjY0NjUyOSJ9fX0=",
            "&x&1&E&9&0&F&F瑕疵的蓝宝石", "", "&f消耗一个蓝宝石和 20 级经验", "&f来进行一次手动附魔或祛魔", "", "&f成功机率: &e60%", " ",
            "&7将它拿在副手然后打开背包", "&7用附魔书按下 Shift 点击装备来附魔", "&7空的书本按下 Shift 点击已附魔的东西祛魔");
    public static final ItemStack ARCANE_LAPIS_GEM_FLAWLESS = Skull.getSkull(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU3Y2ZhOWM3NWJhNTg0NjQ1ZWUyYWY2ZDk4NjdkNzY3ZGRlYTQ2NjdjZGZjNzJkYzEwNjFkZDE5NzVjYTdkMCJ9fX0=",
            "&x&1&E&9&0&F&F无瑕的蓝宝石", "", "&f消耗一个蓝宝石和 20 级经验", "&f来进行一次手动附魔或祛魔", "", "&f成功机率: &e75%", " ",
            "&7将它拿在副手然后打开背包", "&7用附魔书按下 Shift 点击装备来附魔", "&7空的书本按下 Shift 点击已附魔的东西祛魔");

    static {
        setCustomModelData(ARCANE_LAPIS_GEM_ROUGH, -114514);
        setCustomModelData(ARCANE_LAPIS_GEM_FLAWED, -114515);
        setCustomModelData(ARCANE_LAPIS_GEM_FLAWLESS, -114516);
        setCustomModelData(ARCANE_LAPIS_GEM_FINE, -114517);
        setCustomModelData(ARCANE_LAPIS_GEM_PERFECT, -114518);
    }

    private static void setCustomModelData(ItemStack itm, int dt) {
        ItemMeta im = itm.getItemMeta();
        im.setCustomModelData(dt);
        itm.setItemMeta(im);
    }

    private static int judgeItem(ItemStack itm) {
        if (itm.getType().equals(Material.PLAYER_HEAD)) {
            if (!itm.getItemMeta().hasCustomModelData())
                return 100;
            switch (itm.getItemMeta().getCustomModelData()) {
                case -114514:
                    return 50;
                case -114515:
                    return 30;
                case -114516:
                    return 15;
                case -114517:
                    return 5;
                case -114518:
                    return 0;
                default:
                    return 100;
            }
        }
        return 100;
    }

    @SuppressWarnings("deprecation") // 去你妈的警告
    @EventHandler
    public void onInt(PlayerInteractEvent evt) {
        if (evt.isCancelled())
            return;
        if (evt.hasItem()) {
            ItemStack itm = evt.getItem();
            if (judgeItem(itm) != 100) {
                evt.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEnchant(InventoryClickEvent evt) {
        if (!evt.getClick().equals(ClickType.SHIFT_LEFT))
            return;
        if (evt.isCancelled() || !(evt.getWhoClicked() instanceof Player) || evt.getClickedInventory() == null
                || !evt.getClickedInventory().getType().equals(InventoryType.PLAYER))
            return;
        if (evt.getWhoClicked().hasPermission("mooncube.arcane-anvil.use") || evt.getWhoClicked().isOp()) {
            Pair<Boolean, Integer> rt = judgeCondition((Player) evt.getWhoClicked(), evt.getCurrentItem(),
                    evt.getCursor());
            boolean passed = rt.getLeft();
            int loseChance = rt.getRight();
            if (passed) {
                ItemStack curr = evt.getCurrentItem();
                boolean cost = false;
                if (evt.getCursor().getType().equals(Material.ENCHANTED_BOOK)) { // enchant!
                    cost = true;
                    ItemStack ecb = evt.getCursor().clone();
                    EnchantmentStorageMeta ecbi = (EnchantmentStorageMeta) ecb.getItemMeta();
                    ItemStack itm = curr.clone();
                    ItemMeta itmi = itm.getItemMeta();
                    if (chance(loseChance)) {
                        ((Player) evt.getWhoClicked()).playSound(evt.getWhoClicked().getLocation(),
                                Sound.ENTITY_GENERIC_EXPLODE, 1f, 2f);
                        ecb = null;
                    } else {
                        ((Player) evt.getWhoClicked()).playSound(evt.getWhoClicked().getLocation(),
                                Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                        ecbi.getStoredEnchants().forEach((ec, lv) -> {
                            itmi.addEnchant(ec, lv, true);
                        });
                        itm.setItemMeta(itmi);
                        ecb = new ItemStack(Material.BOOK);
                    }
                    evt.getView().setCursor(ecb);
                    evt.setCurrentItem(itm);
                } else if (!curr.getEnchantments().isEmpty() &&
                        evt.getCursor().getType().equals(Material.BOOK)) { // denchant!
                    cost = true;
                    ItemStack eci = curr.clone();
                    ItemMeta ecim = eci.getItemMeta();
                    ItemStack book = evt.getCursor().clone();
                    if (chance(loseChance)) {
                        ((Player) evt.getWhoClicked()).playSound(evt.getWhoClicked().getLocation(),
                                Sound.ENTITY_GENERIC_EXPLODE, 1f, 2f);
                        eci = null;
                    } else {
                        book.setType(Material.ENCHANTED_BOOK);
                        EnchantmentStorageMeta bim = (EnchantmentStorageMeta) book.getItemMeta();
                        ((Player) evt.getWhoClicked()).playSound(evt.getWhoClicked().getLocation(),
                                Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                        ecim.getEnchants().forEach((ec, lv) -> {
                            bim.addStoredEnchant(ec, lv, true);
                            ecim.removeEnchant(ec);
                        });
                        eci.setItemMeta(ecim);
                        book.setItemMeta(bim);
                    }
                    evt.getView().setCursor(book);
                    evt.setCurrentItem(eci);
                }
                if (cost) {
                    evt.setCancelled(true);
                    ItemStack off = evt.getWhoClicked().getInventory().getItemInOffHand();
                    Player ety = (Player) evt.getWhoClicked();
                    off.setAmount(off.getAmount() - 1);
                    ety.setLevel(ety.getLevel() - 20);
                }
            }
        }
    }

    private static Pair<Boolean, Integer> judgeCondition(Player ety, ItemStack cur,
            ItemStack cor) {
        boolean pass = true;
        int loseChance;

        if (cur == null || cor == null)
            return Pair.of(false, 100);

        if (cur.getAmount() != 1 || cor.getAmount() != 1)
            return Pair.of(false, 100);

        Material type = cur.getType();
        Material tp = cor.getType();
        if (type.name().matches(".*AIR.*") || type.getMaxStackSize() != 1 || type.getMaxDurability() < 1
                || (!tp.equals(Material.BOOK) && !tp.equals(Material.ENCHANTED_BOOK))) {
            return Pair.of(false, 100);
        }

        if (ety.getLevel() < 20) {
            return Pair.of(false, 100);
        }

        ItemStack off = ety.getInventory().getItemInOffHand();

        loseChance = judgeItem(off);
        if (loseChance == 100)
            pass = false;

        return Pair.of(pass, loseChance);
    }

    private static boolean chance(int percent) {
        return INumber.getRandom(0, 100) < percent;
    }

}
