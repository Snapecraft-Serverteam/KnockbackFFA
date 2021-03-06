package net.snapecraft.KnockbackFFA.events;

import net.snapecraft.KnockbackFFA.util.Config;
import net.snapecraft.KnockbackFFA.util.GetItems;
import net.snapecraft.KnockbackFFA.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        e.setDeathMessage(null);
        if(Main.gamelist.containsKey(p.getName())) {
            p.sendMessage("§cDu bist gestorben!");
            Bukkit.broadcastMessage("§6" + p.getName() + " §rist gestorben!");
            p.teleport(Config.getArenaSpawn(Config.getArenaNameFromWorldName(p.getWorld().getName())));
        } else {
            p.teleport(Config.getLobby());
        }


    }


    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        if(Main.gamelist.containsKey(e.getPlayer().getName())) {
            GetItems.getWorldItems(e.getPlayer());
        }

    }


}
