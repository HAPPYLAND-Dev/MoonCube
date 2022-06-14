package me.xiaozhangup.mooncube;

import me.xiaozhangup.mooncube.config.ConfigManager;
import me.xiaozhangup.mooncube.gui.tools.IString;
import me.xiaozhangup.mooncube.menu.MainMenu;
import me.xiaozhangup.mooncube.mobs.Spawner;
import me.xiaozhangup.mooncube.player.Hey;
import me.xiaozhangup.mooncube.player.Join;
import me.xiaozhangup.mooncube.player.ProfileEditer;
import me.xiaozhangup.mooncube.player.fastkey.Ketboard;
import me.xiaozhangup.mooncube.player.tab.TABConfig;
import me.xiaozhangup.mooncube.world.RuleManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;
    private static Economy econ = null;

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

        Bukkit.getPluginManager().registerEvents(new Spawner(), this);
        Bukkit.getPluginManager().registerEvents(new Hey(), this);
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new ProfileEditer(), this);
        Bukkit.getPluginManager().registerEvents(new TABConfig(), this);
        Bukkit.getPluginManager().registerEvents(new RuleManager() , this);
        Bukkit.getPluginManager().registerEvents(new Ketboard(), this);
        Bukkit.getPluginManager().registerEvents(new MainMenu(), this);
        //event load

        ConfigManager.createFile("keymap");
        ConfigManager.createFile("emodata");
        //file

        Bukkit.getPluginCommand("profile").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            ProfileEditer.openProfile(p);
            return true;
        });
        Bukkit.getPluginCommand("tabc").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            TABConfig.openTAB(p);
            return true;
        });
        Bukkit.getPluginCommand("mooncube").setExecutor((commandSender, command, s, inside) -> {
            Player p = (Player) commandSender;
            if (!p.isOp()) return false;
            try {
                if (inside[0].equals("profile")) {
                    Hey.openProfile(p , p);
                    return true;
                }
                if (inside[0].equals("control")) {
                    Hey.openIsControl(p , p);
                    return true;
                }
                if (inside[0].equals("main")) {
                    MainMenu.open(p);
                    return true;
                }
                if (inside[0].equals("reload")) {
                    Config.loadConfig();
                    Ketboard.loadKey();
                    p.sendMessage(IString.addColor("&8[DeBug] &freload!"));
                    return true;
                }
                p.sendMessage(IString.addColor("&8[DeBug] &7profile;control;main;reload"));
                return false;
            } catch (Exception e) {
                p.sendMessage(IString.addColor("&8[DeBug] &7profile;control;main;reload"));
                return false;
            }
        });
        //command

        TABConfig.setUp();
        Ketboard.loadKey();
        //misc
    }

}
