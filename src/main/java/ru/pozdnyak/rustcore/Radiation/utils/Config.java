// 
// Decompiled by Procyon v0.5.36
// 

package ru.pozdnyak.rustcore.Radiation.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.pozdnyak.rustcore.Main;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config
{
    Main main;
    File f;
    FileConfiguration fc;
    boolean debug;
    List<String> radiationRegions;
    int radiationInterval;
    int radiationIncrement;
    int radiationHarmRequired;
    int radiationHarmInterval;
    int radiationCap;
    double radiationHarmDamage;
    String radiationIncreased;
    
    public Config() {
        this.main = Main.getMain();
        this.f = new File(this.main.getDataFolder(), "config.yml");
        if (!this.f.exists()) {
            this.main.saveResource("config.yml", true);
        }
        this.fc = (FileConfiguration)YamlConfiguration.loadConfiguration(this.f);
        this.load();
    }
    
    void load() {
        this.debug = this.fc.getBoolean("debug", false);
        this.radiationRegions = (List<String>)this.fc.getStringList("radiation.regions");
        this.radiationIncrement = this.fc.getInt("radiation.increment", 3);
        this.radiationInterval = this.fc.getInt("radiation.interval", 20);
        this.radiationHarmRequired = this.fc.getInt("radiation.harm.required", 250);
        this.radiationHarmInterval = this.fc.getInt("radiation.harm.interval", 20);
        this.radiationHarmDamage = this.fc.getDouble("radiation.harm.damage", 0.4);
        this.radiationCap = this.fc.getInt("radiation.cap", 500);
        this.radiationIncreased = this.fc.getString("messages.radiation-increased", "Radiation: %radiation%");
    }
    
    public void save() {
        try {
            this.fc.save(this.f);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setRadiationRegions(final List<String> list) {
        this.radiationRegions = list;
        this.fc.set("radiation.regions", (Object)list);
        this.save();
    }
    
    public boolean isDebug() {
        return this.debug;
    }
    
    public List<String> getRadiationRegions() {
        return this.radiationRegions;
    }
    
    public int getRadiationInterval() {
        return this.radiationInterval;
    }
    
    public int getRadiationIncrement() {
        return this.radiationIncrement;
    }
    
    public int getRadiationHarmRequired() {
        return this.radiationHarmRequired;
    }
    
    public int getRadiationHarmInterval() {
        return this.radiationHarmInterval;
    }
    
    public int getRadiationCap() {
        return this.radiationCap;
    }
    
    public double getRadiationHarmDamage() {
        return this.radiationHarmDamage;
    }
    
    public String getRadiationIncreased() {
        return this.radiationIncreased;
    }
}
