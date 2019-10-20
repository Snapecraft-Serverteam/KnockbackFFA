package net.snapecraft.KnockbackFFA.kits;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class KitGuiCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(commandSender instanceof Player) {
            KitGui.openMainScreen((Player)commandSender);
        } else {
            commandSender.sendMessage("This command can only be executed by a player.");
        }
        return false;
    }
}
