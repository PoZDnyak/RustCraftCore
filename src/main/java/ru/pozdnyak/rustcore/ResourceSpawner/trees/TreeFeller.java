package ru.pozdnyak.rustcore.ResourceSpawner.trees;

import org.bukkit.block.Block;
import ru.pozdnyak.rustcore.Main;

import java.util.List;

public class TreeFeller {
	private static TreeFeller instance;
	
	public static TreeFeller instance() {
		if (instance == null) {
			instance = new TreeFeller();
		}
		
		return instance;
	}
	
	public void removeTree(Block block) {
		new TreeCutter(block).runTaskAsynchronously(Main.plugin);
	}
	
	public List<Block> getBlocksList(Block block){
		TreeCutter tCutter = new TreeCutter(block);
		List<Block> blocks = tCutter.runLoop(block, block.getX(), block.getZ());
		
		if (!tCutter.isTree()) {
			blocks.clear();
		}
		
		return blocks;
	}
}
