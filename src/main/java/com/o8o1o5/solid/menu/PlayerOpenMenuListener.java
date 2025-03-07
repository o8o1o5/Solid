package com.o8o1o5.solid.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerOpenMenuListener implements Listener {
    @EventHandler
    public void onPlayerOpenMenu(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();

        if(!player.isSneaking()) {
            return;
        }

        event.setCancelled(true);
        MenuGUI.openMenuGUI(player);
    }
}
