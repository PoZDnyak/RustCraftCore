// 
// Decompiled by Procyon v0.5.36
// 

package ru.pozdnyak.rustcore.Radiation.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import ru.pozdnyak.rustcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util
{
    public static void msg(final CommandSender sender, final String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }
    
    public static void error(final CommandSender sender, final String msg) {
        msg(sender, "&4ERROR:&7 " + msg);
    }
    
    public static void error(final String msg) {
        error((CommandSender)Bukkit.getConsoleSender(), msg);
    }
    
    public static void debug(final String msg) {
        if (Main.getMain().getCfg().isDebug()) {
            msg((CommandSender)Bukkit.getConsoleSender(), "&e[&7DEBUG&e]&r " + msg);
        }
    }
    
    public static void actionBar(final Player player, final String msg) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(ChatColor.translateAlternateColorCodes('&', msg)));
    }
}
