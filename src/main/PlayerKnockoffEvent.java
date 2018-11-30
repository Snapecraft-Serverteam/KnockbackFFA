package main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class PlayerKnockoffEvent implements Listener {
    int ctdwn = 1;
    public static List<Player> knockedPlayers = new ArrayList<>();
    @EventHandler
    public void onKnockoff(EntityDamageByEntityEvent e) {

        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player victim = (Player)e.getEntity();


            //Leben wiederherstellen
            victim.setHealth(20.0);

            //Damage auf der Plattform verbieten
            if(e.getEntity().getLocation().getBlockY() == Config.getArenaSpawn(Config.getArenaNameFromWorldName(e.getEntity().getWorld().getName())).getBlockY()) {
                e.setCancelled(true);
            }

            //Todeshöhe abfragen
            else if(e.getEntity().getLocation().getBlockY() != Config.getDeathHeightForWorld(e.getEntity().getWorld().getName())) {
                Player murderer = (Player)e.getDamager();



                knockedPlayers.add(victim);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        ctdwn++;
                        if(ctdwn >= 5) {
                            cancel();
                            ctdwn = 1;
                            e.setCancelled(true);
                            if(victim.getLocation().getY() <= Config.getDeathHeightForWorld(e.getEntity().getWorld().getName())) {
                                Config.addDeath(victim.getName());
                                Config.addKill(murderer.getName());
                                victim.sendMessage("§cDu wurdest von " + murderer.getName() + " getötet!");
                                murderer.sendMessage("§aDu hast " + victim.getName() + " getötet!");
                                Bukkit.broadcastMessage("§6" + murderer.getName() + " §rhat §6" + victim.getName() + " §rgetötet!");
                                victim.teleport(Config.getArenaSpawn(Config.getArenaNameFromWorldName(victim.getWorld().getName())));
                                knockedPlayers.remove(victim);
                            }

                        }
                    }
                }.runTaskTimer(Main.getPlugin(Main.class), 0, 20);
            } else {
                e.setCancelled(true);
            }

        }



    }


}
