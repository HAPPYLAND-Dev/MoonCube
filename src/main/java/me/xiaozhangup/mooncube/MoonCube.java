package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.command.Command;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import me.xiaozhangup.mooncube.manager.ListenerManager;
import me.xiaozhangup.mooncube.menu.MainMenu;
import me.xiaozhangup.mooncube.menu.UniqueShop;
import me.xiaozhangup.mooncube.message.Board;
import me.xiaozhangup.mooncube.mobs.Spawner;
import me.xiaozhangup.mooncube.player.Hey;
import me.xiaozhangup.mooncube.player.Join;
import me.xiaozhangup.mooncube.player.ProfileEditer;
import me.xiaozhangup.mooncube.player.Skills;
import me.xiaozhangup.mooncube.player.fastkey.Ketboard;
import me.xiaozhangup.mooncube.player.tab.TABConfig;
import me.xiaozhangup.mooncube.world.RuleManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public class MoonCube extends JavaPlugin {

    public static Plugin plugin;
    
    private static Economy econ = null;
    

    @Override
    public void onEnable() {
        plugin = this;

        
        getLogger().info("MoonCube Version " + plugin.getDescription().getVersion());
        getLogger().info("");
        //log print

        
        Config.loadConfig();
        setupEconomy();
        RuleManager.setAll();
        //config

        ListenerManager listenerManager = new ListenerManager();
        listenerManager.addListeners(
                new Spawner(), new Hey(), new Join(),
                new ProfileEditer(), new TABConfig(), new RuleManager(),
                new Ketboard(), new MainMenu(), new Skills(), new UniqueShop()
        );
        listenerManager.register();
        //event load

        
        ConfigManager.createFile("keymap");
        ConfigManager.createFile("emodata");
        ConfigManager.createFile("book");
        ConfigManager.createFile("kit");
        ConfigManager.createFile("plan");
        //file

        
        Command.register("profile", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            ProfileEditer.openProfile(p);
            return true;
        });
        
        Command.register("tabc", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            TABConfig.openTAB(p);
            return true;
        });
        
        Command.register("mooncube", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            if (!p.isOp()) return false;
            try {
                switch (inside[0]) {
                    case "profile" -> {
                        Hey.openProfile(p , p);
                        return true;
                    }
                    
                    case "control" -> {
                        Hey.openIsControl(p , p);
                        return true;
                    }
                    
                    case "main" -> {
                        MainMenu.open(p);
                        return true;
                    }
                    
                    case "reload" -> {
                        Config.loadConfig();
                        Ketboard.loadKey();
                        Board.load();
                        Board.print();
                        p.sendMessage(IString.addColor("&8[DeBug] &freload!"));
                        return true;
                    }
                    case "setkit" -> {
                        for (int i = 0 ; i < 37 ; i++) {
                            if (p.getInventory().getItem(i) == null) continue;
                            ConfigManager.writeConfig("kit" , "Slot." + i, p.getInventory().getItem(i));
                            p.sendMessage(p.getInventory().getItem(i).toString());
                        }
                        return true;
                    }
                    case "testkit" -> {
                        p.getInventory().clear();
                        for (int i = 0 ; i < 37 ; i ++) {
                            if (ConfigManager.getConfig("kit").getItemStack("Slot." + i) == null) continue;
                            p.getInventory().setItem(i , ConfigManager.getConfig("kit").getItemStack("Slot." + i));
                        }
                        return true;
                    }
                    case "push" -> {
                        Board.push();
                    }
                }
                p.sendMessage(IString.addColor("&8[DeBug] &7profile;control;main;reload;setkit;testkit;push"));
                return false;
            } catch (Exception e) {
                p.sendMessage(IString.addColor("&8[DeBug] &7profile;control;main;reload;setkit;testkit;push"));
                return false;
            }
        });
        
        Command.register("menu", (commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            MainMenu.open(p);
            return true;
        });
        //command

        Board.load();
        TABConfig.setUp();
        Ketboard.loadKey();
        Board.run();
        for (Player p : Bukkit.getOnlinePlayers()) {
            Spawner.dailyCoin.put(p.getUniqueId(), 0.0);
        }
        //misc

        Bukkit.getScheduler().runTaskTimer(this , () -> {
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == 1) {
                Spawner.dailyCoin.clear();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    Spawner.dailyCoin.put(p.getUniqueId(), 0.0);
                }
            }
        } , 1L , 48000L);
        //task
    }


    public static Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }
        return (econ != null);
    }

}
