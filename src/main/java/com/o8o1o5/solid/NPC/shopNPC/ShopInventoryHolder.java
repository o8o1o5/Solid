package com.o8o1o5.solid.NPC.shopNPC;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ShopInventoryHolder implements InventoryHolder {
    private final int npcId;

    public ShopInventoryHolder(int npcId) {
        this.npcId = npcId;
    }

    public int getNpcId() {
        return npcId;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}

