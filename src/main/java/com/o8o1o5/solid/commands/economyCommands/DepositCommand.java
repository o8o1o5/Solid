package com.o8o1o5.solid.commands.economyCommands;

import com.o8o1o5.solid.economy.CashManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DepositCommand implements CommandExecutor {
    private final CashManager cashManager;

    public DepositCommand (CashManager cashManager) {
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

        cashManager.deposit(player);
        return true;
    }
}
