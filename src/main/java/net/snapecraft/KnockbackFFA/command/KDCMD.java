package net.snapecraft.KnockbackFFA.command;

import net.snapecraft.KnockbackFFA.util.Config;
import net.snapecraft.KnockbackFFA.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KDCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;

        p.sendMessage(Main.prefix + "Deine K/D: §6" + Config.getKD(p.getName()));


        return true;
    }
}
