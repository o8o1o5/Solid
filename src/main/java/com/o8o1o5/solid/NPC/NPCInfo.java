package com.o8o1o5.solid.NPC;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public interface NPCInfo {
    int getId();
    Location getLocation();
    String getName();
    Villager.Profession getProffesion();
    void interact(Player player);
}
