// 
// Decompiled by Procyon v0.5.36
// 

package ru.pozdnyak.rustcore.Radiation.listeners;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.pozdnyak.rustcore.Radiation.utils.Util;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.pozdnyak.rustcore.Main;
import org.bukkit.event.Listener;

public class Listeners implements Listener
{
    Main main;
    
    public Listeners(Main plugin) {
        this.main = plugin;
    }


    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLaterAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                if (!event.getPlayer().hasPermission("rmc.region.immune")) {
                    final Location loc = player.getLocation();
                    final boolean radiated = Listeners.this.main.getRadiationManager().isRadiationLocation(loc);
                    if (radiated) {
                        Listeners.this.main.getRadiationManager().killQueue.add(player);
                    }
                }
            }
        }, 20L);
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Location to = event.getTo();
        final Location from = event.getFrom();
        final Player player = event.getPlayer();
        if (to.getBlockX() != from.getBlockX() || to.getBlockY() != from.getBlockY() || to.getBlockZ() != from.getBlockZ()) {
            Bukkit.getScheduler().runTaskAsynchronously(this.main, (Runnable)new Runnable() {
                @Override
                public void run() {
                    final boolean radiatedTo = Listeners.this.main.getRadiationManager().isRadiationLocation(to);
                    if (radiatedTo) {
                        if (!Listeners.this.main.getRadiationManager().isInRadiationRegion(player.getUniqueId())) {
                            Util.debug("Moved into radiation zone.");
                            Listeners.this.main.getRadiationManager().enterRadiationArea(player.getUniqueId());
                        }
                    }
                    else if (Listeners.this.main.getRadiationManager().isInRadiationRegion(player.getUniqueId())) {
                        Util.debug("Moved out of radiation zone.");
                        Listeners.this.main.getRadiationManager().leaveRadiationArea(player.getUniqueId());
                    }
                }
            });
        }
    }
    
    @EventHandler
    public void onPlayerTeleport(final PlayerTeleportEvent event) {
        final Player player = event.getPlayer();
        final Location loc = event.getTo();
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)this.main, (Runnable)new Runnable() {
            @Override
            public void run() {
                final boolean radiated = Listeners.this.main.getRadiationManager().isRadiationLocation(loc);
                if (radiated) {
                    if (!Listeners.this.main.getRadiationManager().isInRadiationRegion(player.getUniqueId())) {
                        Listeners.this.main.getRadiationManager().enterRadiationArea(player.getUniqueId());
                    }
                }
                else if (Listeners.this.main.getRadiationManager().isInRadiationRegion(player.getUniqueId())) {
                    Listeners.this.main.getRadiationManager().leaveRadiationArea(player.getUniqueId());
                }
            }
        });
    }
    
    public void onPlayerDeath(final PlayerDeathEvent event) {
        final Player player = event.getEntity();
        this.main.getRadiationManager().resetRadiation(player);
        this.main.getRadiationManager().killQueue.remove(player);
    }
}
