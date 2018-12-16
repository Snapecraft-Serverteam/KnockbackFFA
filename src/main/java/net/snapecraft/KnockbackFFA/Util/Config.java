package net.snapecraft.KnockbackFFA.Util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Config {

    public static File ConfigFile = new File("plugins/KnockbackFFA", "config.yml");
    public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);




    public static void save() throws IOException {
        Config.save(ConfigFile);

    }
    public static void reload() {
        Config = YamlConfiguration.loadConfiguration(ConfigFile);

    }

    public static void setDefaults() {

        List<String> starter = new ArrayList<>();
        starter.add("STICK");
        starter.add("APPLE");

        Config.addDefault("kits.starterItems", starter);
        Config.addDefault("settings.game.GetHunger", false);
        Config.options().copyDefaults(true);
    }

    public static Boolean getSetting(String name) {
        return Config.getBoolean("settings.game.GetHunger");
    }

    public static HashMap<String, List<String>> getKits() {

        HashMap<String, List<String>> kits = new HashMap<>();

            for(String kit : Config.getConfigurationSection("kits").getKeys(false)) {
                kits.put(kit, (List<String>) Config.getList("kits." + kit));
            }
            return kits;


    }

    public static List<String> getItemsOfKit(String kitName) {
        return getKits().get(kitName);
    }


    public static void setLobby(Location loc) {
        Config.set("settings.Lobby.WORLD", loc.getWorld().getName());
        Config.set("settings.Lobby.X", loc.getX());
        Config.set("settings.Lobby.Y", loc.getY());
        Config.set("settings.Lobby.Z", loc.getZ());
        Config.set("settings.Lobby.YAW", loc.getYaw());
        Config.set("settings.Lobby.PITCH", loc.getPitch());

        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Location getLobby() {
        return new Location(
                Bukkit.getWorld(Config.getString("settings.Lobby.WORLD")),
                Double.parseDouble(Config.getString("settings.Lobby.X")),
                Double.parseDouble(Config.getString("settings.Lobby.Y")),
                Double.parseDouble(Config.getString("settings.Lobby.Z")),
                Float.parseFloat(Config.getString("settings.Lobby.YAW")),
                Float.parseFloat(Config.getString("settings.Lobby.PITCH"))
        );
    }

    public static void setArena(String name, Location spawnpos) {
        Config.set("worlds." + spawnpos.getWorld().getName() + ".name", name);
        Config.set("worlds." + spawnpos.getWorld().getName() + ".X", spawnpos.getX());
        Config.set("worlds." + spawnpos.getWorld().getName() + ".Y", spawnpos.getY());
        Config.set("worlds." + spawnpos.getWorld().getName() + ".Z", spawnpos.getZ());
        Config.set("worlds." + spawnpos.getWorld().getName() + ".YAW", spawnpos.getYaw());
        Config.set("worlds." + spawnpos.getWorld().getName() + ".PITCH", spawnpos.getPitch());

        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Location getArenaSpawn(String name) {

        return new Location(
                Bukkit.getWorld(getWorldNameFromArenaName(name)),
                Double.parseDouble(Config.getString("worlds." + getWorldNameFromArenaName(name) + ".X")),
                Double.parseDouble(Config.getString("worlds." + getWorldNameFromArenaName(name) + ".Y")),
                Double.parseDouble(Config.getString("worlds." + getWorldNameFromArenaName(name) + ".Z")),
                Float.parseFloat(Config.getString("worlds." + getWorldNameFromArenaName(name) + ".YAW")),
                Float.parseFloat(Config.getString("worlds." + getWorldNameFromArenaName(name) + ".PITCH"))
        );

    }

    public static void setKills(String p, Integer amount) {

        Config.set("stats." + p + ".kills", amount);


        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getKills(String p) {
        if(Config.getString("stats." + p + ".kills") != null ) {
            return Integer.parseInt(Config.getString("stats." + p + ".kills"));
        } else {
            return null;
        }
    }

    public static void addKill(String p) {
        if(getKills(p) == null) {
            setKills(p, 1);
        } else {
            setKills(p, getKills(p) + 1);
        }

        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setDeaths(String p, Integer amount) {

        Config.set("stats." + p + ".deaths", amount);


        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getDeaths(String p) {
        if(Config.getString("stats." + p + ".deaths") != null ) {
            return Integer.parseInt(Config.getString("stats." + p + ".deaths"));
        } else {
            return null;
        }

    }

    public static void addDeath(String p) {
        if(getDeaths(p) == null) {
            setDeaths(p, 1);
        } else {
            setDeaths(p, getDeaths(p) + 1);
        }

        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Float getKD(String p) {

        try{

            return (float) getKills(p) / getDeaths(p);

        } catch (Exception e) {
            return null;
        }
    }

    public static void reloadConfig() {
        reload();
    }


    public static void setDeathHeightForWorld(String name, Integer value) {
        Config.set("worlds." + getWorldNameFromArenaName(name) +".DeathHeight", value);

        try{
            save();
            reload();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer getDeathHeightForWorld(String world) {
        return Integer.parseInt(Config.getString("worlds." + world + ".DeathHeight"));
    }



    public static List getArenas() {
        List<String> arenas = new ArrayList<>();
        for(String key : Config.getConfigurationSection("worlds").getKeys(false)) {

            arenas.add(Config.getString("worlds." + key + ".name"));
        }

        return arenas;
    }

    public static List getWorlds() {
        List<String> worlds = new ArrayList<>();
        for(String key : Config.getConfigurationSection("worlds").getKeys(false)) {

            worlds.add(key);
        }

        return worlds;
    }

    public static String getWorldNameFromArenaName(String arena) {
        String world = "";
        for(String key : Config.getConfigurationSection("worlds").getKeys(false)) {

            if(Config.getString("worlds." + key + ".name").equalsIgnoreCase(arena)) {
                world = key;
            }
        }
        return world;
    }
    public static String getArenaNameFromWorldName(String world) {
        String arena = "";

        arena = Config.getString("worlds." + world + ".name");


        return arena;
    }

    /*
    public static String getPlayerOfRank(Integer rank) {

        String currentWinner = "";
        String secondWinner = "";
        for(String player : Config.getConfigurationSection("stats").getKeys(false)) {
            if(currentWinner.equalsIgnoreCase("")) {
                currentWinner = player;
            } else {
                if(Config.getInt("stats." + player + ".kills") > Config.getInt("stats." + currentWinner + ".kills")) {
                    secondWinner = currentWinner;
                    currentWinner = player;
                }
            }
        }

        if(rank == 1) {
            return currentWinner;
        } else {
            return secondWinner;
        }
    }*/

}
