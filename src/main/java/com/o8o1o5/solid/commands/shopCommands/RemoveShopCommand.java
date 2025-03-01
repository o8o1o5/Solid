package com.o8o1o5.solid.commands.shopCommands;

import com.o8o1o5.solid.shop.ShopNPCManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveShopCommand implements CommandExecutor {
    private final ShopNPCManager shopNPCManager;

    public RemoveShopCommand(ShopNPCManager shopNPCManager) {
        this.shopNPCManager = shopNPCManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "사용법: /removeshop <상점 ID>");
            return true;
        }

        Player player = (Player) sender;

        try {
            int npcId = Integer.parseInt(args[0]);

            boolean success = shopNPCManager.RemoveShopNPC(npcId);
            if (!success) {
                player.sendMessage(ChatColor.RED + "해당 ID를 가진 NPC를 찾을 수 없거나, NPC가 존재하지 않습니다.");
                return true;
            }

            player.sendMessage(ChatColor.GREEN + "상점 NPC가 삭제되었습니다.");
            return true;
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "<NPC ID>는 숫자여야 합니다.");
            return true;
        }
    }
}
