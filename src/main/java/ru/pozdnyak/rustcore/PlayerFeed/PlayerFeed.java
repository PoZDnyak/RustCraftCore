package ru.pozdnyak.rustcore.PlayerFeed;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

public class PlayerFeed implements Listener {
    private final Main plugin;
    private MySQL mySQL;


    public PlayerFeed(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void playerFeed(PlayerItemConsumeEvent e){
        Player p = e.getPlayer();
        ItemStack item = e.getItem();
        ItemStack remove = new ItemStack(item.getType(),1);
        e.setCancelled(true);
        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount()-1);
    }

}
