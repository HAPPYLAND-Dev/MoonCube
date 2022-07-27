package me.xiaozhangup.mooncube.item;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.inventory.ItemStack;

public class ItemSaver {

    public static void saveItemtoFile(ItemStack itemStack, String s) {
        ConfigManager.writeConfig("items", "item." + s, itemStack);
        MoonCube.plugin.getLogger().info(s + " Saved! (ItemStack)");
    }

    public static ItemStack loadFromFile(String s) {
        return ConfigManager.getConfig("items").getItemStack("item." + s);
    }

}
