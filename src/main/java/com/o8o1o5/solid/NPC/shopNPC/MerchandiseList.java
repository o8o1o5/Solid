package com.o8o1o5.solid.NPC.shopNPC;

import com.o8o1o5.solid.NPC.NPCInfo;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MerchandiseList {
    public static final Map<Integer, List<MerchandiseStack>> merchandiseMap = new HashMap<>();

    static {
        merchandiseMap.put(10000, Arrays.asList(
            new MerchandiseStack(new ItemStack(Material.COAL), 15, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.COAL_BLOCK), 135, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.COPPER_INGOT), 30, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.COPPER_BLOCK), 270, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.IRON_INGOT), 220, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.IRON_BLOCK), 1980, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.GOLD_INGOT), 340, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.GOLD_BLOCK), 3060, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.REDSTONE), 190, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.REDSTONE_BLOCK), 1710, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.LAPIS_LAZULI), 180, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.LAPIS_BLOCK), 1620, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.DIAMOND), 1750, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.DIAMOND_BLOCK), 15750, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.EMERALD), 4300, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.EMERALD_BLOCK), 38700, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.NETHERITE_INGOT), 25000, 0, true, false),
            new MerchandiseStack(new ItemStack(Material.NETHERITE_BLOCK), 225000, 0, true, false)
        ));

        merchandiseMap.put(10001, Arrays.asList(
                new MerchandiseStack(new ItemStack(Material.WHEAT_SEEDS), 1, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.PUMPKIN_SEEDS), 1, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.MELON_SEEDS), 1, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.BEETROOT_SEEDS), 1, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.WHEAT), 15, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.HAY_BLOCK), 135, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.POTATO), 11, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.CARROT), 12, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.APPLE), 15, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.MELON_SLICE), 14, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.MELON), 140, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.PUMPKIN), 75, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.BEETROOT), 25, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.SUGAR_CANE), 13, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.COCOA_BEANS), 35, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.CACTUS), 180, 0, true, false),
                new MerchandiseStack(new ItemStack(Material.SWEET_BERRIES), 20, 0, true, false)
        ));

        /*
        merchandiseMap.put(10002, Arrays.asList(
                new MerchandiseStack(new ItemStack(Material.BREAD), 60, 50, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKIE), 12, 10, true, true),
                new MerchandiseStack(new ItemStack(Material.PUMPKIN_PIE), 300, 260, true, true),
                new MerchandiseStack(new ItemStack(Material.GOLDEN_APPLE), 210, 180, true, true),
                new MerchandiseStack(new ItemStack(Material.GOLDEN_CARROT), 150, 130, true, true),
                new MerchandiseStack(new ItemStack(Material.GLISTERING_MELON_SLICE), 240, 210, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_BEEF), 265, 230, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_PORKCHOP), 250, 220, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_CHICKEN), 185, 160, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_MUTTON), 90, 80, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_RABBIT), 12, 10, true, true),
                new MerchandiseStack(new ItemStack(Material.BAKED_POTATO), 470, 410, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_COD), 4100, 3500, true, true),
                new MerchandiseStack(new ItemStack(Material.COOKED_SALMON), 470, 400, true, true),
                new MerchandiseStack(new ItemStack(Material.DRIED_KELP), 80, 70, true, true),
                new MerchandiseStack(new ItemStack(Material.RABBIT_STEW), 150, 130, true, true),
                new MerchandiseStack(new ItemStack(Material.MUSHROOM_STEW), 370, 320, true, true),
                new MerchandiseStack(new ItemStack(Material.SUSPICIOUS_STEW), 100, 90, true, true)
        ));
         */


    }

    public static List<MerchandiseStack> getMerchandiseListById(int id) {
        return merchandiseMap.getOrDefault(id, Collections.emptyList());
    }
}
