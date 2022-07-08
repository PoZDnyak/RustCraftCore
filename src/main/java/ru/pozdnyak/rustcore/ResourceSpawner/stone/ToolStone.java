package ru.pozdnyak.rustcore.ResourceSpawner.stone;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ru.pozdnyak.rustcore.Main;

import java.util.Random;

public class ToolStone {
	private static ToolStone instance;
	
	public static ToolStone instance() {
		if (instance == null) {
			instance = new ToolStone();
		}
		
		return instance;
	}
	
	public boolean hasNeedTool(ItemStack item) {
		if (item != null && item.getType() != Material.AIR) {
			String materialName = item.getType().toString();
			
			for (String section : Main.plugin.getConfig().getConfigurationSection("Settings.Stones.MaterialAmount").getValues(false).keySet()) {
				if (materialName.contains(section)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int getRandomToolResource(Material material) {
		int amount = 0;
		String materialName = material.toString();
		
		for (String section : Main.plugin.getConfig().getConfigurationSection("Settings.Stones.MaterialAmount").getValues(false).keySet()) {
			if (materialName.contains(section)) {
				String amountString = Main.plugin.getConfig().getString("Settings.Stones.MaterialAmount." + section);
				
				if (amountString.contains("-")) {
					String[] parts = amountString.split("-");
					int min = Integer.parseInt(parts[0]);
					int max = Integer.parseInt(parts[1]);
					
					amount = randInt(min, max);
				} else {
					amount = Integer.parseInt(amountString);
				}
			}
		}
		
		return amount;
	}
	
	
	private int randInt(int min, int max) {
	    Random rand = new Random();
	    return rand.nextInt((max - min) + 1) + min;
	}
}
