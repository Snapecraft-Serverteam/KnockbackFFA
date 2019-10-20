package net.snapecraft.KnockbackFFA.kits;

import net.snapecraft.KnockbackFFA.util.Config;
import net.snapecraft.KnockbackFFA.util.Database;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitGui {


    /*
    The main GUI where you can select Kits and click on the banners for pagination.
     */
    public static void openMainScreen(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§dKits §3(Seite " + getPage(p) + "/" + getTotalPages(p) + ")");

        for(int i = 0; i <= 26; i++){
            ItemStack pane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) DyeColor.LIGHT_BLUE.getData());
            ItemMeta panemeta = pane.getItemMeta();
            panemeta.setDisplayName(" ");
            pane.setItemMeta(panemeta);
            inv.setItem(i, pane);
        }


        inv.setItem(21, getBanner("arrow_left"));
        inv.setItem(22, getBalanceDisplay(p));
        inv.setItem(23, getBanner("arrow_right"));

        int itemIndex = 9;

        for(ItemStack kit : getItemSet(p, getPage(p))) {
            inv.setItem(itemIndex, kit);
            itemIndex++;
        }

        p.openInventory(inv);
    }


    /*
    This inventory is opened when you click on a kit and don't own it yet. You get a menu where you can
    see your current balance, the kit you're gonna buy as well as some abort/confirm buttons.
     */
    public static void openBuyScreen(Player p, String kit) {
        Inventory inv = Bukkit.createInventory(null, 9, "§2Kit kaufen");

        String displayName = Config.getDisplayNameOfKit(kit);
        int price = Config.getPriceOfKit(kit);
        Material icon = Config.getIconOfKit(kit);
        List<Material> items = Config.getItemsOfKit(kit);
        List<String> lore = new ArrayList<>();
        lore.add("§7Dieses Kit enthält:");
        for(Material mat : items) {
            lore.add("- §b" + mat.name());
        }

        lore.add(" ");
        if(Database.hasKit(p.getUniqueId(), kit)) {
            lore.add("§dDu besitzt dieses Kit.");
        } else {
            lore.add("§7Preis: §e" + price);
            lore.add("§2§oKlicke, um dieses Kit zu kaufen");
        }

        ItemStack kitStack = new ItemStack(icon, 1);
        ItemMeta kitStackMeta = kitStack.getItemMeta();
        kitStackMeta.setDisplayName(displayName);
        kitStackMeta.setLore(lore);
        kitStack.setItemMeta(kitStackMeta);

        inv.setItem(0, kitStack);

        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName("§eDein Kontostand");
        List<String> chestLore = new ArrayList<>();
        chestLore.add("§7Du hast derzeit:");
        chestLore.add("§e"  +Database.getCoins(p.getUniqueId()) + " Coins");
        chestMeta.setLore(chestLore);
        chest.setItemMeta(chestMeta);

        inv.setItem(1, chest);

        ItemStack cancelStack = new ItemStack(Material.WOOL, 1, (short) DyeColor.RED.getData());
        ItemMeta cancelStackMeta = cancelStack.getItemMeta();
        cancelStackMeta.setDisplayName("§cAbbrechen");
        cancelStack.setItemMeta(cancelStackMeta);

        inv.setItem(7, cancelStack);

        ItemStack buyStack = new ItemStack(Material.WOOL, 1, (short) DyeColor.GREEN.getData());
        ItemMeta buyStackMeta = cancelStack.getItemMeta();
        buyStackMeta.setDisplayName("§aKaufen");
        buyStack.setItemMeta(buyStackMeta);

        inv.setItem(8, buyStack);


        p.openInventory(inv);
    }


    // Returns a banner item with arrows on it for menu navigation, pass "arrow_right" or "arrow_left" as argument.
    public static ItemStack getBanner(String option) {
        if (option.equalsIgnoreCase("arrow_right")) {
            ItemStack i = new ItemStack(Material.BANNER, 1);
            BannerMeta bannerMeta = (BannerMeta)i.getItemMeta();

            bannerMeta.setBaseColor(DyeColor.BLACK);

            List<Pattern> patterns = new ArrayList<Pattern>();

            patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
            patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_VERTICAL));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.SQUARE_BOTTOM_LEFT));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.SQUARE_TOP_LEFT));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_BOTTOM));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_TOP));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.BORDER));
            bannerMeta.setPatterns(patterns);
            bannerMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS); //somehow does this hide the banner pattern lore
            bannerMeta.setDisplayName("§bNächste Seite");
            i.setItemMeta(bannerMeta);

            return i; //Return the ItemStack 'i'
        } else if (option.equalsIgnoreCase("arrow_left")) {
            ItemStack i = new ItemStack(Material.BANNER, 1);
            BannerMeta bannerMeta = (BannerMeta)i.getItemMeta();

            bannerMeta.setBaseColor(DyeColor.BLACK);

            List<Pattern> patterns = new ArrayList<Pattern>();

            patterns.add(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS_MIDDLE));
            patterns.add(new Pattern(DyeColor.WHITE, PatternType.HALF_VERTICAL_MIRROR));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.SQUARE_BOTTOM_RIGHT));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.SQUARE_TOP_RIGHT));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_BOTTOM));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.TRIANGLES_TOP));
            patterns.add(new Pattern(DyeColor.BLACK, PatternType.BORDER));
            bannerMeta.setPatterns(patterns);
            bannerMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS); //somehow does this hide the banner pattern lore
            bannerMeta.setDisplayName("§bVorherige Seite");
            i.setItemMeta(bannerMeta);

            return i; //Return the ItemStack 'i'
        }

        return null;
    }

    // Returns Chest Itemstack with your current balance as lore
    public static ItemStack getBalanceDisplay(Player p) {
        ItemStack chest = new ItemStack(Material.CHEST, 1);
        ItemMeta chestMeta = chest.getItemMeta();
        chestMeta.setDisplayName("§eDein Kontostand");
        List<String> chestLore = new ArrayList<>();
        chestLore.add("§7Du hast derzeit:");
        chestLore.add("§e"  +Database.getCoins(p.getUniqueId()) + " Coins");
        chestMeta.setLore(chestLore);
        chest.setItemMeta(chestMeta);

        return chest;
    }

    /****************
    Pagination Logic
    *****************/

    /*
     Hashmap with all players and the page they're currently looking at. When a player joins, they will be added to
     this list with the page 0. See PlayerJoinEvent.java
     */
    public static HashMap<Player, Integer> currentPage = new HashMap<>();

    /*
    This method auto-increments the page, and performs some checks before doing it.
     */
    public static void nextPage(Player p) {
        if(currentPage.containsKey(p)) {
            int page = currentPage.get(p);
            if(page == (getTotalPages(p))) {
                p.sendMessage("§cDu bist bereits auf der letzten Seite.");
                return;
            }
            currentPage.remove(p);
            currentPage.put(p, page + 1);
        } else {
            if(getTotalPages(p) > 0) {
                currentPage.put(p, 1);
            } else {
                currentPage.put(p, 0); //fix
            }
        }
    }
    /*
    This method auto-decrements the page, and performs some checks before doing it.
    */
    public static void prevPage(Player p) {
        if(currentPage.containsKey(p)) {
            int page = currentPage.get(p);
            if(page != 0) {
                currentPage.remove(p);
                currentPage.put(p, page - 1);
            } else {
                p.sendMessage("§cDu bist bereits auf der ersten Seite.");
            }

        } else {
            p.sendMessage("§cDu bist bereits auf der ersten Seite.");
        }
    }


    // Returns the page a player is currently looking at.
    public static int getPage(Player p) {
        return currentPage.getOrDefault(p, 0);
    }


    /*
    Returns the set of items that is being displayed for one page
     */
    public static List<ItemStack> getItemSet(Player p, Integer page) {
        if(getKitItems(p).size() <= 9) {
            return getKitItems(p);
        } else {
            int pageLimiter;
            // wenn      15       kleiner   8
            if(getKitItems(p).size() < (8 * (page + 1))) {
                pageLimiter=getKitItems(p).size();
            } else {
                pageLimiter = 8*page+9;
            }
            return getKitItems(p).subList(8*page, pageLimiter);
        }
    }


    //Calculates the total page count for all loaded kits.
    public static int getTotalPages(Player p) {
        return (int)Math.floor(getKitItems(p).size() / 9.0F);
    }

    /*
    Get a List of ALL available Kit-Items (items that are presented in the Kit-GUI (with icon, lore, price, displayname)
     */
    public static List<ItemStack> getKitItems(Player p) {
        List<ItemStack> kitItems = new ArrayList<>();
        for(String kit : Config.getKitNames()) {
            String displayName = Config.getDisplayNameOfKit(kit);
            int price = Config.getPriceOfKit(kit);
            Material icon = Config.getIconOfKit(kit);
            List<Material> items = Config.getItemsOfKit(kit);
            List<String> lore = new ArrayList<>();
            lore.add("§7Dieses Kit enthält:");
            for(Material mat : items) {
                lore.add("- §b" + mat.name());
            }
            lore.add(" ");

            if(Database.hasKit(p.getUniqueId(), kit)) {
                lore.add("§dDu besitzt dieses Kit.");
            } else {
                lore.add("§7Preis: §e" + price + " Coins");
                lore.add("§2§oKlicke, um dieses Kit zu kaufen");
            }

            ItemStack kitStack = new ItemStack(icon, 1);
            ItemMeta kitStackMeta = kitStack.getItemMeta();
            kitStackMeta.setDisplayName(displayName);
            kitStackMeta.setLore(lore);
            kitStack.setItemMeta(kitStackMeta);

            kitItems.add(kitStack);
        }
        return kitItems;
    }
}
