package com.o8o1o5.solid.NPC.shopNPC;

import com.o8o1o5.solid.NPC.NPCInfo;
import com.o8o1o5.solid.NPC.NPCList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopGUI {
    public static void openShopGUI(Player player, int npcId) {
        player.sendMessage("GUI 열림: " + npcId);
        NPCInfo npc = NPCList.getNPCById(npcId);

        String name = npc.getName();

        List<MerchandiseStack> merchandiseList = MerchandiseList.getMerchandiseListById(npcId);
        Inventory inventory = Bukkit.createInventory(null, 54, name);

        for (int i = 0; i < Math.min(merchandiseList.size(), 45); i++) {
            MerchandiseStack merchandise = merchandiseList.get(i);
            ItemStack item = merchandise.getItem();
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                List<String> lore = new ArrayList<>();
                lore.add("§e구매 가격: §f" + merchandise.getBuyPrice() + " 코인");
                lore.add("§6판매 가격: §f" + merchandise.getSellPrice() + " 코인");
                lore.add(merchandise.isBuyable() ? "§a구매 가능" : "§c구매 불가");
                lore.add(merchandise.isSellable() ? "§a판매 가능" : "§c판매 불가");

                meta.setLore(lore);
                item.setItemMeta(meta);
            }

            inventory.setItem(i, item);
        }

        player.openInventory(inventory);
    }
}
