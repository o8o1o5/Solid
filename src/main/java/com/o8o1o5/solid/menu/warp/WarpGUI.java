package com.o8o1o5.solid.menu.warp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WarpGUI {
    public static void openWarpGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "워프 이동");

        ItemStack borderItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta borderMeta = borderItem.getItemMeta();
        if (borderMeta != null) {
            borderMeta.setDisplayName(" ");
            borderItem.setItemMeta(borderMeta);
        }

        ItemStack warpItem = new ItemStack(Material.ENDER_PEARL);
        ItemMeta warpMeta = warpItem.getItemMeta();
        if (warpMeta != null) {
            warpMeta.setDisplayName(ChatColor.AQUA + "뒤로 가기");
            warpMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "§o\"피가 적을 때엔 엔더 진주를 던지지 말라.\"",
                    ChatColor.GRAY + "§o- 나폴레옹, 기원전 821년 플라톤과 진주만 공습을 감행하며 -",
                    ""
            ));
            warpMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            warpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            warpItem.setItemMeta(warpMeta);
        }

        ItemStack lobbyItem = new ItemStack(Material.COMPASS);
        ItemMeta lobbyMeta = lobbyItem.getItemMeta();
        if (lobbyMeta != null) {
            lobbyMeta.setDisplayName(ChatColor.WHITE + "로비");
            lobbyMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "많은 석영 블록, 그리고 더 많은 현석이들!",
                    ""
            ));

            lobbyItem.setItemMeta(lobbyMeta);
        }

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, borderItem);
        }

        inventory.setItem(4, warpItem);
        inventory.setItem(19, lobbyItem);

        player.openInventory(inventory);
    }
}
