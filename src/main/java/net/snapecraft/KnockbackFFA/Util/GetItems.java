package net.snapecraft.KnockbackFFA.Util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GetItems {

    public static void getWorldItems(Player p) {
        List<String> sl = Config.getItemsOfKit("starterItems");
        for(String s : sl) {
            Material material = Material.getMaterial(s);

            //ToDo: Special Items hier einfügen
            if(material.equals(Material.STICK)) {
                ItemStack stick = new ItemStack(Material.STICK);
                ItemMeta stickm = stick.getItemMeta();
                stickm.setDisplayName("§5Knockback§r-§aStick");
                stickm.addEnchant(Enchantment.KNOCKBACK, 10, true);
                stick.setItemMeta(stickm);
                p.getInventory().addItem(stick);
            } else {
                p.getInventory().addItem(new ItemStack(material));
            }
        }

    }

    public static void getWorldItems(Player p, String kit) {
        List<String> sl = Config.getItemsOfKit(kit);
        for(String s : sl) {
            Material material = Material.getMaterial(s);

            //ToDo: Special Items hier einfügen
            if(material.equals(Material.STICK)) {
                ItemStack stick = new ItemStack(Material.STICK);
                ItemMeta stickm = stick.getItemMeta();
                stickm.setDisplayName("§5Knockback§r-§aStick");
                stickm.addEnchant(Enchantment.KNOCKBACK, 10, true);
                stick.setItemMeta(stickm);
                p.getInventory().addItem(stick);
            } else {
                p.getInventory().addItem(new ItemStack(material));
            }
        }

    }



}
