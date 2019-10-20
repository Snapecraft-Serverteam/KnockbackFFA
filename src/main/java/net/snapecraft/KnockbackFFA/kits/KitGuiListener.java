package net.snapecraft.KnockbackFFA.kits;

import net.snapecraft.KnockbackFFA.util.Config;
import net.snapecraft.KnockbackFFA.util.Database;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.xml.crypto.Data;

public class KitGuiListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player p = (Player)e.getWhoClicked();
        //main kit menu
        if(e.getClickedInventory() != null) {
            if (e.getClickedInventory().getTitle().startsWith("§dKits")) {
                e.setCancelled(true);
                // Player has Kit already

                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bNächste Seite")) {
                    KitGui.nextPage(p);
                    KitGui.openMainScreen(p);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bVorherige Seite")) {
                    KitGui.prevPage(p);
                    KitGui.openMainScreen(p);
                } else if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    //skip
                } else {
                    if(Database.hasKit(p.getUniqueId(), Config.getKitByDisplayName(e.getCurrentItem().getItemMeta().getDisplayName()))) {
                        //TODO equip kit

                    } else { //Player doesn't have kit yet --> open buy dialog
                        KitGui.openBuyScreen(p, Config.getKitByDisplayName(e.getCurrentItem().getItemMeta().getDisplayName()));
                    }
                }




            } else if(e.getClickedInventory().getTitle().equalsIgnoreCase("§2Kit kaufen")) {
                e.setCancelled(true);
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cAbbrechen")) {
                    KitGui.openMainScreen(p);
                } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aKaufen")) {
                    String kit = Config.getKitByDisplayName(e.getClickedInventory().getItem(0).getItemMeta().getDisplayName());
                    int price = Config.getPriceOfKit(kit);

                    if(Database.getCoins(p.getUniqueId()) > price) {
                        Database.spendCoins(p.getUniqueId(), price);
                        Database.addKit(p.getUniqueId(), kit);
                        KitGui.openMainScreen(p);
                        p.sendMessage("§aVielen Dank für deinen Einkauf, du hast das Kit erfolgreich gekauft! ");
                    } else {
                        p.closeInventory();
                        p.sendMessage("§cDu hast nicht genügend §eCoins§c, um dieses Kit zu kaufen!");
                    }
                }
            }
        }

    }
}
