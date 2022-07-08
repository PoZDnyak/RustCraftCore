package ru.pozdnyak.rustcore.ResourceSpawner.data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.ResourceSpawner.stone.Stone;
import ru.pozdnyak.rustcore.ResourceSpawner.stone.StoneManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSaver {
	private static DataSaver instance;
	private File file = new File("plugins/RustMining/data.yml");
	private YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
	
	public static DataSaver instance() {
		if (instance == null) {
			instance = new DataSaver();
		}
		
		return instance;
	}
	
	private DataSaver() {
		if (!file.exists()) {
			saveFile();
		}
	}
	
	public void loadDataToServer() {
		List<String> list = config.getStringList("Stones");
		
		for (String string : list) {
			String[] parts = string.split(",");
			
			int x = Integer.parseInt(parts[0]);
			int y = Integer.parseInt(parts[1]);
			int z = Integer.parseInt(parts[2]);
			World world = Main.plugin.getServer().getWorld(parts[3]);
			Location location = new Location(world, x, y, z);
			Material material = Material.getMaterial(parts[4]);
			String resourceName = parts[5];
			Block block = world.getBlockAt(location);
			block.setType(material);
			
			StoneManager.instance().create(block, material, resourceName);
		}
	}
	
	public void saveDataToFile() {
		List<String> list = new ArrayList<String>();
		
		for (Stone stone : Stone.classes) {
			String string = stone.getBlockLocation().getBlockX() + 
					"," + stone.getBlockLocation().getBlockY() +
					"," + stone.getBlockLocation().getBlockZ() +
					"," + stone.getBlockLocation().getWorld().getName() +
					"," + stone.getMaterialName() +
					"," + stone.getResourceName();
			
			list.add(string);
		}
		
		config.set("Stones", list);
		saveFile();
	}
	
	private void saveFile() {
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
