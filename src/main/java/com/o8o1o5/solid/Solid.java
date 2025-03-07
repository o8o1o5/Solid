package com.o8o1o5.solid;

import com.mongodb.client.*;
import com.o8o1o5.solid.NPC.InteractNPCListener;
import com.o8o1o5.solid.NPC.NPCList;
import com.o8o1o5.solid.NPC.shopNPC.ShopClickListener;
import com.o8o1o5.solid.commands.*;
import com.o8o1o5.solid.economy.EconomyManager;
import com.o8o1o5.solid.economy.BalanceDatabaseManager;
import com.o8o1o5.solid.listeners.PlayerJoinListener;

import com.o8o1o5.solid.menu.PlayerOpenMenuListener;
import com.o8o1o5.solid.menu.MenuClickListener;
import com.o8o1o5.solid.menu.stat.StatClickListener;
import com.o8o1o5.solid.menu.warp.WarpClickListener;
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
        database = mongoClient.getDatabase("solid");

        BalanceDatabaseManager databaseManager = new BalanceDatabaseManager(database);
        EconomyManager economyManager = new EconomyManager(databaseManager);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractNPCListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ShopClickListener(economyManager), this);

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerOpenMenuListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MenuClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WarpClickListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new StatClickListener(), this);

        getCommand("balance").setExecutor(new BalanceCommand(economyManager));
        getCommand("pay").setExecutor(new PayCommand(economyManager));
        getCommand("eco").setExecutor(new EcoCommand(economyManager));
        getCommand("shop").setExecutor(new ShopCommand());

        NPCList.loadNPCs();

        getLogger().info("Solid Plugin is Enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Solid Plugin is Disabled");
    }
}
