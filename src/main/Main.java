package main;

import net.md_5.bungee.protocol.packet.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;

public class Main extends JavaPlugin {

    // PlayerName, WorldName
    public static HashMap<String, String> gamelist = new HashMap<>();
    public static String prefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "KBFFA" + ChatColor.GRAY + "] " + ChatColor.LIGHT_PURPLE + ">> " + ChatColor.RESET;
    public static String noperm = prefix + "§cDu hast nicht die Berechtigung, um diesen Befehl auszuführen!";

    @Override
    public void onEnable() {
        ConsoleCommandSender console = this.getServer().getConsoleSender();

        console.sendMessage(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        console.sendMessage(ChatColor.BLUE +                "+-                         -+");
        console.sendMessage(ChatColor.YELLOW +              "+-   KnockbackFFA v.1.0    -+");
        console.sendMessage(ChatColor.LIGHT_PURPLE +        "+-        by Mayus         -+");
        console.sendMessage(ChatColor.BLUE +                "+-                         -+");
        console.sendMessage(ChatColor.BLUE +                "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

        init();
    }

    public void init() {

        Config.setDefaults();


        Bukkit.getPluginManager().registerEvents(new PlayerKnockoffEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
        Bukkit.getPluginManager().registerEvents(new LobbyRestrictions(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
        getCommand("kbffa").setExecutor(new commands());
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
