package me.xiaozhangup.mooncube;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.xiaozhangup.mooncube.command.Command;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.island.EntityControl;
import me.xiaozhangup.mooncube.item.BlockSaver;
import me.xiaozhangup.mooncube.item.ItemSaver;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import me.xiaozhangup.mooncube.manager.ListenerManager;
import me.xiaozhangup.mooncube.menu.MainMenu;
import me.xiaozhangup.mooncube.menu.UniqueShop;
import me.xiaozhangup.mooncube.menu.Warps;
import me.xiaozhangup.mooncube.message.Board;
import me.xiaozhangup.mooncube.mobs.Adder;
import me.xiaozhangup.mooncube.mobs.ArmorClear;
import me.xiaozhangup.mooncube.mobs.Spawner;
import me.xiaozhangup.mooncube.player.*;
import me.xiaozhangup.mooncube.player.fastkey.Ketboard;
import me.xiaozhangup.mooncube.player.tab.TABConfig;
import me.xiaozhangup.mooncube.world.RuleManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public class MoonCube extends JavaPlugin {

    private static final String commandHelper = IString.addColor("&8[DeBug] &7profile;control;main;reload;setkit;testkit;push;scanmob");
    public static Plugin plugin;
    public static ListenerManager listenerManager = new ListenerManager();
    public static ProtocolManager protocolManager;
    private static Economy econ = null;

    public static Economy getEconomy() {
        return econ;
    }

    @Override
    public void onEnable() {
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();


        getLogger().info("MoonCube Version " + plugin.getDescription().getVersion());
        getLogger().info("");
        //log print


        Config.loadConfig();
        setupEconomy();
        RuleManager.setAll();
        //config


        listenerManager.addListeners(
                new Spawner(), new Hey(), new Join(),
                new ProfileEditor(), new TABConfig(), new RuleManager(),
                new Ketboard(), new MainMenu(), new Skills(), new UniqueShop(),
                new Warps(), new EntityControl(), new Adder(), new ItemAdders(),
                new ArcaneAnvil(), new ArcaneEnchantBook()
        );
        listenerManager.register();
        //event load

        ConfigManager.createFile("keymap");
        ConfigManager.createFile("emodata");
        ConfigManager.createFile("book");
        ConfigManager.createFile("kit");
        ConfigManager.createFile("plan");
        ConfigManager.createFile("items");
        //file


        Command.register("profile", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            ProfileEditor.openProfile(p);
            return true;
        });

        Command.register("tabc", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            TABConfig.openTAB(p);
            return true;
        });

        Command.register("unique", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            UniqueShop.open(p);
            return true;
        });

        Command.register("holoclear", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            ArmorClear.clear(p);
            return true;
        });

        Command.register("mooncube", (commandSender, command, s, inside) -> {
            if (inside.length == 0) {
                commandSender.sendMessage(commandHelper);
                return false;
            }
            if (!(commandSender instanceof ConsoleCommandSender) && !(commandSender instanceof Player)) return false;
            if (commandSender instanceof Player p && !p.isOp()) return false;

            try {
                // Console and op could both execute the following commands.
                switch (inside[0]) {
                    case "" -> commandSender.sendMessage(commandHelper);

                    case "reload" -> {
                        Config.loadConfig();
                        Ketboard.loadKey();
                        Board.load();
                        Board.print();
                        commandSender.sendMessage(IString.addColor("&8[DeBug] &freload!"));
                        return true;
                    }

                    case "push" -> {
                        Board.push();
                        return true;
                    }

                    case "scanmob" -> {
                        EntityControl.scanEntity();
                        return true;
                    }
                }
            } catch (Exception e) {
                commandSender.sendMessage(commandHelper);
                return false;
            }

            if (!(commandSender instanceof Player p)) return false;
            try {
                // Only op can execute the following commands.
                switch (inside[0]) {
                    case "profile" -> {
                        Hey.openProfile(p, p);
                        return true;
                    }

                    case "control" -> {
                        Hey.openIsControl(p, p);
                        return true;
                    }

                    case "main" -> {
                        MainMenu.open(p);
                        return true;
                    }

                    case "setkit" -> {
                        for (int i = 0; i < 37; i++) {
                            if (p.getInventory().getItem(i) == null) continue;
                            ConfigManager.writeConfig("kit", "Slot." + i, p.getInventory().getItem(i));
                            p.sendMessage(p.getInventory().getItem(i).toString());
                        }
                        return true;
                    }

                    case "testkit" -> {
                        p.getInventory().clear();
                        for (int i = 0; i < 37; i++) {
                            if (ConfigManager.getConfig("kit").getItemStack("Slot." + i) == null) continue;
                            p.getInventory().setItem(i, ConfigManager.getConfig("kit").getItemStack("Slot." + i));
                        }
                        return true;
                    }
                }

                p.sendMessage(commandHelper);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            p.sendMessage(commandHelper);
            return false;
        });

        Command.register("menu", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            MainMenu.open(p);
            return true;
        });

        Command.register("iaguide", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            p.getInventory().addItem(ItemAdders.iabook);
            return true;
        });

        Command.register("moonitem", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            if (inside[0].equals("block")) {
                if (inside[1].equals("save")) {
                    Block block = p.getTargetBlock(4);

                    if (block == null) return false;
                    BlockSaver.saveBlockMeta(block.getBlockData(), inside[2]);
                    return true;
                }
            }
            if (inside[0].equals("item")) {
                if (inside[1].equals("save")) {
                    ItemSaver.saveItemtoFile(p.getInventory().getItemInMainHand(), inside[2]);
                    return true;
                }
                if (inside[1].equals("load")) {
                    p.getInventory().addItem(ItemSaver.loadFromFile(inside[2]));
                    return true;
                }
            }
            return false;
        });

        Command.register("arcaneavl", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            if (p.isOp()) p.getInventory().addItem(ArcaneAnvil.ARCANE_LAPIS_GEM_ROUGH, ArcaneAnvil.ARCANE_LAPIS_GEM_FLAWED, ArcaneAnvil.ARCANE_LAPIS_GEM_FLAWLESS, ArcaneAnvil.ARCANE_LAPIS_GEM_FINE, ArcaneAnvil.ARCANE_LAPIS_GEM_PERFECT);
            return true;
        });

        Command.register("arcanebk", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            if (p.isOp()) p.getInventory().addItem(ArcaneEnchantBook.ARCANE_ENCHANT_BOOK);
            return true;
        });

        //command

        Board.load();
        TABConfig.setUp();
        Ketboard.loadKey();
        Board.run();
        //misc

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 1) {
                Spawner.dailyCoin.clear();
            }
        }, 1L, 48000L);
        Bukkit.getScheduler().runTaskTimer(this, EntityControl::scanEntity, 1L, 400L);
        //task

        //protocol
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }
    }

}
