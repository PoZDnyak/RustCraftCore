package ru.pozdnyak.rustcore.SpawnResources;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

public class SpawnResources implements Listener {
    private final Main plugin;
    private MySQL mySQL;
    public SpawnResources(Main plugin) {
        this.plugin = plugin;
        try {
            mySQL = new MySQL(plugin.getConfig().getString("host"),plugin.getConfig().getString("database"),plugin.getConfig().getString("user"),plugin.getConfig().getString("password"));
        }
        catch(Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    /*
    @EventHandler
    public void BlockBreak(BlockPlaceEvent e){
        Player p = e.getPlayer();
        e.setCancelled(true);
        respawnResources(p);
    }

     */
    @EventHandler
    public void br(BlockBreakEvent e){
        Player p = e.getPlayer();
        p.setCooldown(Material.DIAMOND_BLOCK,100);
    }

    public void respawnResources(Player p){

        BukkitTask run = new BukkitRunnable() {
            Location loc = null;
            int val = 0;
            @Override
            public void run() {
                if(val>=2000)cancel();
                if (loc == null) {
                    loc = new Location(Bukkit.getWorld("world"), (Math.random() * 4000) - 2000, 3, (Math.random() * 4000) - 2000);
                }
                if (loc.getY() >= 150) {
                    System.out.println("OVER 150");
                    loc = null;
                    return;
                }
                if (Bukkit.getWorld("world").getBiome((int) loc.getX(), (int) loc.getZ()).equals(Biome.OCEAN)) {
                    System.out.println("ocean");
                    loc.setY(loc.getY() + 140);
                    return;
                }
                if (Bukkit.getWorld("world").getBiome((int) loc.getX(), (int) loc.getZ()).equals(Biome.FROZEN_OCEAN)) {
                    System.out.println("ocean");
                    loc.setY(loc.getY() + 140);
                    return;
                }
                if (Bukkit.getWorld("world").getBiome((int) loc.getX(), (int) loc.getZ()).equals(Biome.FROZEN_RIVER)) {
                    System.out.println("ocean");
                    loc.setY(loc.getY() + 140);
                    return;
                }
                if (Bukkit.getWorld("world").getBiome((int) loc.getX(), (int) loc.getZ()).equals(Biome.RIVER)) {
                    System.out.println("RIVER");
                    loc.setY(loc.getY() + 140);
                    return;
                }
                System.out.println(loc.getX() + " " + loc.getY() + " " + loc.getZ() + "after");
                loc.add(0,1,0);
                Location check = loc;

                if(!check.getBlock().getType().isSolid())return;
                if(!check.add(0,0,1).getBlock().getType().isSolid())return;
                if(!check.add(0,0,-2).getBlock().getType().isSolid())return;
                if(!check.add(1,0,1).getBlock().getType().isSolid())return;
                if(!check.add(-2,0,0).getBlock().getType().isSolid())return;
                if(!check.add(1,0,0).getBlock().getType().isSolid())return;
                System.out.println("first");

                check = loc;
                if(!check.add(0,1,0).getBlock().getType().equals(Material.AIR))return;
                if(!check.add(0,0,1).getBlock().getType().equals(Material.AIR))return;
                if(!check.add(0,0,-2).getBlock().getType().equals(Material.AIR))return;
                if(!check.add(1,0,1).getBlock().getType().equals(Material.AIR))return;
                if(!check.add(-2,0,0).getBlock().getType().equals(Material.AIR))return;

                check = loc;
                check.getBlock().setType(Material.LAPIS_BLOCK);
                check.add(0,0,1).getBlock().setType(Material.LAPIS_BLOCK);
                check.add(0,0,-2).getBlock().setType(Material.LAPIS_BLOCK);
                check.add(1,0,1).getBlock().setType(Material.LAPIS_BLOCK);
                check.add(-2,0,0).getBlock().setType(Material.LAPIS_BLOCK);
                check.add(1,0,0).getBlock().setType(Material.LAPIS_BLOCK);
                p.teleport(check);
                int x = (int) loc.getX();
                int y = (int) loc.getY();
                int z = (int) loc.getZ();
                p.sendMessage("X:"+x+" Y:"+y+" Z:"+z);
                mySQL.addSpawnResources(x,y,z);
                //mySQL.addSpawnResources(x,y,z);
                val++;


                System.out.println("TP");
                loc = null;
                return;

            }
        }.runTaskTimer(plugin,1,1);

    }
}
