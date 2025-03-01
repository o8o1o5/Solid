package com.o8o1o5.solid.commands.economyCommands;

import com.o8o1o5.solid.economy.EconomyManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PayCommand implements CommandExecutor {
    private final EconomyManager economyManager;

    public PayCommand(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player senderPlayer = (Player) sender;
        UUID senderUUID = senderPlayer.getUniqueId();

        if (args.length != 2) {
            senderPlayer.sendMessage(ChatColor.RED + "사용법: /pay <플레이어> <금액>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null || !targetPlayer.isOnline()) {
            senderPlayer.sendMessage(ChatColor.RED + "해당 플레이어를 찾을 수 없습니다.");
            return true;
        }

        if (targetPlayer.equals(senderPlayer)) {
            senderPlayer.sendMessage(ChatColor.RED + "자기 자신에게 송금할 수 없습니다.");
            return true;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[1]);
            if (amount <= 0) {
                senderPlayer.sendMessage(ChatColor.RED + "0보다 큰 금액을 입력하세요.");
                return true;
            }
        } catch (NumberFormatException e) {
            senderPlayer.sendMessage(ChatColor.RED + "올바른 숫자를 입력하세요.");
            return true;
        }

        UUID targetUUID = targetPlayer.getUniqueId();

        if (!economyManager.subtractBalance(senderUUID, amount)) {
            senderPlayer.sendMessage(ChatColor.RED + "잔액이 부족합니다.");
            return true;
        }

        economyManager.addBalance(targetUUID, amount);

        senderPlayer.sendMessage(ChatColor.GREEN + targetPlayer.getName() + " 님에게" +
                ChatColor.GOLD + amount + " 골드" + ChatColor.GREEN + " 를 보냈습니다.");
        targetPlayer.sendMessage(ChatColor.GREEN + senderPlayer.getName() + "님이" +
                ChatColor.GOLD + amount + " 골드" + ChatColor.GREEN + " 를 보냈습니다.");

        return true;
    }
}
