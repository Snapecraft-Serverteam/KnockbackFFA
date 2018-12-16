package net.snapecraft.KnockbackFFA.Events;

import net.snapecraft.KnockbackFFA.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Main.removeFromGamelist(e.getPlayer().getName());
    }
}
