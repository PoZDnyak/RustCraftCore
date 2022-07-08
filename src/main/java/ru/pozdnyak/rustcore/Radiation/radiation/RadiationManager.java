package ru.pozdnyak.rustcore.Radiation.radiation;

import com.google.common.collect.Lists;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.PlayerStars.PlayerStats;
import ru.pozdnyak.rustcore.Radiation.utils.Util;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RadiationManager
{
    Main main;

    public HashMap<UUID, Integer> getRadiation()
    {
        return this.radiation;
    }

    HashMap<UUID, Integer> radiation = new HashMap();
    List<UUID> inRadiationRegion = Lists.newArrayList();
    public List<Player> killQueue = Lists.newArrayList();

    public RadiationManager()
    {
        this.main = Main.getMain();

        Bukkit.getScheduler().runTaskAsynchronously(this.main, new Runnable()
        {
            @Override
            public void run()
            {
                RadiationManager.this.processRadiationIncrement();
                RadiationManager.this.processRadiationHarm();
                RadiationManager.this.processKillQueue();
            }
        });
    }

    public List<String> getActiveRegions()
    {
        return this.main.getCfg().getRadiationRegions();
    }

    public void setActiveRegions(List<String> list)
    {
        this.main.getCfg().setRadiationRegions(list);
    }

    public void toggleRadiation(CommandSender sender, String regionName)
    {
        List<String> list = getActiveRegions();
        if (list != null) {
            list = Lists.newArrayList();
        }
        if (list.contains(regionName))
        {
            list.remove(regionName);
            Util.msg(sender, "&c" + regionName + " is no longer a radiation zone.");
        }
        else
        {
            list.add(regionName);
            Util.msg(sender, "&a" + regionName + " is now a radiation zone.");
        }
        setActiveRegions(list);
    }

    public boolean isRadiationRegion(String regionName)
    {
        return this.main.getCfg().getRadiationRegions().contains(regionName);
    }

    public boolean isRadiationLocation(Location loc)
    {
        boolean b = false;

        RegionManager rm = this.main.getWorldGuard().getRegionManager(loc.getWorld());
        ApplicableRegionSet regions = rm.getApplicableRegions(loc);
        for (ProtectedRegion region : regions)
        {
            String id = region.getId();
            if (isRadiationRegion(id))
            {
                b = true;
                break;
            }
        }
        return b;
    }

    public int getRadiation(Player player)
    {
        return getRadiation(player.getUniqueId());
    }

    public int getRadiation(UUID uuid)
    {
        int rad = 0;
        if (this.radiation.containsKey(uuid)) {
            rad = ((Integer)this.radiation.get(uuid)).intValue();
        }
        return rad;
    }

    void processRadiationIncrement()
    {
        List<UUID> uuids = Lists.newArrayList(this.radiation.keySet());
        int increment = this.main.getCfg().getRadiationIncrement();
        for (UUID uuid : uuids)
        {
            Player player = Bukkit.getPlayer(uuid);
            if ((player != null) &&
                    (!player.hasPermission("rmc.region.immune"))) {
                if (!player.isDead())
                {
                    int rad = getRadiation(uuid);
                    if (isInRadiationRegion(uuid)) {
                        rad += increment;
                    } else {
                        rad -= increment;
                    }
                    if (rad > this.main.getCfg().getRadiationCap()) {
                        rad = this.main.getCfg().getRadiationCap();
                    }
                    if (rad <= 0) {
                        this.radiation.remove(uuid);
                    } else {
                        this.radiation.put(uuid, Integer.valueOf(rad));
                    }
                    player.sendMessage(String.valueOf(rad));
                    PlayerStats.setRadiation(player,rad);
                }
                else
                {
                    this.radiation.remove(uuid);
                    this.inRadiationRegion.remove(uuid);
                }
            }
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(this.main, new Runnable()
        {
            @Override
            public void run()
            {
                RadiationManager.this.processRadiationIncrement();
            }
        }, this.main.getCfg().getRadiationInterval());
    }

    void processRadiationHarm()
    {
        List<UUID> uuids = Lists.newArrayList(this.radiation.keySet());
        int required = this.main.getCfg().getRadiationHarmRequired();
        for (UUID uuid : uuids)
        {
            int rad = getRadiation(uuid);
            if (rad >= required)
            {
                Player player = Bukkit.getPlayer(uuid);
                if ((player != null) &&
                        (!player.isDead()) &&
                        (!player.hasPermission("rmc.region.immune"))) {
                    try
                    {
                        player.damage(this.main.getCfg().getRadiationHarmDamage());
                    }
                    catch (Exception localException) {}
                }
            }
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(this.main, new Runnable()
        {
            @Override
            public void run()
            {
                RadiationManager.this.processRadiationHarm();
            }
        }, this.main.getCfg().getRadiationHarmInterval());
    }

    public boolean isInRadiationRegion(UUID uuid)
    {
        return this.inRadiationRegion.contains(uuid);
    }

    public void enterRadiationArea(UUID uuid)
    {
        int rad = getRadiation(uuid);
        this.radiation.put(uuid, Integer.valueOf(rad));
        this.inRadiationRegion.add(uuid);
    }

    public void leaveRadiationArea(UUID uuid)
    {
        this.inRadiationRegion.remove(uuid);
    }

    public void resetRadiation(Player player)
    {
        this.radiation.remove(player.getUniqueId());
        if (isInRadiationRegion(player.getUniqueId())) {
            this.inRadiationRegion.remove(player.getUniqueId());
        }
    }

    void processKillQueue()
    {
        if (!this.killQueue.isEmpty())
        {
            Player player = (Player)this.killQueue.get(0);
            if (!player.isDead()) {
                player.damage(player.getHealth());
            } else {
                this.killQueue.remove(player);
            }
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously(this.main, new Runnable()
        {
            @Override
            public void run()
            {
                RadiationManager.this.processKillQueue();
            }
        }, 1L);
    }
}
