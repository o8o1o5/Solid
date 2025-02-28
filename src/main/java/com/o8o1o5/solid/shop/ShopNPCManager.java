package com.o8o1o5.solid.shop;

import com.mongodb.client.MongoDatabase;
import com.o8o1o5.solid.shop.ShopDataManager;
import org.bson.Document;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ShopNPCManager {
    private final JavaPlugin plugin;
    private final ShopDataManager shopDataManager;
    private final NamespacedKey npcKey;
    private final Map<String, Villager> shopNpcs = new HashMap<>();

    public ShopNPCManager(JavaPlugin plugin, MongoDatabase database) {
        this.plugin = plugin;
        this.shopDataManager = new ShopDataManager(database);
        this.npcKey = new NamespacedKey(plugin, "shop_npc");
        loadShopsFromDatabase();
    }

    private void loadShopsFromDatabase() {
        Map<String, Document> shops = shopDataManager.loadShops();
        for (Map.Entry<String, Document> entry : shops.entrySet()) {
            spawnShopFromDatabase(entry.getValue());
        }
    }

    public boolean spawnShop(String id, Location location) {
        Document shopData = shopDataManager.getShop(id);
        if (shopData == null) {
            plugin.getLogger().warning("상점 ID '" + id + "'를 찾을 수 없습니다!");
            return false;
        }
        spawnShopFromDatabase(shopData);
        return true;
    }

    private void spawnShopFromDatabase(Document shopData) {
        String id = shopData.getString("id");
        String name = shopData.getString("name");
        String job = shopData.getString("job");
        String worldName = shopData.getString("world");

        double x = shopData.containsKey("x") ? ((Number) shopData.get("x")).doubleValue() : 0.0;
        double y = shopData.containsKey("y") ? ((Number) shopData.get("y")).doubleValue() : 0.0;
        double z = shopData.containsKey("z") ? ((Number) shopData.get("z")).doubleValue() : 0.0;
        float yaw = shopData.containsKey("yaw") ? ((Number) shopData.get("yaw")).floatValue() : 0.0f;
        float pitch = shopData.containsKey("pitch") ? ((Number) shopData.get("pitch")).floatValue() : 0.0f;

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

    public boolean isShopNPC(Villager villager) {
        return villager.getPersistentDataContainer().has(npcKey, PersistentDataType.BYTE);
    }

}
