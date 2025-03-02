package com.o8o1o5.solid.NPC.shopNPC;

import org.bukkit.inventory.ItemStack;

public class MerchandiseStack {
    private final ItemStack item;
    private final int sellPrice;
    private final int buyPrice;
    private final boolean canSell;
    private final boolean canBuy;

    public MerchandiseStack(ItemStack item, int sellPrice, int buyPrice, boolean canSell, boolean canBuy) {
        this.item = item;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.canSell = canSell;
        this.canBuy = canBuy;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public boolean isSellable() {
        return canSell;
    }

    public boolean isBuyable() {
        return canBuy;
    }
}
