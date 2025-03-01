package com.o8o1o5.solid.economy;

import com.o8o1o5.solid.database.BalanceDatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class CashManager {
    private final BalanceDatabaseManager db;

    private static final Map<Material, Integer> cashValues = new LinkedHashMap<>();

    static {
        cashValues.put(Material.IRON_INGOT, 1);
        cashValues.put(Material.GOLD_INGOT, 100);
        cashValues.put(Material.DIAMOND, 10000);
        cashValues.put(Material.NETHER_STAR, 1000000);
    }

    public CashManager(BalanceDatabaseManager databaseManager) {
        this.db = databaseManager;
    }

    private void removeCashItems(Player player) {
        ItemStack[] inventory = player.getInventory().getContents();

        for (int i = 0; i < inventory.length; i++) {
            ItemStack item = inventory[i];

            if (item != null && CashItemManager.isCashItem(item)) {
                player.getInventory().setItem(i, null); // 아이템 제거
            }
        }
    }




    public boolean deposit(Player player) {
        int totalAmount = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && CashItemManager.isCashItem(item)) {
                Material material = item.getType();
                int value = cashValues.getOrDefault(material, 0);
                if (value > 0) {
                    totalAmount += item.getAmount() * value;
                }
            }
        }

        if (totalAmount == 0) {
            player.sendMessage(ChatColor.RED + "화폐 아이템을 찾을 수 없습니다.");
            return false;
        }

        removeCashItems(player);

        UUID playerUUID = player.getUniqueId();
        db.updatePlayerBalance(playerUUID, db.getPlayerBalance(playerUUID) + totalAmount);

        player.sendMessage("" + ChatColor.GOLD + totalAmount + " 골드" + ChatColor.GREEN + "가 계좌로 입금되었습니다.");
        return true;
    }

    public boolean withdraw(Player player, int amount) {
        UUID playerUUID = player.getUniqueId();
        double balance = db.getPlayerBalance(playerUUID);

        if (balance < amount) {
            player.sendMessage(ChatColor.RED + "계좌에 잔액이 충분하지 않습니다.");
            return false;
        }

        List<ItemStack> itemsToGive = convertToItems(amount);
        if (itemsToGive.isEmpty()) {
            player.sendMessage(ChatColor.RED + "출금할 수 있는 화폐가 없습니다.");
            return false;
        }

        double newBalance = balance - amount;
        db.updatePlayerBalance(playerUUID, newBalance);

        for (ItemStack item : itemsToGive) {
            player.getInventory().addItem(item);
        }

        player.sendMessage("" + ChatColor.GOLD + amount + " 골드" + ChatColor.GREEN + "가 인벤토리로 지급되었습니다.");
        return true;
    }

    public List<ItemStack> convertToItems(int amount) {
        List<ItemStack> items = new ArrayList<>();

        while (amount > 0) {
            if (amount >= 1000000) {
                items.add(CashItemManager.createCashItem(Material.NETHER_STAR, amount / 1000000));
                amount %= 1000000;
            } else if (amount >= 10000) {
                items.add(CashItemManager.createCashItem(Material.DIAMOND, amount / 10000));
                amount %= 10000;
            } else if (amount >= 100) {
                items.add(CashItemManager.createCashItem(Material.GOLD_INGOT, amount / 100));
                amount %= 100;
            } else {
                items.add(CashItemManager.createCashItem(Material.IRON_INGOT, amount));
                break;
            }
        }

        return items;
    }
}
