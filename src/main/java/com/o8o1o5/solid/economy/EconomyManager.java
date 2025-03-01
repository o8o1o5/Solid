package com.o8o1o5.solid.economy;

import com.o8o1o5.solid.database.BalanceDatabaseManager;

import java.util.UUID;

public class EconomyManager {
    private static EconomyManager instance;
    private final BalanceDatabaseManager databaseManager;

    public EconomyManager(BalanceDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public double getBalance(UUID playerUUID) {
        return databaseManager.getPlayerBalance(playerUUID);
    }

    public void setBalance(UUID playerUUID, double amount) {
        databaseManager.updatePlayerBalance(playerUUID, amount);
    }

    public void addBalance(UUID playerUUID, double amount) {
        databaseManager.updatePlayerBalance(playerUUID, getBalance(playerUUID) + amount);
    }

    public boolean subtractBalance(UUID playerUUID, double amount) {
        double currentBalance = getBalance(playerUUID);
        if (currentBalance < amount) return false;
        double newBalance = currentBalance - amount;
        databaseManager.updatePlayerBalance(playerUUID, newBalance);
        return true;
    }
}
