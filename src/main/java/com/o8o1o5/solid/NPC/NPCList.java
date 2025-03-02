package com.o8o1o5.solid.NPC;

import com.o8o1o5.solid.NPC.shopNPC.ShopNPC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Villager;

import java.util.HashMap;
import java.util.Map;

public class NPCList {
    public static final Map<Integer, NPCInfo> npcMap = new HashMap<>();

    public static void registerNPC(NPCInfo npc) {
        npcMap.put(npc.getId(), npc);
    }

    public static NPCInfo getNPCById(int id) {
        return npcMap.get(id);
    }

    public static void loadNPCs() {
        registerNPC(new ShopNPC(10000,
                new Location(Bukkit.getWorld("world"), 100, 100, 100),
                "경열",
                Villager.Profession.FARMER));
        registerNPC(new ShopNPC(10001,
                new Location(Bukkit.getWorld("world"), 103, 100, 100),
                "희정",
                Villager.Profession.TOOLSMITH));
        registerNPC(new ShopNPC(10002,
                new Location(Bukkit.getWorld("world"), 106, 100, 100),
                "재윤",
                Villager.Profession.CLERIC));
    }
}
