package com.o8o1o5.solid.shop;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ShopDataManager {
    private final MongoCollection<Document> collection;

    public ShopDataManager(MongoDatabase database) {
        this.collection = database.getCollection("shops");
    }

    public boolean saveShop(Player player, String id, String name, String job, Location location) {
        if (location == null || location.getWorld() == null) {
            player.sendMessage(ChatColor.RED + "위치 정보가 없습니다. ID: " + id);
            return false;
        }

        if (collection.find(Filters.eq("id", id)).first() != null) {
            player.sendMessage(ChatColor.RED + "이미 존재하는 상점 ID입니다: " + id);
            return false;
        }

        Document shop = new Document("id", id)
                .append("name", name)
                .append("job", job)
                .append("world", location.getWorld().getName())
                .append("x", location.getX() * 1.0)
                .append("y", location.getY() * 1.0)
                .append("z", location.getZ() * 1.0)
                .append("yaw", (double) location.getYaw())
                .append("pitch", (double) location.getPitch());

        collection.insertOne(shop);
        return true;
    }

    public Map<String, Document> loadShops() {
        Map<String, Document> shops = new HashMap<>();
        for (Document doc : collection.find()) {
            shops.put(doc.getString("id"), doc);
        }
        return shops;
    }

    public Document getShop(String id) {
        return collection.find(Filters.eq("id", id)).first();
    }

    public void removeShop(String id) {
        collection.deleteOne(Filters.eq("id", id));
    }
}
