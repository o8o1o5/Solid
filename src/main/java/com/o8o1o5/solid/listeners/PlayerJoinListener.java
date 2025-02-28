package com.o8o1o5.solid.listeners;

import com.o8o1o5.solid.economy.EconomyManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("안녕하세요, " + event.getPlayer().getName() + "님!");
    }
}
