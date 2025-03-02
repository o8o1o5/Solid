package com.o8o1o5.solid.commands;

import com.o8o1o5.solid.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EcoCommand implements CommandExecutor {
    private final EconomyManager economyManager;

    public EcoCommand(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "명령어를 사용할 권한이 없습니다.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "사용법: /eco <set|add|remove|balance> <플레이어> [금액]");
            return true;
        }

        String action = args[0];
        Player targetPlayer = Bukkit.getPlayer(args[1]);

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            sender.sendMessage("해당 플레이어를 찾을 수 없습니다.");
            return true;
        }

        UUID targetUUID = targetPlayer.getUniqueId();

        if (action.equalsIgnoreCase("balance")) {
            double balance = economyManager.getBalance(targetUUID);
            sender.sendMessage(ChatColor.GREEN + targetPlayer.getName() + "의 잔액: " + ChatColor.GOLD + balance + " 골드");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "사용법: /eco <set|add|remove> <플레이어> <금액>");
            return true;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "올바른 숫자를 입력하세요.");
            return true;
        }

        switch (action.toLowerCase()) {
            case "set":
                economyManager.setBalance(targetUUID, amount);
                sender.sendMessage(ChatColor.GREEN + targetPlayer.getName() + "의 잔액을 " + amount + " 으로 설정했습니다.");
                break;

            case "add":
                economyManager.addBalance(targetUUID, amount);
                sender.sendMessage(ChatColor.GREEN + targetPlayer.getName() + "의 잔액에 " + amount + " 골드를 추가했습니다.");
                break;

            case "remove":
                if (!economyManager.subtractBalance(targetUUID, amount)) {
                    sender.sendMessage(ChatColor.RED + "잔액이 부족하여 차감할 수 없습니다.");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + targetPlayer.getName() + "의 잔액에서 " + amount + " 골드를 차감했습니다.");
                break;

            default:
                sender.sendMessage(ChatColor.RED + "사용법: /eco <set|add|remove|balance> <플레이어> [금액]");
                return true;
        }

        return true;
    }
}
