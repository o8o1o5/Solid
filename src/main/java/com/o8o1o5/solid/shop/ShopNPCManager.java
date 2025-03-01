package com.o8o1o5.solid.shop;

import com.mongodb.client.MongoDatabase;
import com.o8o1o5.solid.database.ShopNPCDatabaseManager;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ShopNPCManager {
    private final JavaPlugin plugin;
    private final ShopNPCDatabaseManager shopNPCDatabaseManager;
    private final NamespacedKey npcKey;
    private final Map<Integer, Villager> shopNpcs = new HashMap<>();

    public ShopNPCManager(JavaPlugin plugin, MongoDatabase database) {
        this.plugin = plugin;
        this.shopNPCDatabaseManager = new ShopNPCDatabaseManager(database);
        this.npcKey = new NamespacedKey(plugin, "shop_npc");
    }

    public boolean spawnShopNPC(int id, Location location) {
        Document shopNPCData = shopNPCDatabaseManager.getShop(id);
        if (shopNPCData == null) {
            plugin.getLogger().warning("상점 ID '" + id + "'를 찾을 수 없습니다!");
            return false;
        }
        spawnShopNPCFromDatabase(shopNPCData);
        return true;
    }

    private void spawnShopNPCFromDatabase(Document shopNPCData) {
        int id = shopNPCData.getInteger("id");
        String name = shopNPCData.getString("name");
        String job = shopNPCData.getString("job");
        String worldName = shopNPCData.getString("world");

        double x = shopNPCData.containsKey("x") ? ((Number) shopNPCData.get("x")).doubleValue() : 0.0;
        double y = shopNPCData.containsKey("y") ? ((Number) shopNPCData.get("y")).doubleValue() : 0.0;
        double z = shopNPCData.containsKey("z") ? ((Number) shopNPCData.get("z")).doubleValue() : 0.0;
        float yaw = shopNPCData.containsKey("yaw") ? ((Number) shopNPCData.get("yaw")).floatValue() : 0.0f;
        float pitch = shopNPCData.containsKey("pitch") ? ((Number) shopNPCData.get("pitch")).floatValue() : 0.0f;

        if (worldName == null) {
            Bukkit.getLogger().warning("상점 '" + id + "'의 월드 정보가 없습니다!");
            return;
        }

        World world = Bukkit.getServer().getWorld(worldName);
        if (world == null) {
            Bukkit.getLogger().warning("월드를 찾을 수 없습니다! ID: " + id);
            return;
        }

        Location location = new Location(world, x, y, z, yaw, pitch);

        Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);
        villager.setCustomName(ChatColor.GOLD + name);
        villager.setCustomNameVisible(true);
        villager.setAI(false);
        villager.setInvulnerable(true);
        villager.getPersistentDataContainer().set(npcKey, PersistentDataType.BYTE, (byte) 1);
        villager.setProfession(Villager.Profession.valueOf(job));

        shopNpcs.put(id, villager);
    }

    public boolean RemoveShopNPC(int id) {
        return RemoveShopNPCFromDatabase(id);
    }

    private boolean RemoveShopNPCFromDatabase(int id) {
        Document shopNPCData = shopNPCDatabaseManager.getShop(id);

        if (shopNPCData == null) {
            Bukkit.getLogger().warning("ID " + id + "에 해당하는 상점 NPC를 DB에서 찾을 수 없습니다.");
            return false;
        }

        shopNPCDatabaseManager.removeShop(id);
        return true;
    }

    public boolean isShopNPC(Villager villager) {
        return villager.getPersistentDataContainer().has(npcKey, PersistentDataType.BYTE);
    }
}
