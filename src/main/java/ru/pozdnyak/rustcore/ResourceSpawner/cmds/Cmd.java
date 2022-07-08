package ru.pozdnyak.rustcore.ResourceSpawner.cmds;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.pozdnyak.rustcore.ResourceSpawner.stone.StoneManager;

public class Cmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			if (player.hasPermission("rustm.set")) {
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("set")) {
						String materialName = args[1].toUpperCase();
						Material material = Material.getMaterial(materialName);
						
						String resourceName = args[2].toUpperCase();
						Material materialResource = Material.getMaterial(resourceName);
						
						if (material != null && materialResource != null) {
							Block block = player.getTargetBlock(null, 5);
							
							if (block != null && block.getType() != Material.AIR) {
								StoneManager.instance().createdIfNeed(player, block, material, resourceName);
							} else {
								player.sendMessage("�6�������� ������ �� ����!");
							}
						} else {
							player.sendMessage("�6�� ����� �� ������ ��������!");
						}
					}
				} else {
					player.sendMessage("�7/rustm set <�f�������� ���������7> <�f������7> - ������: /rustm set stone diamond | [�f������ ���������� �� ����7]");
				}
			} else {
				player.sendMessage("�4� ��� ��� ����!");
			}
		}
		
		return false;
	}

}
