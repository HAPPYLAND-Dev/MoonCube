package me.xiaozhangup.mooncube.item;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;

public class BlockSaver {

    public static void saveBlockMeta(BlockData blockData, String s) {
        ConfigManager.writeConfig("items", "block." + s, blockData.getAsString());
        MoonCube.plugin.getLogger().info(s + " Saved! (BlockData)");
    }

    public static BlockData loadFromFile(String s) {
        String data = ConfigManager.getConfig("items").getString("block." + s);
        if (data == null) {
            return null;
        }
        return Bukkit.createBlockData(data);
    }

}
