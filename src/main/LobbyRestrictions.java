package main;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LobbyRestrictions implements Listener {

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
            if(e.getEntity() instanceof Player) {
                Player p = (Player) e.getEntity();
               // if(Config.getWorlds().contains(p.getWorld().getName())) {
                    if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setCancelled(true);
                    }
                //} else {
                 //   e.setCancelled(true);
               } else {
                e.setCancelled(true);
            }


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {

        boolean rain = event.toWeatherState();
        if (rain)
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {

        boolean storm = event.toThunderState();
        if (storm)
            event.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if (!e.getPlayer().hasPermission("CustomLobby.pickupItems")) {
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (!Buildmode.BuildModePlayers.contains(e.getPlayer())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onDestroy(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        /*
        if(p.getItemInHand().getType() == Material.BARRIER) {
            p.sendMessage("Barrierklick");
            //Do Stuff
            e.setCancelled(true);
            if(e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                Sign s = (Sign) e.getClickedBlock().getState();
                p.sendMessage("Material ist schild");
                if(s.getLine(0).equalsIgnoreCase("[KBFFA]")) {

                    p.sendMessage("1 schild erkannt");
                    if(s.getLine(1).equalsIgnoreCase("1")) {
                        s.setLine(1, Config.getPlayerOfRank(1));
                        p.sendMessage("Schild erkannt!");
                    } else if(s.getLine(1).equalsIgnoreCase("2")) {
                        s.setLine(1, Config.getPlayerOfRank(2));
                    }
                }
            }
        } else {*/
            if(Buildmode.BuildModePlayers.contains(p)) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }

        }
    //}


}
