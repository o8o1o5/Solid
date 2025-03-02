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
                new MerchandiseStack(
                        new ItemStack(Material.NETHERITE_BLOCK, 3) {{
                            ItemMeta meta = getItemMeta();
                            if (meta != null) {
                                meta.setDisplayName("⚡ 강화된 네더라이트 블록");
                                meta.setLore(Arrays.asList(
                                        "§7이 아이템은 특별한 효과가 있습니다.",
                                        "§e사용 시 강력한 능력이 발휘될 수 있음!"
                                ));
                                meta.addEnchant(Enchantment.UNBREAKING, 1, true);
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                                setItemMeta(meta);
                            }
                        }},
                        1000, 500, true, false
                ),
                new MerchandiseStack(new ItemStack(Material.DIAMOND, 5), 500, 250, true, true),
                new MerchandiseStack(new ItemStack(Material.GOLDEN_APPLE, 1), 200, 100, true, true),
                new MerchandiseStack(new ItemStack(Material.WOODEN_SHOVEL, 1), 50, 20, true, true)
        ));
    }

    public static List<MerchandiseStack> getMerchandiseListById(int id) {
        return merchandiseMap.getOrDefault(id, Collections.emptyList());
    }
}
