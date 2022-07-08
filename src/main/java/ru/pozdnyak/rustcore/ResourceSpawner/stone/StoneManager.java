package ru.pozdnyak.rustcore.ResourceSpawner.stone;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import ru.pozdnyak.rustcore.Main;

public class StoneManager {
	private static StoneManager instance;
	
	public static StoneManager instance() {
		if (instance == null) {
			instance = new StoneManager();
		}
		
		return instance;
	}
	
	public void createdIfNeed(Player player, Block block, Material material, String resourceName) {
		if (!Stone.containsByLocation(block.getLocation())) {
			block.setType(material);
			
			int durability = 10;
			if (Main.plugin.getConfig().contains("Settings.Stones.MaterialDurability." + material.toString())) {
				durability = Main.plugin.getConfig().getInt("Settings.Stones.MaterialDurability." + material.toString());
			}
			
			new Stone(block, durability, resourceName);
			player.sendMessage("�2���� �����������!");
		} else {
			player.sendMessage("�4���� ��� �����������!");
		}
	}
	
	public void create(Block block, Material material, String resourceName) {
		if (!Stone.containsByLocation(block.getLocation())) {
			block.setType(material);
			
			int durability = 10;
			if (Main.plugin.getConfig().contains("Settings.Stones.MaterialDurability." + material.toString())) {
				durability = Main.plugin.getConfig().getInt("Settings.Stones.MaterialDurability." + material.toString());
			}
			
			new Stone(block, durability, resourceName);
		}
	}
}
