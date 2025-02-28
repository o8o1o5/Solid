package com.o8o1o5.solid.shop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCClickListener implements Listener {
    private final ShopNPCManager shopNPCManager;

    public NPCClickListener(ShopNPCManager shopNPCManager) {
        this.shopNPCManager = shopNPCManager;
    }

    @EventHandler
    public void onShopNPCClick(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (!(entity instanceof Villager)) return;

        Villager villager = (Villager) entity;
        Player player = event.getPlayer();

        if (shopNPCManager.isShopNPC(villager)) {
            event.setCancelled(true);
            openShopGUI(player);
        }
    }

    public void openShopGUI(Player player) {
        player.sendMessage(ChatColor.DARK_PURPLE + "DEBUGGING!");
    }
}
