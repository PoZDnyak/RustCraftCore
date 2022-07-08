// 
// Decompiled by Procyon v0.5.36
// 

package ru.pozdnyak.rustcore.Radiation.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.Radiation.utils.Util;

public class RadiationCommand implements CommandExecutor
{
    Main main;
    
    public RadiationCommand(Main plugin) {
        this.main = plugin;
    }
    
    @Override
    public boolean onCommand(final CommandSender sender, final Command arg1, final String arg2, final String[] args) {
        Bukkit.getScheduler().runTaskAsynchronously(this.main, (Runnable)new Runnable() {
            @Override
            public void run() {
                if (sender.hasPermission("rmc.admin")) {
                    if (args.length == 0) {
                        Util.msg(sender, "&7Usage: ");
                        Util.msg(sender, "&e- /radiation set/toggle <regionName>&f - Включить радиацию в регионе.");
                        Util.msg(sender, "&e- /radiation reload&f - Перезагрузить конфиг");
                    }
                    else if (args[0].equalsIgnoreCase("reload")) {
                        if (sender.hasPermission("rmc.region.reload")) {
                            RadiationCommand.this.main.reload();
                            Util.msg(sender, "&aRadiation Configuration has been reloaded!");
                        }
                    }
                    else if ((args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("toggle")) && sender.hasPermission("rmc.region.set")) {
                        if (args.length == 2) {
                            final String regionId = args[1];
                            RadiationCommand.this.main.getRadiationManager().toggleRadiation(sender, regionId);
                        }
                        else {
                            Util.msg(sender, "&e- /radiation set/toggle <regionName>&f - Toggles active radiation in the region.");
                        }
                    }
                }
            }
        });
        return true;
    }
}
