package net.snapecraft.KnockbackFFA.Command;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.snapecraft.KnockbackFFA.Util.Config;
import net.snapecraft.KnockbackFFA.Util.GetItems;
import net.snapecraft.KnockbackFFA.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player p =(Player) commandSender;

        if(args.length == 2) {
            if (args[0].equalsIgnoreCase("setarena")) {
                Config.setArena(args[1], p.getLocation());
                p.sendMessage(Main.prefix + "§aArena gesetzt!");
                TextComponent message1 = new TextComponent( "[" + ChatColor.YELLOW +  "Weiter§r] " );
                message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/kbffa setup 3" ));
                p.spigot().sendMessage(message1);
            }
            if(args[0].equalsIgnoreCase("join")) {
                if(Config.getArenaSpawn(args[1]) != null)  {
                    if(!Main.gamelist.containsKey(p.getName())) {
                        Main.addToGamelist(p.getName(), args[1]);
                        Bukkit.broadcastMessage("Gamelist: " + Main.gamelist.toString());
                        p.teleport(Config.getArenaSpawn(args[1]));
                        GetItems.getWorldItems(p);
                    } else {
                        p.sendMessage(Main.prefix + "§cDu bist bereits in einer Arena!");
                    }

                } else {
                    p.sendMessage(Main.prefix + "§cWelt nicht gefunden!");
                }
            } else if(args[0].equalsIgnoreCase("kit")) {
                if(Config.getKits().containsKey(args[1])){
                    p.getInventory().clear();
                    GetItems.getWorldItems(p, args[1]);
                } else {
                    p.sendMessage(Main.prefix + "§cDieses Kit gibt es nicht!");
                }
            }
             else if(args[0].equalsIgnoreCase("setdeathheight")) {
                if(Config.getArenaSpawn(args[1]) != null) {
                    Config.setDeathHeightForWorld(args[1], p.getLocation().getBlockY());
                    TextComponent message1 = new TextComponent( "[" + ChatColor.YELLOW +  "Weiter§r] " );
                    message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/kbffa setup 4" ));
                    p.spigot().sendMessage(message1);
                } else {
                    p.sendMessage(Main.prefix + "§cWelt ungültig!");
                }

            }
            else if(args[0].equalsIgnoreCase("setup")) {
                if(args[1].equalsIgnoreCase("2")) {
                    p.sendMessage(ChatColor.YELLOW + " +++ SETUP ASSISTENT +++ ");
                    p.sendMessage("-----------------------");
                    p.sendMessage("Schritt 2: Arena adden");
                    p.sendMessage("-----------------------");
                    p.sendMessage("Arenen sind Welten, in denen sich Spieler bekämpfen");
                    p.sendMessage(ChatColor.RED + ">> " + ChatColor.GREEN + "Erstelle eine Welt mit Multiverse, gehe zum Spawnpunkt, drücke den Knopf, gebe einen Namen ein und sende den Command");
                    TextComponent message1 = new TextComponent( "[§aSetzen§r] " );
                    message1.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, "/kbffa setarena " ));
                    p.sendMessage("/kbffa setup 2, um diesen Schritt zu wiederholen");
                    p.spigot().sendMessage(message1);
                } else if(args[1].equalsIgnoreCase("3")) {
                    p.sendMessage(ChatColor.YELLOW + " +++ SETUP ASSISTENT +++ ");
                    p.sendMessage("-----------------------");
                    p.sendMessage("Schritt 3: Todeshöhe   ");
                    p.sendMessage("-----------------------");
                    p.sendMessage("Du musst eine Höhe einstellen, ab der die Spieler als tot gelten sollen.");
                    p.sendMessage(ChatColor.RED + ">> " + ChatColor.GREEN + "Fliege zu der X-Koodinate deiner Wahl und drücke den Knopf");
                    TextComponent message1 = new TextComponent( "[§aSetzen§r] " );
                    message1.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, "/kbffa setDeathHeight " ));
                    p.spigot().sendMessage(message1);

                } else if(args[1].equalsIgnoreCase("4")) {
                    p.sendMessage(ChatColor.YELLOW + " +++ SETUP ASSISTENT +++ ");
                    p.sendMessage("-----------------------");
                    p.sendMessage("Schritt 4: Arena joinen");
                    p.sendMessage("-----------------------");
                    p.sendMessage("Wenn du einer Arena beitrittst, stürzst du dich direkt ins Geschehen");
                    p.sendMessage(ChatColor.RED + ">> " + ChatColor.GREEN + "Klicke den Knopf und schreibe den Namen der eben erstellten Arena, um ihr beizutreten!");
                    TextComponent message1 = new TextComponent( "[§aJoin§r] " );
                    message1.setClickEvent( new ClickEvent( ClickEvent.Action.SUGGEST_COMMAND, "/kbffa join " ));
                    p.spigot().sendMessage(message1);

                }

            }
        } else if(args.length == 1) {
            if(args[0].equalsIgnoreCase("setlobby")) {
                Config.setLobby(p.getLocation());
                p.sendMessage(Main.prefix + "§aLobby gesetzt.");
                TextComponent message1 = new TextComponent("[" + ChatColor.YELLOW + "Weiter§r] ");
                TextComponent message2 = new TextComponent("[" + ChatColor.RED + "Abbrechen§r] ");
                message1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/kbffa setup 2"));
                message2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/kbffa cancelsetup"));
                message1.addExtra(message2);
                p.spigot().sendMessage(message1);

            } else if(args[0].equalsIgnoreCase("kit")) {
                String kitsSeperated = String.join(",", Config.getKits().keySet());

                p.sendMessage(Main.prefix + "Es gibt zurzeit folgende Kits: §6" + kitsSeperated);
            }  else if (args[0].equalsIgnoreCase("setup")) {

                p.sendMessage(ChatColor.BLUE +   "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                p.sendMessage(ChatColor.YELLOW + "+++ SETUP ASSISTENT KBFFA +++");
                p.sendMessage(ChatColor.BLUE +   "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                p.sendMessage("-----------------------");
                p.sendMessage("Schritt 1: Lobby setzen");
                p.sendMessage("-----------------------");
                p.sendMessage("In die Lobby werden alle Spieler teleportiert, die KnockbackFFA beitreten wollen.");
                p.sendMessage(ChatColor.RED + ">> " + ChatColor.GREEN + "Gehe zu dem gewünschten Spawnpunkt und drücke diesen Knopf:");
                TextComponent message1 = new TextComponent( "[§aSetzen§r] " );
                message1.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/kbffa setlobby" ));
                p.spigot().sendMessage(message1);

            } else if(args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("lobby")) {
                p.sendMessage("Du hast das Spiel verlassen!");
                p.teleport(Config.getLobby());
                Main.removeFromGamelist(p.getName());
                p.getInventory().clear();
            } else if(args[0].equalsIgnoreCase("list")) {

                String arenaSeperated = String.join(",", Config.getArenas());
                p.sendMessage(Main.prefix + "Foldende Arenen sind verfügbar: §6" + arenaSeperated);
            } else if(args[0].equalsIgnoreCase("cancelsetup")) {
                for(int i = 0; i < 100; i++) {
                    p.sendMessage("");
                }
                p.sendMessage(Main.prefix + "§aSetup beendet.");
            } else if(args[0].equalsIgnoreCase("reload")) {
                Config.reload();
                p.sendMessage(Main.prefix + "§aConfig wurde neugeladen!");
            } else if(args[0].equalsIgnoreCase("ver")) {
                p.sendMessage(Main.prefix + "Version v." + Main.getPlugin(Main.class).getDescription().getVersion());
            }
        }




        return true;
    }
}
