package com.o8o1o5.solid.NPC.shopNPC;

import com.o8o1o5.solid.NPC.NPCInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class ShopNPC implements NPCInfo {
    private final int id;
    private final Location location;
    private final String name;
    private final Villager.Profession profession;

    public ShopNPC(int id, Location location, String name, Villager.Profession profession) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.profession = profession;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Villager.Profession getProffesion() {
        return profession;
    }

    @Override
    public void interact(Player player) {
        ShopGUI.openShopGUI(player, id);
    }
}
