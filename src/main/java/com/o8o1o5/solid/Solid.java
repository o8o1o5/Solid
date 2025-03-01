package com.o8o1o5.solid;

import com.mongodb.client.*;
import com.o8o1o5.solid.commands.economyCommands.*;
import com.o8o1o5.solid.commands.shopCommands.CreateShopCommand;
import com.o8o1o5.solid.commands.shopCommands.RemoveShopCommand;
import com.o8o1o5.solid.commands.shopCommands.SpawnShopCommand;
import com.o8o1o5.solid.database.ShopNPCDatabaseManager;
import com.o8o1o5.solid.economy.CashItemManager;
import com.o8o1o5.solid.economy.CashManager;
import com.o8o1o5.solid.economy.EconomyManager;
import com.o8o1o5.solid.database.BalanceDatabaseManager;
import com.o8o1o5.solid.listeners.PlayerJoinListener;
import com.o8o1o5.solid.listeners.VillagerTradeBlocker;
import com.o8o1o5.solid.shop.NPCClickListener;
import com.o8o1o5.solid.shop.ShopNPCManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Solid extends JavaPlugin {
    private static Solid instance;

    private MongoClient mongoClient;
    private MongoDatabase database;

    public static Solid getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("solid_economy");

        BalanceDatabaseManager databaseManager = new BalanceDatabaseManager(database);
        CashManager cashManager = new CashManager(databaseManager);
        EconomyManager economyManager = new EconomyManager(databaseManager);
        ShopNPCDatabaseManager shopDatabaseManager = new ShopNPCDatabaseManager(database);
        ShopNPCManager shopNPCManager = new ShopNPCManager(this, database);

        CashItemManager.init(this);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new VillagerTradeBlocker(shopNPCManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(new NPCClickListener(shopNPCManager), this);

        getCommand("balance").setExecutor(new BalanceCommand(economyManager));
        getCommand("pay").setExecutor(new PayCommand(economyManager));
        getCommand("eco").setExecutor(new EcoCommand(economyManager));
        getCommand("deposit").setExecutor(new DepositCommand(cashManager));
        getCommand("withdraw").setExecutor(new WithdrawCommand(cashManager));
        getCommand("createshop").setExecutor(new CreateShopCommand(shopDatabaseManager));
        getCommand("spawnshop").setExecutor(new SpawnShopCommand(shopNPCManager));
        getCommand("removeshop").setExecutor(new RemoveShopCommand(shopNPCManager));

        getLogger().info("Solid Plugin is Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Solid Plugin is Disabled");
    }
}
