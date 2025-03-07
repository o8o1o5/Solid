package com.o8o1o5.solid.menu;

import com.o8o1o5.solid.NPC.shopNPC.ShopInventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class MenuGUI {
    public static void openMenuGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "메뉴");

        ItemStack warpItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta warpMeta = warpItem.getItemMeta();
        if (warpMeta != null) {
            warpMeta.setDisplayName(ChatColor.AQUA + "워프 이동");
            warpMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "신비한 엔더의 힘으로 어디로든 이동할 수 있을 것 같다.",
                    ChatColor.GRAY + "통행료는 당신의 텔레포트를 위해 수없이 도살당했을 엔더맨들의 절규다!",
                    ""
            ));

            warpItem.setItemMeta(warpMeta);
        }

        ItemStack statItem = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta statMeta = statItem.getItemMeta();
        if (statMeta != null) {
            statMeta.setDisplayName(ChatColor.AQUA + "스탯");
            statMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "당신의 능력은 책 위 글자로서 기록된다.",
                    ChatColor.GRAY + "마르지 않는 경험만이 그 잉크가 되리라!",
                    ""
            ));

            statItem.setItemMeta(statMeta);
        }

        inventory.setItem(13, warpItem);
        inventory.setItem(16, statItem);

        player.openInventory(inventory);
    }
}
