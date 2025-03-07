package com.o8o1o5.solid.NPC.shopNPC;

import com.o8o1o5.solid.NPC.NPCInfo;
import com.o8o1o5.solid.NPC.NPCList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShopGUI {
    public static void openShopGUI(Player player, int npcId) {
        player.sendMessage("GUI 열림: " + npcId);
        NPCInfo npc = NPCList.getNPCById(npcId);

        String name = npc.getName();

        List<MerchandiseStack> merchandiseList = MerchandiseList.getMerchandiseListById(npcId);
        Inventory inventory = Bukkit.createInventory(new ShopInventoryHolder(npcId), 54, name);

        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeButton.getItemMeta();
        if (closeMeta != null) {
            closeMeta.setDisplayName("§c상점 닫기");
            closeButton.setItemMeta(closeMeta);
        }
        inventory.setItem(49, closeButton);

        int[] shopSlots = {
                10,11,12,13,14,15,16,
                19,20,21,22,23,24,25,
                28,29,30,31,32,33,34,
                37,38,39,40,41,42,43
        };
        int index = 0;

        for (MerchandiseStack merchandise : merchandiseList) {
            if (index >= shopSlots.length) break;

            ItemStack item = merchandise.getItem().clone();
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                List<String> lore = new ArrayList<>();

                String buyLore = merchandise.isBuyable() ?
                        ChatColor.YELLOW + "구매 가격: " + ChatColor.WHITE + merchandise.getBuyPrice() + " 코인" :
                        ChatColor.YELLOW + "구매 가격: " + ChatColor.RED + "구매 불가";
                String sellLore = merchandise.isSellable() ?
                        ChatColor.YELLOW + "판매 가격: " + ChatColor.WHITE + merchandise.getSellPrice() + " 코인" :
                        ChatColor.YELLOW + "판매 가격: " + ChatColor.RED + "판매 불가";

                lore.add(buyLore);
                lore.add(sellLore);

                meta.setLore(lore);
                item.setItemMeta(meta);
            }

            inventory.setItem(shopSlots[index], item);
            index++;
        }

        player.openInventory(inventory);
    }
}

