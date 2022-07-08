package ru.pozdnyak.rustcore.ResourceSpawner.trees;

import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	public static List<Tree> classes = new ArrayList<Tree>();
	
	private List<Block> blocks = new ArrayList<Block>();
	private int durability = 0;
	private long lastModify = 0;
	
	public Tree(List<Block> blocks, int durability) {
		this.blocks = blocks;
		this.durability = durability;
		this.lastModify = System.currentTimeMillis();
		
		classes.add(this);
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}

	public int getDurability() {
		return durability;
	}
	
	public void minusDurability() {
		durability = durability -1;
	}
	
	public long getLastModify() {
		return lastModify;
	}

	public void updateLastModify() {
		this.lastModify = System.currentTimeMillis();
	}
	
	public static Tree getByBlock(Block block) {
		for (Tree tree : classes) {
			if (tree.getBlocks().contains(block)) {
				return tree;
			}
		}
		
		return null;
	}
	
	public static boolean containsByBlock(Block block) {
		for (Tree tree : classes) {
			if (tree.getBlocks().contains(block)) {
				return true;
			}
		}
		
		return false;
	}
}
