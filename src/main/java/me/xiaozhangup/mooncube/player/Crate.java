package me.xiaozhangup.mooncube.player;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.gui.HeyProfile;
import me.xiaozhangup.mooncube.gui.tools.IBuilder;
import me.xiaozhangup.mooncube.gui.tools.INumber;
import me.xiaozhangup.mooncube.gui.tools.Skull;
import me.xiaozhangup.mooncube.menu.CrateGUI;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static me.xiaozhangup.mooncube.guide.ABook.mm;

public class Crate implements Listener {

    public static List<Player> in = new ArrayList<>();

    public static final ItemStack board = IBuilder.getBorder(Material.GRAY_STAINED_GLASS_PANE);
    public static final ItemStack nan = Skull.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZlYTk5YWQ5NWI1NzAxNzVmZGEyNWMzYTY5Nzg4ZDZhOWI4NTRhYTEzZjhhNWZmNjNmNmVmZWRmNTgxZGZiNiJ9fX0=", "&e???");
    public static final ItemStack barrier = IBuilder.buildItem(Material.BARRIER, "&c正在开奖中...");
    public static Inventory cratein = Bukkit.createInventory(new CrateGUI(), 27, mm.deserialize("滚轮老虎机 | 进行中..."));
    public static int time = 0;

    public static final ItemStack push = IBuilder.buildItem(
            Material.STICK,
            "&f启动机器!",
            " ",
            "&7点击此处,你将花费100来进行一次",
            "&7抽奖. 奖池为 &e2-600 &7不等"
    );


    public static void open(Player p) {
        Inventory crate = Bukkit.createInventory(new CrateGUI(), 27, mm.deserialize("滚轮老虎机"));
        for (int i = 0; i < 27; i++) {
            crate.setItem(i, board);
        }

        //11,12,13 15
        for (int i = 11; i < 14; i++) {
            crate.setItem(i, nan);
        }

        crate.setItem(15, push);
        p.openInventory(crate);
    }

    public static void start(Player p) {
        in.add(p);
        p.openInventory(cratein);
        time = time + 30;
        Bukkit.getScheduler().runTaskLater(MoonCube.plugin, () -> {
            in.remove(p);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);

            p.closeInventory();
            if (INumber.getRandom(1,16) == 2) {
                int reward = INumber.getRandom(120, 600);
                Bukkit.broadcast(mm.deserialize("<dark_gray>[<color:#cff24e>抽金</color>]</dark_gray> <white>恭喜玩家 " + p.getName() + " 在<yellow>老虎机</yellow>中抽得了 " + reward + " 个<yellow>金币</yellow>!</white>"));
                MoonCube.getEconomy().depositPlayer(p, reward);
                open(p);
            } else {
                int reward = INumber.getRandom(2, 110);
                Bukkit.broadcast(mm.deserialize("<dark_gray>[<color:#cff24e>抽金</color>]</dark_gray> <white>恭喜玩家 " + p.getName() + " 在<yellow>老虎机</yellow>中抽得了 " + reward + " 个<yellow>金币</yellow>!</white>"));
                MoonCube.getEconomy().depositPlayer(p, reward);
                open(p);
            }

        },62L);
    }

    public static void setup() {
        for (int i = 0; i < 27; i++) {
            cratein.setItem(i, board);
        }
        cratein.setItem(15, barrier);

        Bukkit.getScheduler().runTaskTimer(MoonCube.plugin, () -> {
            if (time > 0) {
                for (int i = 11; i < 14; i++) {
                    cratein.setItem(i, Skull.getSkull(getRSkull(), "&7开奖中"));
                }
                in.forEach((player -> {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1, 1);
                    player.closeInventory();
                    player.openInventory(cratein);
                }));
                time--;
            }
        }, 0L, 2L);
    }

    public static String getRSkull() {
        List<String> givenList = Arrays.asList(
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTdjNWU5MjVhOTQ5ZTU1ZGIyYzI1ZWZhYWQ2NDUxMmViNmRhYjc0YWZmYjJlOWYzMDRjMzg1YjRmNGIzMGJhNSJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzBmN2Y3NmE4ZjNhMDU4ODdjZmY1OGY5OTZkYWQzMDAxZmJlMWYyMjUzMWQ2MmRjZDdkNGY2MzRhMDdiMmNhMSJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzE1OTdlOTU3ZTUzZDAyOGZiYjZmYzlmMzI2MTc0NmI3MTYyYmM4MjVhOGIzOTE5MTdjZGUyMGZkMGVjNzkxNCJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTZlZjYxNGIzYTVmYmVjOWI4YWYzNWQ4YTQwZTkxZGNjZGQ4OTc3YzcxMmVjZWY2Y2U1MmQ5MWFmNDljNGM5MyJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWRjNjNiMjJlODY3ZTMwY2I4M2VmYjUyODc2YTUyOGRhZDUxZTc4YzQ4ZWMzYWRlYTRmODA0OGFhMjQyZDY1MCJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGYxYmVjYTNjNjIyODIxYmVjOGM4ZWI4N2QxMWNmOTY5YmVjM2NkMzdiNTc5YmI4NGU5NjI4MmY3YzFjOTA1NyJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ3ZTljYjY1MDhmMDc5YWRiYmNiNDk2OWM1MGQxMjZlMTljYzk0YjllOGViMTNiOTRlNmUyMGJmYmEyMWVkMSJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE5NTJkMjcyYjc0ODVmYzNiNDE5ZTU2ZDczMDc0ZmMxOGNmMzExZTU0MTNhODNhMTFlMWU5Njc3YmQ1MDBmIn19fQ==",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJkYWU4MDQ2ZWY4OTRjY2U1ODFkMzY4MjVkZDI3NzUwZTU5MjM3NzVkMjM5MTU2ZmI2Y2YyMmQ0NTU1MjcyOCJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQ3ZTljYjY1MDhmMDc5YWRiYmNiNDk2OWM1MGQxMjZlMTljYzk0YjllOGViMTNiOTRlNmUyMGJmYmEyMWVkMSJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmJkYWU4MDQ2ZWY4OTRjY2U1ODFkMzY4MjVkZDI3NzUwZTU5MjM3NzVkMjM5MTU2ZmI2Y2YyMmQ0NTU1MjcyOCJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1YjY5ZGY0YjBlMzU1YmYzYjk4ZmE5MGU2OWZlOTZlNzY5ODFmZWEwYjhjZWQzNGIwZDljZTllZGQ2MWNmNCJ9fX0=",
                "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmU2NDRjMTJlODJhNzU1ODU0NzlhYjdiOTE3MjMzMTM4YmM3MzAyMTUyYzY5ODI2MDYwODBkODZiNTY4ZGVmMyJ9fX0="
        );
        Random rand = new Random();
        return givenList.get(rand.nextInt(givenList.size()));
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player p) {
            if (e.getInventory().getHolder() instanceof CrateGUI) {
                e.setCancelled(true);
                if (e.getRawSlot() == 15) {
                    if (MoonCube.getEconomy().withdrawPlayer(p, 100).type == EconomyResponse.ResponseType.SUCCESS) {
                        start(p);
                    } else {
                        p.closeInventory();
                        p.sendMessage(mm.deserialize("<dark_gray>[<color:#cff24e>抽金</color>]</dark_gray> <red>你似乎没有足够的前来抽奖</red>"));
                    }
                }
            }
        }
    }
}
