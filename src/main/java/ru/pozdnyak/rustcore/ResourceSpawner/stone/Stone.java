package ru.pozdnyak.rustcore.ResourceSpawner.stone;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Stone {
	public static List<Stone> classes = new ArrayList<Stone>();
	
	private Location blockLocation = null;
	private String materialName = null;
	private String resourceName = null;
	private int durability = 0;
	private long lastModify = 0;
	
	public Stone(Block block, int durability, String resourceName) {
		this.blockLocation = block.getLocation();
		this.materialName = block.getType().toString();
		this.resourceName = resourceName;
		this.durability = durability;
		this.lastModify = System.currentTimeMillis();
		
		classes.add(this);
	}
	
	public Location getBlockLocation() {
		return blockLocation;
	}
	
	public String getMaterialName() {
		return materialName;
	}
	
	public String getResourceName() {
		return resourceName;
	}

	public int getDurability() {
		return durability;
	}
	
	public void minusDurability() {
		durability = durability -1;
	}
	
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public long getLastModify() {
		return lastModify;
	}

	public void updateLastModify() {
		this.lastModify = System.currentTimeMillis();
	}
	
	public static Stone getByLocation(Location blockLocation) {
		for (Stone stone : classes) {
			if (stone.getBlockLocation().getBlockX() == blockLocation.getBlockX()) {
				if (stone.getBlockLocation().getBlockY() == blockLocation.getBlockY()) {
					if (stone.getBlockLocation().getBlockZ() == blockLocation.getBlockZ()) {
						return stone;
					}	
				}
			}
		}
		
		return null;
	}
	
	public static boolean containsByLocation(Location blockLocation) {
		for (Stone stone : classes) {
			if (stone.getBlockLocation().getBlockX() == blockLocation.getBlockX()) {
				if (stone.getBlockLocation().getBlockY() == blockLocation.getBlockY()) {
					if (stone.getBlockLocation().getBlockZ() == blockLocation.getBlockZ()) {
						return true;
					}	
				}
			}
		}
		
		return false;
	}
}
