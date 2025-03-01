package com.o8o1o5.solid.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.Document;

import java.util.UUID;

public class BalanceDatabaseManager {
    private static BalanceDatabaseManager instance;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public BalanceDatabaseManager(MongoDatabase db) {
        this.database = db;
        this.collection = database.getCollection("player_balance");
    }

    public double getPlayerBalance(UUID playerUUID) {
        Document doc = collection.find(Filters.eq("uuid", playerUUID.toString())).first();
        return (doc != null) ? doc.getDouble("balance") : 0.0;
    }

    public void updatePlayerBalance(UUID playerUUID, double balance) {
        Document doc = new Document("uuid", playerUUID.toString()).append("balance", balance);
        collection.replaceOne(Filters.eq("uuid", playerUUID.toString()), doc, new ReplaceOptions().upsert(true));
    }
}
