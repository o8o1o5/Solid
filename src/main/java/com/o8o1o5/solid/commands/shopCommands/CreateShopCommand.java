package com.o8o1o5.solid.commands.shopCommands;

import com.o8o1o5.solid.database.ShopNPCDatabaseManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class CreateShopCommand implements CommandExecutor {
    private final ShopNPCDatabaseManager shopDatabaseManager;

    public CreateShopCommand(ShopNPCDatabaseManager shopDatabaseManager) {
        this.shopDatabaseManager = shopDatabaseManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "사용법: /createshop <NPC ID> <NPC 이름> <직업>");
            return true;
        }

        Player player = (Player) sender;

        try {
            int npcId = Integer.parseInt(args[0]);
            String npcName = args[1];
            String npcJob = args[2].toUpperCase();

            boolean isValidProfession = false;
            for (Villager.Profession profession : Villager.Profession.values()) {
                if (profession.name().equals(npcJob)) {
                    isValidProfession = true;
                    break;
                }
            }

            if (!isValidProfession) {
                sender.sendMessage(ChatColor.RED + "유효한 NPC 직업이 아닙니다.");
                return true;
            }

            Location location = player.getLocation();
            if (shopDatabaseManager.saveShop(player, npcId, npcName, npcJob, location)) {
                sender.sendMessage(ChatColor.GREEN + "상점이 생성되었습니다. : " + npcName + " (ID: " + npcId + ", 직업: " + npcJob + ")");
            }
            return true;

        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "npcId는 숫자여야 합니다.");
            return true;
        }
    }
}
