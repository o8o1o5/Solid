package com.o8o1o5.solid.NPC;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class InteractNPCListener implements Listener {
    @EventHandler
    public void onPlayerInteractEventNPC(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (!(entity instanceof Villager)) {
            return;
        }

        String name = entity.getCustomName();
        if (name == null) {
            return;
        }

        for (NPCInfo npc : NPCList.npcMap.values()) {
            if (npc.getName().equals(name)) {
                event.setCancelled(true);
                npc.interact(event.getPlayer());
                return;
            }
        }
    }
}
