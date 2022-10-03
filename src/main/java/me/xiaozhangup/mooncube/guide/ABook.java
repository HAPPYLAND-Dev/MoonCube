package me.xiaozhangup.mooncube.guide;

import me.xiaozhangup.mooncube.MoonCube;
import me.xiaozhangup.mooncube.manager.ConfigManager;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ABook {

    public static List<String> guide_node = new ArrayList<>();
    public static HashMap<Integer, String> guide_content = new HashMap<>();

    public static MiniMessage mm = MiniMessage.miniMessage();

    public static void openBook(Integer node, Player p) {
        var content = guide_content.get(node);
        Component bookTitle = Component.text("Guide Book");
        Component bookAuthor = Component.text("Guider");

        Book book = Book.book(bookTitle, bookAuthor,
                Component.text(guide_node.get(node - 1) + "\n \n").color(TextColor.color(160,82,45)).decoration(TextDecoration.BOLD, true)
                        .append(mm.deserialize("<black>" + content + "</black>\n \n").decoration(TextDecoration.BOLD, false))
                        .append(mm.deserialize("<dark_gray><b><click:run_command:'/treepicker'><hover:show_text:'<gray>回到上级</gray>'>返回主目录</hover></click></b></dark_gray>"))
        );

        p.openBook(book);
        Bukkit.getScheduler().runTaskAsynchronously(MoonCube.plugin, () -> {
            List<Integer> lists = ConfigManager.getConfig("playerdate").getIntegerList(p.getUniqueId() + ".Guide");
            lists.add(node);
            ConfigManager.writeConfig("playerdate", p.getUniqueId() + ".Guide", lists);
        });
    }

    public static void freshGuide() {
        var config = ConfigManager.getConfig("guide");
        guide_node.clear();
        guide_content.clear();
        for (int i = 1; i < 45; i++) {
            String name = config.getString(i + ".name");
            if (name == null) break;
            guide_node.add(name);
            guide_content.put(i, config.getString(i+".content"));
            MoonCube.plugin.getLogger().info("载入教程: " + name);
        }
        MoonCube.plugin.getLogger().info("已成功重新载入所有教程");
    }

}
