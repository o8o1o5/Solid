package com.o8o1o5.solid.commands.economyCommands;

import com.o8o1o5.solid.economy.CashManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WithdrawCommand implements CommandExecutor {
    private final CashManager cashManager;

    public WithdrawCommand(CashManager cashManager) {
        this.cashManager = cashManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (args.length != 1) {
            player.sendMessage(ChatColor.RED + "사용법: /withdraw <금액>");
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[0]);
            if (amount <= 0) {
                player.sendMessage(ChatColor.RED + "0보다 큰 금액을 입력하세요.");
                return true;
            }
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "올바른 숫자를 입력하세요.");
            return true;
        }

        cashManager.withdraw(player, amount);
        return true;
    }
}
