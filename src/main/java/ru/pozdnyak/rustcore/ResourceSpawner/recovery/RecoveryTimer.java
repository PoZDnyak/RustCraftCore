package ru.pozdnyak.rustcore.ResourceSpawner.recovery;

import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.ResourceSpawner.stone.Stone;
import ru.pozdnyak.rustcore.ResourceSpawner.trees.Tree;

import java.util.Iterator;

public class RecoveryTimer {
	private static RecoveryTimer instance;
	private static int recoveryTimeStone = 1;
	private static int removeTimeTree = 1;
	
	public static RecoveryTimer instance() {
		if (instance == null) {
			instance = new RecoveryTimer();
			
			recoveryTimeStone = Main.plugin.getConfig().getInt("Settings.Stones.RecoveryTime");
			removeTimeTree = Main.plugin.getConfig().getInt("Settings.Trees.RemoveTime");
		}
		
		return instance;
	}
	
	public void startTimer() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				Long curTime = System.currentTimeMillis();
				
				for (Stone stone : Stone.classes) {
					if (stone.getDurability() <= 0) {
						long stoneTime = stone.getLastModify();
						
						if ((curTime - stoneTime) / 1000 >= recoveryTimeStone) {
							new BukkitRunnable() {
								
								@Override
								public void run() {
									stone.getBlockLocation().getBlock().setType(Material.getMaterial(stone.getMaterialName()));
								}
							}.runTask(Main.plugin);
							
							int durability = 10;
							if (Main.plugin.getConfig().contains("Settings.Stones.MaterialDurability." + stone.getMaterialName())) {
								durability = Main.plugin.getConfig().getInt("Settings.Stones.MaterialDurability." + stone.getMaterialName());
							}
							stone.setDurability(durability);
							stone.updateLastModify();
						}
					}
				}
				
				Iterator<Tree> it = Tree.classes.iterator();
				while (it.hasNext()) {
					Tree tree = it.next();
					
					long treeTime = tree.getLastModify();
					
					if ((curTime - treeTime) / 1000 >= removeTimeTree) {
						it.remove();
					}
				}
			}
		}.runTaskTimerAsynchronously(Main.plugin, 0, 20);
	}
}
