package ru.pozdnyak.rustcore.ResourceSpawner.inventory;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {
	private static InventoryManager instance;
	
	public static InventoryManager instance() {
		if (instance == null) {
			instance = new InventoryManager();
		}
		
		return instance;
	}
	
	public boolean addItemPlayer(Player player, ItemStack item) {
		if (player.getInventory().firstEmpty() >= 0) {
			player.getInventory().addItem(item);
			return true;
		} else {
			for (ItemStack inventoryItem : player.getInventory().getContents()) {
				if (inventoryItem == null) continue;
				
				if (inventoryItem.getType().equals(item.getType())) {
					if (inventoryItem.getAmount() >= 64) continue;
					
					int newAmount = inventoryItem.getAmount() + item.getAmount();
					
					if (newAmount > 64) {
						newAmount = 64;
					}
					
					if (newAmount <= 64) {
						inventoryItem.setAmount(newAmount);
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
