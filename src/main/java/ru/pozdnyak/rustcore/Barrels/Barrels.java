package ru.pozdnyak.rustcore.Barrels;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import ru.pozdnyak.rustcore.Items;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

public class Barrels implements Listener {

    private MySQL mySQL;
    private final Main plugin;
    public Barrels(Main plugin) {
        this.plugin = plugin;
    }
    //Дроп с бочки
    @EventHandler
    public void barrelDrop(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(e.getBlock().getType().equals(Material.LAPIS_BLOCK)|| e.getBlock().getType().equals(Material.REDSTONE_BLOCK)){
            barrelDropItems(e.getBlock(),p);
            Bukkit.getWorld(p.getWorld().getName()).getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
        }else return;
    }

    private void barrelDropItems(Block b, Player p){
        int scrapCount = 1 + (int)(Math.random()*2);
        ItemStack scrap = Items.giveResourceScrap(scrapCount);
        String world = p.getWorld().getName();
        Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),scrap);
        int amount;
        if(generatePercent(15)){
            amount = 1;
            ItemStack metalBlade = Items.giveComponentMetalBlade(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),metalBlade);
        }
        if(generatePercent(15)){
            amount = 1 + (int)(Math.random()*2);
            ItemStack rope = Items.giveComponentRope(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),rope);
        }
        if(generatePercent(8)){
            amount = 3 + (int)(Math.random()*4);
            ItemStack kit = Items.giveComponentSewingKit(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),kit);
        }
        if(generatePercent(1)){
            amount = 2;
            ItemStack shesterenki = Items.giveComponentGears(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),shesterenki);

        }
        if(generatePercent(1)){
            amount = 1 + (int)(Math.random()*4);
            ItemStack metalPipe = Items.giveComponentMetalPipe(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),metalPipe);
        }
        if(generatePercent(1)){
            amount = 1;
            ItemStack metalSpring = Items.giveComponentMetalSpring(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),metalSpring);
        }
        if(generatePercent(1)){
            amount = 2 + (int)(Math.random()*3);
            ItemStack roadSigns = Items.giveComponentRoadSigns(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),roadSigns);
        }
        if(generatePercent(1)){
            amount = 1;
            ItemStack semiAutomaticBody = Items.giveComponentSemiAutomaticBody(amount);
            Bukkit.getWorld(world).dropItem(b.getLocation().add(0,1,0),semiAutomaticBody);
        }
    }

    public static boolean generatePercent(int percent) {
        double a = Math.random()*100;
        return a <= percent;

    }

}
