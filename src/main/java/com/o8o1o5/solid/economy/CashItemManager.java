package com.o8o1o5.solid.economy;

import com.o8o1o5.solid.Solid;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashItemManager {
    private static NamespacedKey CASH_TAG;

    public static void init(JavaPlugin plugin) {
        if (CASH_TAG == null) {
            CASH_TAG = new NamespacedKey(plugin, "currency");
        }
    }

    public static ItemStack createCashItem(Material material, int amount) {
        ItemStack cash = new ItemStack(material, amount);
        ItemMeta meta = cash.getItemMeta();
        if (meta != null) {
            if (material == Material.IRON_INGOT) {
                meta.setDisplayName("1 골드");
                meta.setLore(Arrays.asList(
                        "화폐로 사용된다",
                        ChatColor.GRAY + "§o푼돈!"));
            } else if (material == Material.GOLD_INGOT) {
                meta.setDisplayName("100 골드");
                meta.setLore(Arrays.asList(
                        "화폐로 사용된다",
                        ChatColor.GRAY + "§o여전히 푼돈!"));
            } else if (material == Material.DIAMOND) {
                meta.setDisplayName("10000 골드");
                meta.setLore(Arrays.asList(
                        "화폐로 사용된다",
                        ChatColor.GRAY + "§o우와 세종대왕!"));
            } else if (material == Material.NETHER_STAR) {
                meta.setDisplayName("1000000 골드");
                meta.setLore(Arrays.asList(
                        "화폐로 사용된다",
                        ChatColor.GRAY + "§o난 돈이 좋아!"));
            }

            meta.addEnchant(Enchantment.UNBREAKING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            PersistentDataContainer data = meta.getPersistentDataContainer();
            data.set(CASH_TAG, PersistentDataType.BOOLEAN, true);

            cash.setItemMeta(meta);
        }
        return cash;
    }

    public static boolean isCashItem(ItemStack item) {
        if (item == null || !item.hasItemMeta()) {
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();

        return data.getOrDefault(CASH_TAG, PersistentDataType.BOOLEAN, false);
    }
}
