package com.o8o1o5.solid.menu.warp;

import com.o8o1o5.solid.LOCATIONS;
import com.o8o1o5.solid.menu.MenuGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class WarpClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("워프 이동")) {
            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        ItemStack item = event.getCurrentItem();

        if (item.getItemMeta().getDisplayName().contains("뒤로 가기")) {
            MenuGUI.openMenuGUI(player);
        }

        if (item.getItemMeta().getDisplayName().contains("로비")) {
            player.teleport(LOCATIONS.locationLobby);
        }
    }
}
