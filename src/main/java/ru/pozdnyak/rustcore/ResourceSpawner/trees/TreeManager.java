package ru.pozdnyak.rustcore.ResourceSpawner.trees;

import org.bukkit.block.Block;
import ru.pozdnyak.rustcore.Main;

import java.util.List;

public class TreeManager {
	private static TreeManager instance;
	
	public static TreeManager instance() {
		if (instance == null) {
			instance = new TreeManager();
		}
		
		return instance;
	}
	
	public void createIfNeed(Block block) {
		if (!Tree.containsByBlock(block)) {
			List<Block> blocks = TreeFeller.instance().getBlocksList(block);
			
			if (!blocks.isEmpty()) {
				int BaseDurability = Main.plugin.getConfig().getInt("Settings.Trees.DefaultDurability");
				int BlockMod = Main.plugin.getConfig().getInt("Settings.Trees.BlockMod");
				
				BaseDurability += blocks.size() * BlockMod;
				
				new Tree(blocks, BaseDurability);
			}
		}
	}
}
