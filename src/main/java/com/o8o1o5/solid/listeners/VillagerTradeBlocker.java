package com.o8o1o5.solid.listeners;

import com.o8o1o5.solid.shop.ShopNPCManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerTradeBlocker implements Listener {
    private final ShopNPCManager shopNPCManager;

    public VillagerTradeBlocker(ShopNPCManager shopNPCManager) {
        this.shopNPCManager = shopNPCManager;
    }

    @EventHandler
    public void onVillagerTrade(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity instanceof Villager) {
            Villager villager = (Villager) entity;
            Player player = (Player) event.getPlayer();

            if (!shopNPCManager.isShopNPC(villager)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "주민과 거래할 수 없습니다.");
            }
        }
    }

}
