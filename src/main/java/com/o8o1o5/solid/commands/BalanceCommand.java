package com.o8o1o5.solid.commands;

import com.o8o1o5.solid.economy.EconomyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand implements CommandExecutor {
    private final EconomyManager economyManager;

    public BalanceCommand(EconomyManager economyManager) {
        this.economyManager = economyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "플레이어만 사용할 수 있습니다.");
            return true;
        }

        Player player = (Player) sender;
        double balance = economyManager.getBalance(player.getUniqueId());

        player.sendMessage(ChatColor.GREEN + "현재 잔액: " + ChatColor.GOLD + balance + " 골드" + ChatColor.GREEN + " 입니다.");
        return true;
    }
}
