package com.o8o1o5.solid.NPC.shopNPC;

import com.o8o1o5.solid.economy.EconomyManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ShopClickListener implements Listener {
    private final EconomyManager economyManager;

    public ShopClickListener(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        if (!(inventory.getHolder() instanceof ShopInventoryHolder)) return;
        int npcId = ((ShopInventoryHolder) inventory.getHolder()).getNpcId();

        if (event.getCurrentItem().getType() == Material.BARRIER) {
            event.setCancelled(true);
            player.closeInventory();
            return;
        }

        List<MerchandiseStack> merchandiseList = MerchandiseList.getMerchandiseListById(npcId);
        int[] shopSlots = {
                10,11,12,13,14,15,16,
                19,20,21,22,23,24,25,
                28,29,30,31,32,33,34,
                37,38,39,40,41,42,43
        };
        int index = -1;

        for (int i = 0; i < shopSlots.length; i++) {
            if (event.getRawSlot() == shopSlots[i]) {
                index = i;
                break;
            }
        }

        if (index == -1 || index >= merchandiseList.size()) return;

        MerchandiseStack selectedMerchandise = merchandiseList.get(index);
        ClickType clickType = event.getClick();

        boolean canBuy = selectedMerchandise.isBuyable();
        boolean canSell = selectedMerchandise.isSellable();
        int buyPrice = selectedMerchandise.getBuyPrice();
        int sellPrice = selectedMerchandise.getSellPrice();
        ItemStack item = selectedMerchandise.getItem();

        if (clickType == ClickType.LEFT || clickType == ClickType.SHIFT_LEFT) {
            event.setCancelled(true);

            if (!canBuy) {
                player.sendMessage(ChatColor.RED + "구매할 수 없는 상품입니다.");
                return;
            }

            int quantity = (clickType == ClickType.LEFT) ? 1 : 64;
            int totalPrice = buyPrice * quantity;

            if (!canFitInInventory(player, item, quantity)) {
                player.sendMessage(ChatColor.RED + "인벤토리의 공간이 부족합니다.");
                return;
            }

            if (!economyManager.subtractBalance(player.getUniqueId(), totalPrice)) {
                player.sendMessage(ChatColor.RED + "잔액이 부족합니다.");
                return;
            }

            addItemsToInventory(player, item, quantity);
            player.sendMessage(ChatColor.GREEN + "구매가 완료되었습니다. " + ChatColor.RED + "(-" + totalPrice + ")");
            return;
        }

        else if (clickType == ClickType.SHIFT_RIGHT || clickType == ClickType.RIGHT) {
            event.setCancelled(true);

            if (!canSell) {
                player.sendMessage(ChatColor.RED + "판매할 수 없는 상품입니다.");
                return;
            }

            int haveAmount = getItemAmount(player, item);

            if (haveAmount < 1) {
                player.sendMessage(ChatColor.RED + "해당 아이템이 인벤토리에 없습니다.");
                return;
            }

            int sellAmount = clickType == ClickType.RIGHT ? 1 : haveAmount;

            int totalPrice = sellPrice * sellAmount;

            removeItemsFromInventory(player, item, sellAmount);
            economyManager.addBalance(player.getUniqueId(), totalPrice);

            player.sendMessage(ChatColor.GREEN + "판매가 완료되었습니다. " + ChatColor.YELLOW + "(+" + totalPrice + ")");
        }

        event.setCancelled(true);
    }

    private void addItemsToInventory(Player player, ItemStack item, int amount) {
        ItemStack itemClone = item.clone(); // 아이템 복사
        itemClone.setAmount(amount); // 개수 설정
        player.getInventory().addItem(itemClone); // 인벤토리에 추가
    }

    private boolean canFitInInventory(Player player, ItemStack item, int amount) {
        int remaining = amount; // 필요한 공간

        for (ItemStack invItem : player.getInventory().getContents()) {
            if (invItem == null || invItem.getType() == Material.AIR) {
                // 빈 슬롯이면 최대 스택 크기만큼 공간 확보 가능
                remaining -= Math.min(item.getMaxStackSize(), remaining);
            } else if (invItem.isSimilar(item)) {
                // 동일한 아이템이면 남은 공간만큼 추가 가능
                remaining -= Math.min(item.getMaxStackSize() - invItem.getAmount(), remaining);
            }

            // 만약 모든 아이템을 넣을 수 있다면 true 반환
            if (remaining <= 0) return true;
        }

        return false; // 공간이 부족하면 false 반환
    }

    private int getItemAmount(Player player, ItemStack item) {
        int count = 0;

        for (ItemStack invItem : player.getInventory().getContents()) {
            if (invItem != null && invItem.getType() == item.getType() && invItem.isSimilar(item)) {
                count += invItem.getAmount();
            }
        }

        return count;
    }

    private void removeItemsFromInventory(Player player, ItemStack item, int amount) {
        for (ItemStack invItem : player.getInventory().getContents()) {
            if (invItem != null && invItem.getType() == item.getType() && invItem.isSimilar(item)) {
                int removeAmount = Math.min(amount, invItem.getAmount());
                invItem.setAmount(invItem.getAmount() - removeAmount);
                amount -= removeAmount;

                if (amount <= 0) break;
            }
        }
    }
}
