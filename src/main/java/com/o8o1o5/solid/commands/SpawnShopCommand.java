package com.o8o1o5.solid.commands;

import com.o8o1o5.solid.shop.ShopNPCManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnShopCommand implements CommandExecutor {
    private final ShopNPCManager shopNPCManager;

    public SpawnShopCommand(ShopNPCManager shopNPCManager) {
        this.shopNPCManager = shopNPCManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "사용법: /spawnshop <NPC ID>");
            return true;
        }

        Player player = (Player) sender;
        String npcId = args[0];
        Location location = player.getLocation();

        boolean success = shopNPCManager.spawnShop(npcId, location);
        if (!success) {
            player.sendMessage(ChatColor.RED + "해당 ID의 상점이 존재하지 않습니다! (ID: " + npcId + ")");
            return true;
        }

        player.sendMessage(ChatColor.GREEN + "상점 NPC 소환됨! (ID: " + npcId + ")");
        return true;
    }
}
