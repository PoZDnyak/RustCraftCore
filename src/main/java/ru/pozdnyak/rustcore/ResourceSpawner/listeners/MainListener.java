package ru.pozdnyak.rustcore.ResourceSpawner.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.ResourceSpawner.data.Lang;
import ru.pozdnyak.rustcore.ResourceSpawner.inventory.InventoryManager;
import ru.pozdnyak.rustcore.ResourceSpawner.stone.Stone;
import ru.pozdnyak.rustcore.ResourceSpawner.stone.ToolStone;
import ru.pozdnyak.rustcore.ResourceSpawner.trees.ToolWood;
import ru.pozdnyak.rustcore.ResourceSpawner.trees.Tree;
import ru.pozdnyak.rustcore.ResourceSpawner.trees.TreeFeller;
import ru.pozdnyak.rustcore.ResourceSpawner.trees.TreeManager;

public class MainListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		
		if (player.getGameMode() == GameMode.SURVIVAL) {
			if (item != null && item.getType() == Material.AIR) {
				event.setCancelled(true);
			} 
		}
	}
	
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (event.getHand() == EquipmentSlot.HAND) {
			ItemStack item = player.getInventory().getItemInMainHand();
			
			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				Block block = event.getClickedBlock();
				
				if (block.getType().toString().contains("LOG")){
					//TODO, Check if block is no structure
					
					if (ToolWood.instance().hasNeedTool(item)) {
						TreeManager.instance().createIfNeed(block);
						
						if (Tree.containsByBlock(block)) {
							event.setCancelled(true);
							
							if (!player.hasCooldown(item.getType())) {
								int resourceAmount = ToolWood.instance().getRandomToolResource(item.getType());
								String materialName = Main.plugin.getConfig().getString("Settings.Trees.Resource");
								ItemStack itemToAdd = new ItemStack(Material.getMaterial(materialName), resourceAmount);
								
								if (InventoryManager.instance().addItemPlayer(player, itemToAdd)) {
									Tree tree = Tree.getByBlock(block);
									tree.minusDurability();
									tree.updateLastModify();
									
									if (tree.getDurability() <= 0) {
										TreeFeller.instance().removeTree(block);
										Tree.classes.remove(tree);
										player.sendMessage(Lang.instance().BreakTree);
									} else {
										player.sendMessage(Lang.instance().DurabilityTree.replaceAll("%v", "" + tree.getDurability()));
									}
									
									player.setCooldown(item.getType(), Main.plugin.getConfig().getInt("Settings.Trees.Cooldown"));
								} else {
									player.sendMessage(Lang.instance().InventoryFull);
								}
							}
						}
					}
				} else {
					if (Stone.containsByLocation(block.getLocation())) {
						event.setCancelled(true);
						
						if (ToolStone.instance().hasNeedTool(item)) {
							if (!player.hasCooldown(item.getType())) {
								Stone stone = Stone.getByLocation(block.getLocation());
								int resourceAmount = ToolStone.instance().getRandomToolResource(item.getType());
								ItemStack itemToAdd = new ItemStack(Material.getMaterial(stone.getResourceName()), resourceAmount);
								
								if (InventoryManager.instance().addItemPlayer(player, itemToAdd)) {
									stone.minusDurability();
									stone.updateLastModify();
									
									if (stone.getDurability() <= 0) {
										block.setType(Material.AIR);
										player.sendMessage(Lang.instance().BreakStone);
									} else {
										player.sendMessage(Lang.instance().DurabilityStone.replaceAll("%v", "" + stone.getDurability()));
									}
									
									player.setCooldown(item.getType(), Main.plugin.getConfig().getInt("Settings.Stones.Cooldown"));
								} else {
									player.sendMessage(Lang.instance().InventoryFull);
								}
							}
						}
					}
				}
			}
		}
	}
}
