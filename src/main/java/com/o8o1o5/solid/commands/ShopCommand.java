package com.o8o1o5.solid.commands;

import com.o8o1o5.solid.NPC.NPCInfo;
import com.o8o1o5.solid.NPC.NPCList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.List;

public class ShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "사용법: /shop <ID1> <ID2> <ID3> ...");
            return true;
        }

        List<Integer> idList = new ArrayList<>();
        for (String arg : args) {
            try {
                int id = Integer.parseInt(arg);
                idList.add(id);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "잘못된 ID: " + arg);
                return true;
            }
        }

        int[] npcIds = idList.stream().mapToInt(Integer::intValue).toArray();

        for (int npcId : npcIds) {
            if (!spawnShop(npcId)) {
                player.sendMessage(ChatColor.RED + "알 수 없는 ID: " + npcId);
            }
            player.sendMessage(ChatColor.GREEN + "상점 소환됨: " + npcId);
        }

        return true;
    }

    public boolean spawnShop(int npcId) {
        NPCInfo npc = NPCList.getNPCById(npcId);

        if (npc != null) {
            int id = npc.getId();
            String name = npc.getName();
            Location location = npc.getLocation();

            if (location == null) {
                Bukkit.getLogger().warning("위치를 찾을 수 없습니다.");
                return false;
            }

            World world = location.getWorld();
            if (world == null) {
                Bukkit.getLogger().warning("월드를 찾을 수 없습니다.");
                return false;
            }

            Villager villager = (Villager) world.spawnEntity(location, EntityType.VILLAGER);

            if (villager instanceof Villager) {
                villager.setCustomName(name);
                villager.setCustomNameVisible(true);
                villager.setProfession(npc.getProffesion());
                villager.setAI(false);
                villager.setInvulnerable(true);
                villager.setPersistent(true);
            }

            return true;
        }

        return false;
    }
}
