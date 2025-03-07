package com.o8o1o5.solid.menu;

import com.o8o1o5.solid.menu.stat.StatGUI;
import com.o8o1o5.solid.menu.warp.WarpGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("메뉴")) {
            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        ItemStack item = event.getCurrentItem();

        if (item.getItemMeta().getDisplayName().contains("워프 이동")) {
            WarpGUI.openWarpGUI(player);
        }

        else if (item.getItemMeta().getDisplayName().contains("스탯")) {
            StatGUI.openStatGUI(player);
        }
    }
}
