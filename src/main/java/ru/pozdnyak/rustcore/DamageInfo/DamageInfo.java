package ru.pozdnyak.rustcore.DamageInfo;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

public class DamageInfo implements Listener {

    private MySQL mySQL;
    private final Main plugin;
    public DamageInfo(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerDamaged(EntityDamageByEntityEvent e){
        if(!e.getEntityType().equals(EntityType.PLAYER))return;
        if(!e.getDamager().getType().equals(EntityType.PLAYER))return;
        Player damager = (Player) e.getDamager();
        Player player = (Player) e.getEntity();
        String out = ChatColor.RED+"-"+((int)e.getDamage()*4)+ChatColor.WHITE+"("+(int)player.getHealth()*4+")";
        damager.sendTitle("", out, 1,20,20);
    }

}
