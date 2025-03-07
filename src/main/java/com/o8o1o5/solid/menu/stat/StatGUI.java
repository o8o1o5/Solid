package com.o8o1o5.solid.menu.stat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class StatGUI {
    public static void openStatGUI (Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "스탯");

        ItemStack borderItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta borderMeta = borderItem.getItemMeta();
        if (borderMeta != null) {
            borderMeta.setDisplayName(" ");
            borderItem.setItemMeta(borderMeta);
        }

        ItemStack statItem = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta statMeta = statItem.getItemMeta();
        if (statMeta != null) {
            statMeta.setDisplayName(ChatColor.AQUA + "뒤로 가기");
            statMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "§o\"나는 STR에 투자한다.\"",
                    ChatColor.GRAY + "§o- 마슐, 크림빵 광인 -",
                    ""
            ));
            statMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            statMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            statItem.setItemMeta(statMeta);
        }

        ItemStack statPointItem = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta statPointMeta = statPointItem.getItemMeta();
        if (statPointMeta != null) {
            statPointMeta.setDisplayName(ChatColor.AQUA + "스탯 포인트 구매");
            statPointMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "필요 : 5 (레벨)",
                    ChatColor.GRAY + "보유 : 13 SP"
            ));
        }

        ItemStack strItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta strMeta = strItem.getItemMeta();
        if (strMeta != null) {
            strMeta.setDisplayName(ChatColor.WHITE + "STR");
            strMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "근접 공격의 피해량이 증가합니다.",
                    "",
                    ChatColor.GRAY + "Lv. " + "123231",
                    ChatColor.DARK_GRAY + "§o근접 공격력 " + "192%" + " 증가",
                    ""
            ));

            strMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            strItem.setItemMeta(strMeta);
        }

        ItemStack spdItem = new ItemStack(Material.FEATHER);
        ItemMeta spdMeta = spdItem.getItemMeta();
        if (spdMeta != null) {
            spdMeta.setDisplayName(ChatColor.WHITE + "SPD");
            spdMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "이동 속도가 증가합니다.",
                    "",
                    ChatColor.GRAY + "Lv. ",
                    ChatColor.DARK_GRAY + "§o이동 속도 " + "111" + " 증가",
                    ""
            ));

            spdItem.setItemMeta(spdMeta);
        }

        ItemStack hpItem = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta hpMeta = hpItem.getItemMeta();
        if (hpMeta != null) {
            hpMeta.setDisplayName(ChatColor.WHITE + "HP");
            hpMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "체력이 증가합니다",
                    "",
                    ChatColor.GRAY + "Lv. ",
                    ChatColor.DARK_GRAY + "§o체력 " + "111" + " 증가",
                    ""
            ));

            hpItem.setItemMeta(hpMeta);
        }

        ItemStack hstItem = new ItemStack(Material.TRIDENT);
        ItemMeta hstMeta = hstItem.getItemMeta();
        if (hstMeta != null) {
            hstMeta.setDisplayName(ChatColor.WHITE + "HST");
            hstMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "공격 속도가 증가합니다.",
                    "",
                    ChatColor.GRAY + "Lv. ",
                    ChatColor.DARK_GRAY + "§o공격 속도 " + "1%" + " 증가",
                    ""
            ));

            hstMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            hstItem.setItemMeta(hstMeta);
        }

        ItemStack lukItem = new ItemStack(Material.EMERALD);
        ItemMeta lukMeta = lukItem.getItemMeta();
        if (lukMeta != null) {
            lukMeta.setDisplayName(ChatColor.WHITE + "LUK");
            lukMeta.setLore(Arrays.asList(
                    ChatColor.GRAY + "운이 증가합니다.",
                    "",
                    ChatColor.GRAY + "Lv. ",
                    ChatColor.DARK_GRAY + "§o추가 드롭률 " + "111" + " 증가",
                    ""
            ));

            lukItem.setItemMeta(lukMeta);
        }

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, borderItem);
        }

        inventory.setItem(4, statItem);
        inventory.setItem(20, strItem);
        inventory.setItem(24, spdItem);
        inventory.setItem(31, hpItem);
        inventory.setItem(38, hstItem);
        inventory.setItem(42, lukItem);

        player.openInventory(inventory);
    }
}
