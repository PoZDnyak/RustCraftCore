package ru.pozdnyak.rustcore.ResourceSpawner.trees;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import ru.pozdnyak.rustcore.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TreeCutter extends BukkitRunnable {
    private Block startBlock;
    private List<String> comparisonBlockArray = new ArrayList<>();
    private List<String> scanned = new ArrayList<>();
    private List<String> comparisonBlockArrayLeaves = new ArrayList<>();
    private List<Block> blocks = new ArrayList<>();
    private int indexed = 0;
    private boolean loop = false;
    private boolean initialized;


    public TreeCutter(Block startBlock) {
        this.startBlock = startBlock;
    }

    public List<Block> runLoop(Block block, final int centerX, final int centerZ) {
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -2; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0)
                        continue;
                    Block blockRelative = block.getRelative(x, y, z);
                    String s = blockRelative.getX() + ":" + blockRelative.getY() + ":" + blockRelative.getZ();
                    if (scanned.contains(s)){
                        continue;
                    }
                    scanned.add(s);
                    if (blockRelative.getType().toString().contains(Material.LEAVES.toString()) && !comparisonBlockArrayLeaves.contains(s)) {
                        comparisonBlockArrayLeaves.add(s);
                        continue;
                    }

                    if (blockRelative.getType().toString().contains(Material.LOG.toString())) {
                        int searchSquareSize = 25;
                        if (blockRelative.getX() > centerX + searchSquareSize || blockRelative.getX() < centerX - searchSquareSize
                                || blockRelative.getZ() > centerZ + searchSquareSize || blockRelative.getZ() < centerZ - searchSquareSize)
                            break;
                        if (!comparisonBlockArray.contains(s)) {
                            comparisonBlockArray.add(s);
                            blocks.add(blockRelative);
                            this.runLoop(blockRelative, centerX, centerZ);
                        }
                    }
                }
            }
        }
        
        return blocks;
    }

    @Override
    public void run() {
        if (initialized){
            return;
        }
        initialized = true;
        blocks.add(startBlock);
        runLoop(startBlock, startBlock.getX(), startBlock.getZ());

        if (isTree()) {
            cutDownTree();
        } else {
            new BukkitRunnable() {

                @Override
                public void run() {
                    Location center = startBlock.getLocation().add(0.5, 0.5, 0.5);
                    for (ItemStack stack : startBlock.getDrops())
                        startBlock.getWorld().dropItem(center, stack);
                    
                    
                    startBlock.setType(Material.AIR);
                }
            }.runTask(Main.plugin);
        }
    }

    public boolean isTree() {
        return (comparisonBlockArrayLeaves.size() * 1D) / (blocks.size() * 1D) > 0.3;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    private void cutDownTree() {
        blocks = blocks.stream().sorted((b, b2) -> b2.getY() - b.getY()).collect(Collectors.toList());
        
        new BukkitRunnable() {
        	
            @SuppressWarnings("deprecation")
			@Override
            public void run() {
                if (true && !loop) {
                    for (int i = 0; i < blocks.size(); i++) {
                        loop = true;
                        this.run();
                    }
                    this.cancel();
                    return;
                }

                if (blocks.size() < indexed - 2) {
                    this.cancel();
                    return;
                }

                Block block = blocks.get(indexed++);

                if (block.getX() == startBlock.getX() && block.getZ() == startBlock.getZ() && block.getY() <= startBlock.getY()) {
                    Block b = block.getRelative(BlockFace.DOWN);

                    if ((b.getType() == Material.DIRT || b.getType() == Material.GRASS) && blocks.size() > 5) {
                    	byte data = getSaplingData(block);
                    	block.setType(Material.SAPLING);
                    	block.setData(data);
                    } else {
                        block.setType(Material.AIR);
                    }
                } else {
                    block.setType(Material.AIR);
                }

                if (blocks.size() <= indexed)
                    this.cancel();
            }

            @Override
            public void cancel() {
                super.cancel();
            }


        }.runTaskTimer(Main.plugin, 0L, 20);
    }
    
    //TODO, add all types
    @SuppressWarnings("deprecation")
	private byte getSaplingData(Block block) {
    	byte data = 0;
    	byte blockData = block.getData();
    	String blockMaterial = block.getType().toString();
    	
    	 if (blockMaterial.equalsIgnoreCase("LOG_2")) {
     		if (blockData == 0) {
     			data = 4;
     		} else if (blockData == 1) {
     			data = 5;
     		}
     	} else if (blockMaterial.equalsIgnoreCase("LOG")) {
    		data = blockData;
    	}
    	
    	return data;
    }
}
