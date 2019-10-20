package net.snapecraft.KnockbackFFA;

import net.snapecraft.KnockbackFFA.command.Buildmode;
import net.snapecraft.KnockbackFFA.command.commands;
import net.snapecraft.KnockbackFFA.events.*;
import net.snapecraft.KnockbackFFA.kits.KitGuiCommand;
import net.snapecraft.KnockbackFFA.kits.KitGuiListener;
import net.snapecraft.KnockbackFFA.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    // PlayerName, WorldName
    public static HashMap<String, String> gamelist = new HashMap<>();
    public static String prefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "KBFFA" + ChatColor.GRAY + "] " + ChatColor.LIGHT_PURPLE + ">> " + ChatColor.RESET;
    public static String noperm = prefix + "§cDu hast nicht die Berechtigung, um diesen Befehl auszuführen!";

    @Override
    public void onEnable() {
        ConsoleCommandSender console = this.getServer().getConsoleSender();

        console.sendMessage(" \n\n___  __    ________  ________ ________ ________     \n" +
                "|\\  \\|\\  \\ |\\   __  \\|\\  _____\\\\  _____\\\\   __  \\    \n" +
                "\\ \\  \\/  /|\\ \\  \\|\\ /\\ \\  \\__/\\ \\  \\__/\\ \\  \\|\\  \\   \n" +
                " \\ \\   ___  \\ \\   __  \\ \\   __\\\\ \\   __\\\\ \\   __  \\  \n" +
                "  \\ \\  \\\\ \\  \\ \\  \\|\\  \\ \\  \\_| \\ \\  \\_| \\ \\  \\ \\  \\ \n" +
                "   \\ \\__\\\\ \\__\\ \\_______\\ \\__\\   \\ \\__\\   \\ \\__\\ \\__\\\n" +
                "    \\|__| \\|__|\\|_______|\\|__|    \\|__|    \\|__|\\|__|\n" +
                "                                                     \n" +
                "                                                     \n" +
                "                                                     ");

        init();
    }

    public void init() {

        Config.setDefaults();


        System.out.println("kits: " + Config.getKits().toString());
        System.out.println("items: " + Config.getDisplayNameOfKit("starterItems"));


        Bukkit.getPluginManager().registerEvents(new PlayerKnockoffEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyRestrictions(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);

        Bukkit.getPluginManager().registerEvents(new KitGuiListener(), this);

        getCommand("kbffa").setExecutor(new commands());
        getCommand("kit").setExecutor(new KitGuiCommand());
        getCommand("kits").setExecutor(new KitGuiCommand());
        getCommand("build").setExecutor(new Buildmode());
    }

    public static void addToGamelist(String p, String world) {
        if(gamelist.containsKey(p)) {
            gamelist.remove(p);
            gamelist.put(p, world);
        } else {
            gamelist.put(p, world);
        }
    }
    public static void removeFromGamelist(String p) {
        try{
            gamelist.remove(p);
        } catch(Exception e) {

        }
    }
}
