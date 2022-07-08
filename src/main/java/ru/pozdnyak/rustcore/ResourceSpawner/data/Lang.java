package ru.pozdnyak.rustcore.ResourceSpawner.data;

import org.bukkit.ChatColor;

import ru.pozdnyak.rustcore.Main;

public class Lang {
	private static Lang instance;
	
	public static Lang instance() {
		if (instance == null) {
			instance = new Lang();
		}
		
		return instance;
	}
	
	public String DurabilityStone = getColor("Lang.DurabilityStone");
	public String DurabilityTree = getColor("Lang.DurabilityTree");
	public String InventoryFull = getColor("Lang.InventoryFull");
	public String BreakStone = getColor("Lang.BreakStone");
	public String BreakTree = getColor("Lang.BreakTree");
	
	
	private String getColor(String path) {
		return ChatColor.translateAlternateColorCodes('&', Main.plugin.getConfig().getString(path));
	}
}
