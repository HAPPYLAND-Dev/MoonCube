package me.xiaozhangup.mooncube.interfaces;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@FunctionalInterface
public interface ItemNameConsumer {
	void accept(ItemMeta meta, String name);
	
	
	static void check(ItemStack item, ItemNameConsumer consumer) {
		if(item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if(meta.hasDisplayName()) {
				consumer.accept(meta, meta.getDisplayName());
			}
		}
	}
}
