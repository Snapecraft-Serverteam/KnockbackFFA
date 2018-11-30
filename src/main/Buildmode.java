package main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Buildmode implements CommandExecutor {

    public static List<Player> BuildModePlayers = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player)commandSender;
        if(p.hasPermission("KBFFA.BuildMode")) {
            if(BuildModePlayers.contains(p)) {
                BuildModePlayers.remove(p);
                p.sendMessage(Main.prefix + "Du bist jetzt §cNICHT §rmehr im Baumodus.");
            } else {
                BuildModePlayers.add(p);
                p.sendMessage(Main.prefix + "Du bist jetzt §aim Baumodus§r.");
            }
        } else {
            p.sendMessage(Main.noperm);
        }


        return true;
    }
}
