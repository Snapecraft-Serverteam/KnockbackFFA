package net.snapecraft.KnockbackFFA.Events;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.snapecraft.KnockbackFFA.Util.Config;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().getInventory().clear();
        e.getPlayer().setHealth(20.0);
        e.getPlayer().sendMessage("§aWillkommen auf KnockbackFFA!");
        e.getPlayer().teleport(Config.getLobby());

        if(Config.ConfigFile.length() == 0) {
            if(e.getPlayer().hasPermission("KBFFA.receiveWarnings")) {
                e.getPlayer().sendMessage("KBFFA ist noch nicht eingerichtet. Klicke den Knopf, um den Setup-Wizard zu starten:");
                TextComponent message1 = new TextComponent( "[" + ChatColor.YELLOW +  "Setup starten§r] " );
                message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/kbffa setup" ));
                e.getPlayer().spigot().sendMessage(message1);

            } else {
                e.getPlayer().sendMessage("KBFFA ist noch nicht eingerichtet. Bitte warte, bis ein Admin dies tut.");
            }
        } else {
            e.getPlayer().sendMessage("§aNutze /kbffa join <welt>, um einer Welt beizutreten!");

        }
    }

}
