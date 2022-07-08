package ru.pozdnyak.rustcore.DisconnectBodies;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.golde.bukkit.corpsereborn.CorpseAPI.CorpseAPI;
import org.golde.bukkit.corpsereborn.CorpseAPI.events.CorpseClickEvent;
import org.golde.bukkit.corpsereborn.nms.Corpses;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

public class DisconnectBodies  implements Listener {

    private final Main plugin;
    private MySQL mySQL;
    String inv;


    public DisconnectBodies(Main plugin) {
        this.plugin = plugin;
        try {
            mySQL = new MySQL(plugin.getConfig().getString("host"),plugin.getConfig().getString("database"),plugin.getConfig().getString("user"),plugin.getConfig().getString("password"));
        }
        catch(Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }
    @EventHandler
    public void quitRemovePlayerStats(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.isOp())return;
        Corpses.CorpseData corpse = CorpseAPI.spawnCorpse(p,p.getLocation(),p.getInventory().getContents());
        corpse.setTicksLeft(999999999*20);
    }
    int x,y,z;
    Action act = null;

    @EventHandler
    public void test(CorpseClickEvent e){
        Location need = e.getCorpse().getOrigLocation();
        int xx = need.getBlockX();
        int yy = need.getBlockY();
        int zz = need.getBlockZ();
        if(z!=zz && z!=zz-1)e.setCancelled(true);
        if(y!=yy) e.setCancelled(true);
        if(x!=xx)e.setCancelled(true);
        if(act.equals(Action.LEFT_CLICK_BLOCK)){
            e.setCancelled(true);
            CorpseAPI.removeCorpse(e.getCorpse());
            return;
        }else return;

    }
    @EventHandler
    public void test2(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK)|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            act = e.getAction();
            Location loc = e.getClickedBlock().getLocation();
            x = loc.getBlockX();
            y = loc.getBlockY();
            y++;
            z = loc.getBlockZ();
        }else return;
    }

    @EventHandler
    public void remove(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.isOp())return;
        for(Corpses.CorpseData corpse :CorpseAPI.getCorpseInRadius(e.getPlayer().getLocation(),3)){
            if(corpse.getCorpseName().equals(p.getName())){
                Inventory inv = corpse.getLootInventory();
                p.getInventory().clear();

                int count=-1;
                for(ItemStack item : inv.getContents()){
                    count++;
                    if(count<18)continue;
                    if(item==null)continue;
                    p.getInventory().addItem(item);
                }
                CorpseAPI.removeCorpse(corpse);
                return;
            }
        }
        p.setHealth(0);
        BukkitTask runnable = new BukkitRunnable() {
            @Override
            public void run() {
                for(Corpses.CorpseData corpse :CorpseAPI.getCorpseInRadius(e.getPlayer().getLocation(),3)){
                    if(corpse.getCorpseName().equals(p.getName())){
                        CorpseAPI.removeCorpse(corpse);
                        return;
                    }
                }
            }
        }.runTaskLater(plugin,20);
    }


}
